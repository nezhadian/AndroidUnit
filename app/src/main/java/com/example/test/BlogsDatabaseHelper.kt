import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.test.BlogItem
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class BlogsDatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "blog.db"
        private const val DATABASE_VERSION = 3
        private lateinit var DATABASE_PATH : String
        const val TABLE_BLOG = "blogs"
        const val COLUMN_TITLE = "Title"
        const val COLUMN_CONTENT = "Content"
    }

    init {
        DATABASE_PATH = "${context.getApplicationInfo().dataDir}/databases/"
    }

    override fun onCreate(db: SQLiteDatabase) {
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if(newVersion>oldVersion)
            safeCopyDatabase()


    }

    fun createDatabase() {
        val dbDir = File(DATABASE_PATH)
        if (!dbDir.exists()) {
            dbDir.mkdirs() // Create the directory
        }

        val dbFile = File(DATABASE_PATH + DATABASE_NAME)
        if (!dbFile.exists()) {
            this.readableDatabase // This will create the database file
            safeCopyDatabase()
        }
    }

    private  fun safeCopyDatabase(){
        try {
            copyDatabase()
        } catch (e: Exception) {
            Toast.makeText(context,"Error copying database: $e",Toast.LENGTH_LONG).show()
        }
    }
    private fun copyDatabase() {
        val inputStream: InputStream = context.assets.open("databases/$DATABASE_NAME")
        val outputStream:OutputStream = FileOutputStream(DATABASE_PATH + DATABASE_NAME)

        // Open the asset file

        // Use a buffer to read and write the data
        val buffer = ByteArray(1024)
        var length: Int

        try {
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
        } finally {
            // Close the streams
            inputStream.close()
            outputStream.close()
        }
    }


    // Method to retrieve all blog items
    fun getAllBlogItems(): List<BlogItem> {
        val blogItems = mutableListOf<BlogItem>()
        val db = this.readableDatabase

        try{


            val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_BLOG", null)

            while (cursor.moveToNext()){
                val titleI = cursor.getColumnIndex(COLUMN_TITLE);
                val contentI = cursor.getColumnIndex(COLUMN_CONTENT)

                if(titleI == -1 || contentI == -1)
                    continue

                val title = cursor.getString(titleI)
                val content = cursor.getString(contentI)
                blogItems.add(BlogItem(title, content, false))
            }


            cursor.close()
            db.close()
        }
        catch (e:Exception){
            Toast.makeText(context,"We have an error : $e",Toast.LENGTH_LONG).show();
        }

        return blogItems
    }

    // Method to insert a blog item
    fun insertBlogItem(title: String, content: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_CONTENT, content)
        db.insert(TABLE_BLOG, null, values)
        db.close()
    }
}

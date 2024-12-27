import android.content.Context
import android.content.SharedPreferences

class LoginManager(context: Context) {

    // SharedPreferences instance
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

    // Key for the boolean value
    private val LOGINKEY = "is_login"

    // Check if the user is logged in
    fun IsLogin(): Boolean {
        return sharedPreferences.getBoolean(LOGINKEY, false)
    }

    // Log in the user
    fun Login() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(LOGINKEY, true)
        editor.apply()
    }

    // Log out the user
    fun Logout() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(LOGINKEY, false)
        editor.apply()
    }
}
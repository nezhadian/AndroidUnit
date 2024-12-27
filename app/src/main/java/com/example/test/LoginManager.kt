import android.content.Context
import android.content.SharedPreferences

class LoginManager(context: Context) {

    // SharedPreferences instance
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

    // Keys for SharedPreferences
    private val LOGIN_KEY = "is_login"
    private val USERNAME_KEY = "username"

    // Check if the user is logged in
    fun IsLogin(): Boolean {
        return sharedPreferences.getBoolean(LOGIN_KEY, false)
    }

    // Get the saved username
    fun GetUsername(): String? {
        return sharedPreferences.getString(USERNAME_KEY, null)
    }

    // Log in the user and save the username
    fun Login(username: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(LOGIN_KEY, true)
        editor.putString(USERNAME_KEY, username)
        editor.apply()
    }

    // Log out the user and clear the username
    fun Logout() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(LOGIN_KEY, false)
        editor.remove(USERNAME_KEY) // Clear the username
        editor.apply()
    }
}
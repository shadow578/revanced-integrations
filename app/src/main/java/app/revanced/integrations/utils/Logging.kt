package app.revanced.integrations.utils

import android.util.Log
import app.revanced.integrations.BuildConfig
import app.revanced.integrations.settings.SettingsEnum

/**
 * logging helper, closely wraps [Log]
 */
@Suppress("unused")
object Logging {

    /**
     * log a debug message
     *
     * @see Log.d
     * @param callingClass the class to log from. used for logging tag creation
     * @param message the message to log
     */
    @JvmStatic
    fun debug(callingClass: Class<*>?, message: String) {
        if (BuildConfig.DEBUG || SettingsEnum.DEBUG.boolean) {
            Log.d(getLogTag(callingClass), message)
        }
    }

    /**
     * log a informational message
     *
     * @see Log.i
     * @param callingClass the class to log from. used for logging tag creation
     * @param message the message to log
     */
    @JvmStatic
    fun info(callingClass: Class<*>?, message: String) =
        Log.i(getLogTag(callingClass), message)

    /**
     * log a warning message
     *
     * @see Log.w
     * @param callingClass the class to log from. used for logging tag creation
     * @param message the message to log
     */
    @JvmStatic
    fun warning(callingClass: Class<*>?, message: String) =
        Log.w(getLogTag(callingClass), message)

    /**
     * log a error message
     *
     * @see Log.e
     * @param callingClass the class to log from. used for logging tag creation
     * @param message the message to log
     */
    @JvmStatic
    fun error(callingClass: Class<*>?, message: String) = error(callingClass, message, null)

    /**
     * log a error message
     *
     * @see Log.e
     * @param callingClass the class to log from. used for logging tag creation
     * @param throwable throwable to attach to the log message
     * @param message the message to log
     */
    @JvmStatic
    fun error(callingClass: Class<*>?, message: String, throwable: Throwable?) =
        Log.e(getLogTag(callingClass), message, throwable)

    /**
     * log a wtf message
     *
     * @see Log.wtf
     * @param callingClass the class to log from. used for logging tag creation
     * @param message the message to log
     */
    @JvmStatic
    fun wtf(callingClass: Class<*>?, message: String) = wtf(callingClass, message, null)

    /**
     * log a wtf message
     *
     * @see Log.wtf
     * @param callingClass the class to log from. used for logging tag creation
     * @param throwable throwable to attach to the log message
     * @param message the message to log
     */
    @JvmStatic
    fun wtf(callingClass: Class<*>?, message: String, throwable: Throwable?) =
        Log.wtf(getLogTag(callingClass), message, throwable)

    /**
     * get the log tag to use
     *
     * @param callingClass class that called the logging function
     * @return the logging tag
     */
    private fun getLogTag(callingClass: Class<*>?): String = callingClass?.simpleName ?: "unknown"
}

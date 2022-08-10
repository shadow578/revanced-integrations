package app.revanced.integrations

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import com.google.android.apps.youtube.app.YouTubeTikTokRoot_Application

/**
 * main application class of ReVanced.
 * note that this class is set as the application class in the AndroidManifest of the patched application
 *
 * @smali Lapp/revanced/integrations/ReVancedApp;
 */
class ReVancedApp : YouTubeTikTokRoot_Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
        checkMicroG()
    }

    /**
     * check if microG is installed.
     * if the microG patch is not active, this will do nothing
     */
    private fun checkMicroG() {
        // get microG package name
        // if not set, assume microG patch is not active
        val metaData =
            packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA).metaData
        val microGPackageName = metaData.getString(META_MICROG_PACKAGE_NAME)
        if (microGPackageName.isNullOrEmpty()) {
            return
        }

        // check if microG is installed
        try {
            packageManager.getPackageInfo(microGPackageName, PackageManager.GET_ACTIVITIES)
            return
        } catch (_: PackageManager.NameNotFoundException) {
            // not installed
        }

        // show info that microG is not installed
        Toast.makeText(
            this,
            "ReVanced microG was not found! The app may not work as expected.",
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        const val META_MICROG_PACKAGE_NAME = "app.revanced.android.youtube.MICROG_PACKAGE_NAME"

        @Volatile
        private lateinit var instance: ReVancedApp

        /**
         * static reference to the application class instance
         */
        @JvmStatic
        val staticApplication: ReVancedApp
            get() = instance

        /**
         * static reference to the application context
         */
        @JvmStatic
        val staticApplicationContext: Context
            get() = instance.applicationContext
    }
}
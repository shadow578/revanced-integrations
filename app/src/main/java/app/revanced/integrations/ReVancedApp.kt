package app.revanced.integrations

import android.content.Context
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
    }

    companion object {
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
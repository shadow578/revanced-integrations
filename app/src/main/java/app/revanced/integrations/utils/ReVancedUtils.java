package app.revanced.integrations.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.google.android.apps.youtube.app.YouTubeTikTokRoot_Application;

import app.revanced.integrations.sponsorblock.player.PlayerType;

public class ReVancedUtils {

    private static PlayerType env;
    private static boolean newVideo = false;

    //Used by Integrations patch
    public static Context context;
    //Used by Integrations patch
    public static Context getAppContext() {
        if (context != null) {
            return context;
        }
        LogHelper.printException(ReVancedUtils.class, "Context is null!");
        return null;
    }

    public static void setNewVideo(boolean started) {
        LogHelper.debug(ReVancedUtils.class, "New video started: " + started);
        newVideo = started;
    }

    public static boolean isNewVideoStarted() {
        return newVideo;
    }

    /**
     * @deprecated this method is deprecated. use {@link ResourcesHelper#getIdentifier(Context, ResourcesHelper.ResourceType, String)} or a more specialized function in {@link ResourcesHelper} instead!
     */
    @Deprecated(since = "0.24.2", forRemoval = true)
    public static Integer getResourceIdByName(Context context, String type, String name) {
        try {
            Resources res = context.getResources();
            return res.getIdentifier(name, type, context.getPackageName());
        } catch (Throwable exception) {
            LogHelper.printException(ReVancedUtils.class, "Resource not found.", exception);
            return null;
        }
    }

    /**
     * @deprecated this method is deprecated. depend your patch on app.revanced.patches.youtube.misc.playertype.patch.PlayerTypeHookPatch instead!
     */
    @Deprecated(since = "0.24.2", forRemoval = true)
    public static void setPlayerType(PlayerType type) {
        env = type;
    }

    /**
     * @deprecated this method is deprecated. use {@link app.revanced.integrations.shared.PlayerType#getCurrent()} instead!
     */
    @Deprecated(since = "0.24.2", forRemoval = true)
    public static PlayerType getPlayerType() {
        return env;
    }

    /**
     * @deprecated this method is deprecated. use {@link ResourcesHelper#getIdentifier(Context, ResourcesHelper.ResourceType, String)} or a more specialized function in {@link ResourcesHelper} instead!
     */
    @Deprecated(since = "0.24.2", forRemoval = true)
    public static int getIdentifier(String name, String defType) {
        Context context = getContext();
        return context.getResources().getIdentifier(name, defType, context.getPackageName());
    }

    public static void runOnMainThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public static Context getContext() {
        Context context = YouTubeTikTokRoot_Application.getAppContext();
        if (context != null) {
            return context;
        } else {
            LogHelper.printException(ReVancedUtils.class, "Context is null, returning null!");
            return null;
        }
    }

    public static boolean isTablet(Context context) {
        return context.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }
}
package app.revanced.integrations.utils;

/**
 * @deprecated this class is deprecated. use {@link Logging} instead!
 */
@Deprecated(since = "0.24.2", forRemoval = true)
public class LogHelper {

    /**
     * @deprecated this method is deprecated. use {@link Logging#debug(Class, String)} instead!
     */
    @Deprecated(since = "0.24.2", forRemoval = true)
    public static void debug(Class clazz, String message) {
        Logging.debug(clazz, message);
    }

    /**
     * @deprecated this method is deprecated. use {@link Logging#error(Class, String, Throwable)} instead!
     */
    @Deprecated(since = "0.24.2", forRemoval = true)
    public static void printException(Class clazz, String message, Throwable ex) {
        Logging.error(clazz, message, ex);
    }

    /**
     * @deprecated this method is deprecated. use {@link Logging#error(Class, String)} instead!
     */
    @Deprecated(since = "0.24.2", forRemoval = true)
    public static void printException(Class clazz, String message) {
        Logging.error(clazz, message);
    }

    /**
     * @deprecated this method is deprecated. use {@link Logging#info(Class, String)} instead!
     */
    @Deprecated(since = "0.24.2", forRemoval = true)
    public static void info(Class clazz, String message) {
        Logging.info(clazz, message);
    }
}

package io.github.devas.net;

public class TimeStamp {

    private static boolean enabled = true;

    public static void enableTimeStamp() {
        enabled = true;
    }

    public static void disableTimeStamp() {
        enabled = false;
    }

    public static String getTimeStamp() {
        return enabled ? new java.util.Date().toString() + " | " : "";
    }

}

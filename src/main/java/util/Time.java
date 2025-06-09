package util;

public class Time {
    public static float timeStarted = System.nanoTime();

    // getTime function returns how long the application has been running for in seconds (cast to a float)
    public static float getTime() {return (float)((System.nanoTime() - timeStarted)*(1E-9));}


}

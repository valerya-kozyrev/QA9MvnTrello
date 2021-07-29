package org.example.util;


import org.apache.log4j.Logger;

public class LogLog4j {

    // Initialize Log4j log s
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());//

//    public static int rowCount = 0;

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public static void startTestCase(String testCaseName) {
        Log.info("");
        Log.info("");
        Log.info(" ======================================================================");
        Log.info(" \t\t\t\tTest Case started:\t" + testCaseName);
        Log.info(" ----------------------------------------------------------------------");
        Log.info("");
    }

    //This is to print log for the ending of the test case
    public static void startMethod(String methodName) {
        Log.info("       Method - " + methodName + " - STARTED");
    }

    public static void endMethod(String methodName) {
        Log.info("       Method - " + methodName + " - ENDED");
    }

    public static void endTestCase(String testCaseName) {
        Log.info("");
        Log.info(" ----------------------------------------------------------------------");
        Log.info(" \t\t\t\tTest Case ended:\t" + testCaseName);
        Log.info(" ======================================================================");
        Log.info("");
        Log.info("");
    }

    // Need to create these methods, so that they can be called
    public static void parametricII(String str, String str2) {
        Log.info("\t\t\t\t" + str + " - '" + str2 + "' - STARTED");
    }

    public static void parametricIII(String str, String str2, String str3) {
        Log.info("\t\t\t\t" + str + str2 + str3 + " - STARTED");
    }

    public static void info(String message) {
        Log.info("\t\t\t\t" + message);
    }

    public static void info1(String message) {
        Log.info("\t\t\t\t" + message);
    }

    public static void verify(String message) {
        Log.info("Verify:   " + message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }

}

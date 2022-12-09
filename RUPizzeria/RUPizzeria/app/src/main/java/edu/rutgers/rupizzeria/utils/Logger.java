package edu.rutgers.rupizzeria.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Custom static Logger class that can log to either the LogCat or the app UI
 * @author Michael Liu, Genfu Liu
 */
public class Logger {

    /**
     * The display tag in Logcat to easily find everything this logger logs
     */
    private static final String TAG = "CUSTOM_LOGGER";

    /**
     * Logs a message to the android console logcat
     * @param message the message to be logged
     */
    public static void log(String message) {
        Log.d(TAG, message);
    }

    /**
     * Logs a message to the android console logcat with formatting
     * @param format a format string of the message to be logged
     * @param args arguments for the format string
     */
    public static void logf(String format, Object ... args) {
        log(String.format(format, args));
    }

    /**
     * Creates an Confirmation AlertDialog with the specified message and onClick events
     * @param context the current context to open the dialog
     * @param title the title of the dialog
     * @param message the log message that the dialog displays
     * @param positive The action to be done when the user clicks the positive confirmation button "Yes"
     * @param negative The action to be done when the user clicks the negative confirmation button "Cancel"
     */
    public static void logAlertConfirmation(Context context, String title, String message, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("Yes", positive)
                .setNegativeButton("Cancel", negative)
                .show();

    }

}

package edu.rutgers.rupizzeria.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import kotlin.text.UStringsKt;

public class Logger {

    private static final String TAG = "CUSTOM_LOGGER";

    /**
     * Since this is the main client controller class,
     * it will also be responsible for logging messages to the alert box "console"
     * such as helpful info as well as errors/warnings,
     * @param message the message to be logged
     */
    public static void log(String message) {
        Log.d(TAG, message);
    }

    /**
     * Logs a message on the console with formatting
     * @param format a format string of the message to be logged
     * @param args arguments for the format string
     */
    public static void logf(String format, Object ... args) {
        log(String.format(format, args));
    }

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

package br.inf.combustivelemconta.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarUtils {

    public static void showLoggingError(Context context, String provider) {
        Snackbar.make(((Activity) context).findViewById(android.R.id.content),
                "Error logging on " + provider + ".", Snackbar.LENGTH_LONG).show();
    }

    public static void showMessage(Context context, String message) {
        Snackbar.make(((Activity) context).findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }
}

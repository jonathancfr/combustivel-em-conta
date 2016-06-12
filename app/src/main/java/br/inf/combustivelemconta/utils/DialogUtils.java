package br.inf.combustivelemconta.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import br.inf.combustivelemconta.R;

public class DialogUtils {
    private static MaterialDialog mDialog;

    public static void showLoadingDialog(Context context) {
        mDialog = new MaterialDialog.Builder(context)
                .title(R.string.login_dialog_title)
                .content(R.string.login_dialog_content)
                .progress(true, 0)
                .widgetColor(context.getResources().getColor(R.color.colorPrimary))
                .show();
    }

    public static void dismissLoadingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}

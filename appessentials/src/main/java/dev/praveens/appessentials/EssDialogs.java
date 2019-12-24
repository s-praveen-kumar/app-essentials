/*
 * Copyright (c) 2019. Praveen Kumar S
 */

package dev.praveens.appessentials;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public class EssDialogs {
    private static AlertDialog.Builder createDialog(Context context, String title, String msg) {
        return new AlertDialog.Builder(context).setTitle(title).setMessage(msg);
    }

    private static AlertDialog.Builder createDialog(Context context, @StringRes int titleId, @StringRes int msgId) {
        return new AlertDialog.Builder(context).setTitle(titleId).setMessage(msgId);
    }

    public static void infoDialog(Context context, String title, String info) {
        createDialog(context, title, info).setPositiveButton(android.R.string.ok, null).create().show();
    }

    public static void infoDialog(Context context, @StringRes int titleId, @StringRes int infoId) {
        createDialog(context, titleId, infoId).setPositiveButton(android.R.string.ok, null).create().show();
    }

    public static void infoDialog(Context context, String title, String info, String buttonText) {
        createDialog(context, title, info).setPositiveButton(buttonText, null).create().show();
    }

    public static void infoDialog(Context context, @StringRes int titleId, @StringRes int infoId, @StringRes int buttonTextId) {
        createDialog(context, titleId, infoId).setPositiveButton(buttonTextId, null).create().show();
    }

    public static void yesNoDialog(Context context, String title, String msg, @NonNull final YesNoCallback callback) {
        DialogInterface.OnClickListener listener = (dialog, which) -> callback.onResult(which == AlertDialog.BUTTON_POSITIVE);
        createDialog(context, title, msg).setPositiveButton(android.R.string.yes, listener)
                .setNegativeButton(android.R.string.no, listener).create().show();
    }

    public static void yesNoDialog(Context context, @StringRes int titleId, @StringRes int msgId, @NonNull final YesNoCallback callback) {
        DialogInterface.OnClickListener listener = (dialog, which) -> callback.onResult(which == AlertDialog.BUTTON_POSITIVE);
        createDialog(context, titleId, msgId).setPositiveButton(android.R.string.yes, listener)
                .setNegativeButton(android.R.string.no, listener).create().show();
    }

    public static void yesNoDialog(Context context, String title, String msg, String yesText, String noText, @NonNull final YesNoCallback callback) {
        DialogInterface.OnClickListener listener = (dialog, which) -> callback.onResult(which == AlertDialog.BUTTON_POSITIVE);
        createDialog(context, title, msg).setPositiveButton(yesText, listener)
                .setNegativeButton(noText, listener).create().show();
    }

    public static void yesNoDialog(Context context, String title, String msg, @StringRes int yesTextId, @StringRes int noTextId, @NonNull final YesNoCallback callback) {
        DialogInterface.OnClickListener listener = (dialog, which) -> callback.onResult(which == AlertDialog.BUTTON_POSITIVE);
        createDialog(context, title, msg).setPositiveButton(yesTextId, listener)
                .setNegativeButton(noTextId, listener).create().show();
    }

    interface YesNoCallback {
        void onResult(boolean yes);
    }
}

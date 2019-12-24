/*
 * Copyright (c) 2019. Praveen Kumar S
 */

package dev.praveens.appessentials;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        createDialog(context, title, msg).setCancelable(false).setPositiveButton(android.R.string.yes, listener)
                .setNegativeButton(android.R.string.no, listener).create().show();
    }

    public static void yesNoDialog(Context context, @StringRes int titleId, @StringRes int msgId, @NonNull final YesNoCallback callback) {
        DialogInterface.OnClickListener listener = (dialog, which) -> callback.onResult(which == AlertDialog.BUTTON_POSITIVE);
        createDialog(context, titleId, msgId).setCancelable(false).setPositiveButton(android.R.string.yes, listener)
                .setNegativeButton(android.R.string.no, listener).create().show();
    }

    public static void yesNoDialog(Context context, String title, String msg, String yesText, String noText, @NonNull final YesNoCallback callback) {
        DialogInterface.OnClickListener listener = (dialog, which) -> callback.onResult(which == AlertDialog.BUTTON_POSITIVE);
        createDialog(context, title, msg).setCancelable(false).setPositiveButton(yesText, listener)
                .setNegativeButton(noText, listener).create().show();
    }

    public static void yesNoDialog(Context context, @StringRes int titleId, @StringRes int msgId, @StringRes int yesTextId, @StringRes int noTextId, @NonNull final YesNoCallback callback) {
        DialogInterface.OnClickListener listener = (dialog, which) -> callback.onResult(which == AlertDialog.BUTTON_POSITIVE);
        createDialog(context, titleId, msgId).setCancelable(false).setPositiveButton(yesTextId, listener)
                .setNegativeButton(noTextId, listener).create().show();
    }

    public static void singleChoiceDialog(Context context, String title, @Nullable String msg, String[] entries, @NonNull final SingleItemCallback callback) {
        final int[] selected = {0};
        DialogInterface.OnClickListener listener = (dialog, which) -> {
            if (which == AlertDialog.BUTTON_POSITIVE)
                callback.onItemSelected(selected[0], entries[selected[0]]);
            else if (which == AlertDialog.BUTTON_NEGATIVE)
                callback.onCanceled();
            else
                selected[0] = which;
        };
        createDialog(context, title, msg).setSingleChoiceItems(entries, 0, (listener))
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel, listener)
                .setOnCancelListener(dialog -> callback.onCanceled()).create().show();
    }

    public static void singleChoiceDialog(Context context, @StringRes int titleId, @StringRes int msgId, @ArrayRes int entries, @NonNull final SingleItemCallback callback) {
        final int[] selected = {0};
        DialogInterface.OnClickListener listener = (dialog, which) -> {
            if (which == AlertDialog.BUTTON_POSITIVE)
                callback.onItemSelected(selected[0], context.getResources().getStringArray(entries)[selected[0]]);
            else if (which == AlertDialog.BUTTON_NEGATIVE)
                callback.onCanceled();
            else
                selected[0] = which;
        };
        createDialog(context, titleId, msgId).setSingleChoiceItems(entries, 0, (listener))
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel, listener)
                .setOnCancelListener(dialog -> callback.onCanceled()).create().show();
    }

    public static void multipleChoiceDialog(Context context, String title, @Nullable String msg, String[] entries, final boolean[] checked, @NonNull final MultipleItemCallback callback) {
        DialogInterface.OnClickListener listener = (dialog, which) -> {
            if (which == AlertDialog.BUTTON_POSITIVE)
                callback.onSelected(checked);
            else if (which == AlertDialog.BUTTON_NEGATIVE)
                callback.onCanceled();
        };
        createDialog(context, title, msg).setMultiChoiceItems(entries, checked, (dialog, which, isChecked) -> checked[which] = isChecked)
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel, listener)
                .setOnCancelListener(dialog -> callback.onCanceled()).create().show();
    }

    public static void multipleChoiceDialog(Context context, @StringRes int titleId, @StringRes int msgId, @ArrayRes int entries, final boolean[] checked, @NonNull final MultipleItemCallback callback) {
        DialogInterface.OnClickListener listener = (dialog, which) -> {
            if (which == AlertDialog.BUTTON_POSITIVE)
                callback.onSelected(checked);
            else if (which == AlertDialog.BUTTON_NEGATIVE)
                callback.onCanceled();
        };
        createDialog(context, titleId, msgId).setMultiChoiceItems(entries, checked, (dialog, which, isChecked) -> checked[which] = isChecked)
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel, listener)
                .setOnCancelListener(dialog -> callback.onCanceled()).create().show();
    }


    interface YesNoCallback {
        void onResult(boolean yes);
    }

    interface SingleItemCallback {
        void onItemSelected(int pos, String entry);

        void onCanceled();
    }

    interface MultipleItemCallback {
        void onSelected(boolean[] checked);

        void onCanceled();
    }
}

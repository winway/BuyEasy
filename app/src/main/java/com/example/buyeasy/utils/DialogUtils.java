package com.example.buyeasy.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @PackageName: com.example.buyeasy.utils
 * @ClassName: DialogUtils
 * @Author: winwa
 * @Date: 2023/5/3 8:44
 * @Description:
 **/
public class DialogUtils {

    public static void showCommonAlertDialog(Context context, String title, String msg,
                                             String negativeText, DialogInterface.OnClickListener negativeListener,
                                             String positiveText, DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(msg)
                .setNegativeButton(negativeText, negativeListener)
                .setPositiveButton(positiveText, positiveListener);
        builder.create().show();
    }
}

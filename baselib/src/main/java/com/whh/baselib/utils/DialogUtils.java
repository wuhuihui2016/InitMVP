package com.whh.baselib.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.whh.baselib.R;

/**
 * 自定义美观的AlertDialog
 */
public class DialogUtils {

    private static TextView msg;
    private static LinearLayout noLayout;
    private static Button ok, no;

    /**
     * 初始化dialog
     * 界面弹框只弹一个，多个弹框时仅显示第一个
     *
     * @param activity
     * @return
     */
    public static AlertDialog initDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        AlertDialog dialog = builder.show();
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_message);

        WindowManager manager = activity.getWindowManager();
        Window window = dialog.getWindow();
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        window.setLayout(width / 5 * 4, WindowManager.LayoutParams.WRAP_CONTENT);//设置宽度为屏幕的5/4，高度自适应

        msg = (TextView) dialog.findViewById(R.id.msg);
        noLayout = (LinearLayout) dialog.findViewById(R.id.noLayout);
        ok = (Button) dialog.findViewById(R.id.ok);
        no = (Button) dialog.findViewById(R.id.no);

        return dialog;
    }

    /**
     * 用于显示必要的提示说明，没有实际点击操作(点击后关闭)
     *
     * @param activity
     * @param message
     * @return
     */
    public static AlertDialog showMsgDialog(Activity activity, String message) {
        final AlertDialog dialog = initDialog(activity);
        msg.setText(message);
        if (message.length() > 12) msg.setGravity(Gravity.LEFT);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return dialog;
    }

    /**
     * 用于显示必要的提示说明，单选操作（确定按钮）
     *
     * @param activity
     * @param message         提示说明
     * @param comfireListener 确定按钮
     * @return
     */
    public static AlertDialog showMsgDialog(Activity activity, String message, final DialogListener comfireListener) {
        final AlertDialog dialog = initDialog(activity);
        msg.setText(message);
        if (message.length() > 12) msg.setGravity(Gravity.LEFT);
        if (comfireListener != null) {
            noLayout.setVisibility(View.VISIBLE);
            ok.setBackgroundResource(R.drawable.dialog_right_btn_bkg);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (comfireListener != null) {
                        comfireListener.onClick();
                    }
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        return dialog;
    }

    /**
     * 用于显示必要的提示说明，单选操作（确定按钮）
     *
     * @param activity
     * @param message         提示说明
     * @param isCancel        是否可以点击取消键
     * @param comfireListener 确定按钮
     * @return
     */
    public static AlertDialog showMsgDialog(Activity activity, String message, boolean isCancel, final DialogListener comfireListener) {
        final AlertDialog dialog = initDialog(activity);
        dialog.setCancelable(isCancel);
        msg.setText(message);
        if (message.length() > 12) msg.setGravity(Gravity.LEFT);
        if (comfireListener != null) {
            noLayout.setVisibility(View.VISIBLE);
            ok.setBackgroundResource(R.drawable.dialog_right_btn_bkg);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (comfireListener != null) {
                        comfireListener.onClick();
                    }
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        return dialog;
    }

    /**
     * 用于显示必要的提示说明，单选操作（确定按钮，确定按钮的名称）
     *
     * @param activity
     * @param okTitle         确定按钮的名称
     * @param message         提示说明
     * @param comfireListener 确定按钮
     * @return
     */
    public static AlertDialog showMsgDialog(Activity activity, String message, String okTitle, final DialogListener comfireListener) {
        final AlertDialog dialog = initDialog(activity);
        msg.setText(message);
        if (message.length() > 12) msg.setGravity(Gravity.LEFT);
        if (comfireListener != null) {
            noLayout.setVisibility(View.VISIBLE);
            ok.setBackgroundResource(R.drawable.dialog_right_btn_bkg);
            ok.setText(okTitle);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (comfireListener != null) {
                        comfireListener.onClick();
                    }
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        return dialog;
    }

    /**
     * 用于显示必要的提示说明，双选操作（确定按钮，确定按钮的名称；取消按钮，取消按钮的名称）
     *
     * @param activity
     * @param okTitle         确定按钮的名称
     * @param noTitle         取消按钮的名称
     * @param message         提示说明
     * @param comfireListener 确定按钮
     * @param cancelListener  取消按钮
     * @return
     */
    public static AlertDialog showMsgDialog(Activity activity, String message,
                                            String okTitle, final DialogListener comfireListener,
                                            String noTitle, final DialogListener cancelListener) {
        final AlertDialog dialog = initDialog(activity);
        msg.setText(message);
        if (message.length() > 12) msg.setGravity(Gravity.LEFT);
        if (comfireListener != null) {
            noLayout.setVisibility(View.VISIBLE);
            ok.setBackgroundResource(R.drawable.dialog_right_btn_bkg);
            ok.setText(okTitle);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (comfireListener != null) {
                        comfireListener.onClick();
                    }
                }
            });

            no.setText(noTitle);
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (cancelListener != null) {
                        cancelListener.onClick();
                    }
                }
            });
        }
        return dialog;
    }

    /**
     * 用于显示必要的提示说明，双选操作（确定按钮，确定按钮的名称；取消按钮，取消按钮的名称）
     *
     * @param activity
     * @param okTitle         确定按钮的名称
     * @param noTitle         取消按钮的名称
     * @param message         提示说明
     * @param isCancel        是否可以点击取消键
     * @param comfireListener 确定按钮
     * @param cancelListener  取消按钮
     * @return
     */
    public static AlertDialog showMsgDialog(Activity activity, String message, boolean isCancel,
                                            String okTitle, final DialogListener comfireListener,
                                            String noTitle, final DialogListener cancelListener) {
        final AlertDialog dialog = initDialog(activity);
        dialog.setCancelable(isCancel);
        msg.setText(message);
        if (message.length() > 12) msg.setGravity(Gravity.LEFT);
        if (comfireListener != null) {
            noLayout.setVisibility(View.VISIBLE);
            ok.setBackgroundResource(R.drawable.dialog_right_btn_bkg);
            ok.setText(okTitle);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (comfireListener != null) {
                        comfireListener.onClick();
                    }
                }
            });

            no.setText(noTitle);
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (cancelListener != null) {
                        cancelListener.onClick();
                    }
                }
            });
        }
        return dialog;
    }

}

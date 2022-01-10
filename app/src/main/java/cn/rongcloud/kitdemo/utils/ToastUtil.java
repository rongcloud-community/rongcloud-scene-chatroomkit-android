package cn.rongcloud.kitdemo.utils;

import android.widget.Toast;

import cn.rongcloud.kitdemo.MyApplication;

/**
 * Created by gyn on 2021/12/1
 */
public class ToastUtil {
    public static void show(String msg) {
        if (msg == null) {
            return;
        }
        Toast.makeText(MyApplication.app, msg, Toast.LENGTH_SHORT).show();
    }
}

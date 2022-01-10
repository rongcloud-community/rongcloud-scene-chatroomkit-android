package cn.rongcloud.kitdemo;

import android.app.Application;

import cn.rongcloud.corekit.api.RCSceneKitEngine;
import cn.rongcloud.corekit.utils.VMLog;
import cn.rongcloud.voiceroom.api.RCVoiceRoomEngine;
import cn.rongcloud.voiceroom.api.callback.RCVoiceInitCallback;

/**
 * Created by gyn on 2021/11/15
 */
public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        long millis = System.currentTimeMillis();
        // 初始化 Kit，优先根据 appkey 从远端下载配置，不成功采用默认配置
        RCSceneKitEngine.getInstance().initWithAppKey(this, null);
        VMLog.e(TAG, "init kit cost time: " + (System.currentTimeMillis() - millis));
        // demo 的依赖初始化
        // 初始化语聊房SDK
        RCVoiceRoomEngine.getInstance().initWithAppKey(this, "kj7swf8ok3052", new RCVoiceInitCallback() {
            @Override
            public void onInitComplete() {

            }
        });
    }

}

package cn.rongcloud.kitdemo;

import android.app.Application;

import java.util.Arrays;

import cn.rongcloud.chatroomkit.RCChatRoomKit;
import cn.rongcloud.corekit.api.RCSceneKitEngine;
import cn.rongcloud.kitdemo.chatroomkit.RCChatroomGift;
import io.rong.imlib.RongIMClient;

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

        // 初始化 Kit，优先根据 appkey 从远端下载配置，不成功采用默认配置，目前没有远端服务，appkey可传任意非空字符串
        RCSceneKitEngine.getInstance().initWithAppKey(this, "appkey");
        // 初始化chatroomkit
        RCChatRoomKit.getInstance().init(this);


        // demo 的依赖初始化
        // 初始化im
        RongIMClient.init(this, "kj7swf8ok3052");
        // 注册自定义消息
        RongIMClient.registerMessageType(Arrays.asList(new Class[]{RCChatroomGift.class}));
        // 连接融云服务,获取im用户token后连接融云服务
        // RongIMClient.connect("token", new RongIMClient.ConnectCallback() {
        //     @Override
        //     public void onSuccess(String t) {
        //
        //     }
        //
        //     @Override
        //     public void onError(RongIMClient.ConnectionErrorCode e) {
        //
        //     }
        //
        //     @Override
        //     public void onDatabaseOpened(RongIMClient.DatabaseOpenStatus code) {
        //
        //     }
        // });
    }

}

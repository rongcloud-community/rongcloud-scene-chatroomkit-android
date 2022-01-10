package cn.rongcloud.kitdemo.chatroomkit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.rongcloud.chatroomkit.api.OnMessageContentClickListener;
import cn.rongcloud.chatroomkit.bean.ActionButton;
import cn.rongcloud.chatroomkit.manager.AudioRecordManager;
import cn.rongcloud.chatroomkit.widget.ChatRoomView;
import cn.rongcloud.chatroomkit.widget.InputBar;
import cn.rongcloud.chatroomkit.widget.ToolBar;
import cn.rongcloud.kitdemo.R;

/**
 * Created by gyn on 2021/11/23
 */
public class ChatRoomKitActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ChatRoomKitActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroomkit);

        ChatRoomView chatRoomView = findViewById(R.id.chat_room_view);
        // 设置InputBar监听
        chatRoomView.setInputBarListener(new InputBar.InputBarListener() {
            @Override
            public void onClickSend(String message) {
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                // 这里可以把发送的消息主动添加到消息列表
                chatRoomView.addMessage(new RCChatroomMessage("1234", "小明", message));
            }

            @Override
            public boolean onClickEmoji() {
                // return true 拦截内部的emoji点击事件，可以自己实现弹出emoji选择器
                // return false 不拦截，默认采用内部的emoji选择器
                return false;
            }
        });
        // 设置消息富文本点击
        chatRoomView.setOnMessageContentClickListener(new OnMessageContentClickListener() {
            @Override
            public void onClickMessageContent(Object clickParam) {
                Toast.makeText(getBaseContext(), clickParam.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // 设置录音监听
        chatRoomView.setOnAudioRecordListener(new AudioRecordManager.OnAudioRecordListener() {
            @Override
            public boolean onRecordStart() {
                // return true 拦截录制，false 不拦截，可以用来做是否可用录音的前置判断，如麦克风被占用，则点击后不触发录音
                return false;
            }

            @Override
            public void onRecordCanceled() {

            }

            @Override
            public void onFinished(File file, long duration) {
                // TODO 上传语音到服务器，uploading。。。。

                //  成功后发送消息到消息区
                RCChatroomVoice chatroomVoice = new RCChatroomVoice(file, duration, "小光", "123454");
                chatRoomView.addMessage(chatroomVoice);
            }
        });
        // 设置ToolBar底部按钮监听
        chatRoomView.setToolbarActionListener(new ToolBar.OnActionClickListener() {
            @Override
            public void onClickAction(int index, String extra) {
                Toast.makeText(getBaseContext(), "index: " + index + " ,extra: " + extra, Toast.LENGTH_SHORT).show();

                if (index == 0) {
                    RCChatroomGift chatroomGift = new RCChatroomGift();
                    chatroomGift.setGiftId("1");
                    chatroomGift.setGiftName("小心心");
                    chatroomGift.setNumber("99");
                    chatroomGift.setUserId("123");
                    chatroomGift.setUserName("小明");
                    chatroomGift.setTargetId("456");
                    chatroomGift.setTargetName("小红");
                    chatRoomView.addMessage(chatroomGift);
                }
            }
        });
        // 手动配置ToolBar底部按钮
        List<ActionButton> actionButtonList = new ArrayList<>();

        ActionButton actionButton0 = new ActionButton();
        actionButton0.setLocalIcon(R.drawable.ic_send_gift);
        actionButton0.setHasBadge(false);
        actionButtonList.add(actionButton0);

        ActionButton actionButton1 = new ActionButton();
        actionButton1.setLocalIcon(R.drawable.ic_message);
        actionButton1.setHasBadge(true);
        actionButton1.setBadgeNum(5);
        actionButtonList.add(actionButton1);

        ActionButton actionButton2 = new ActionButton();
        actionButton2.setLocalIcon(R.drawable.rckit_ic_setting);
        actionButton2.setHasBadge(false);
        actionButtonList.add(actionButton2);

        chatRoomView.setToolbarActionButtons(actionButtonList);
    }
}

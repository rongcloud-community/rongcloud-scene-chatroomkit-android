package cn.rongcloud.chatroomkit.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import java.util.List;

import cn.rongcloud.chatroomkit.R;
import cn.rongcloud.chatroomkit.RCChatRoomKit;
import cn.rongcloud.chatroomkit.api.IRCChatroomMessage;
import cn.rongcloud.chatroomkit.api.OnMessageContentClickListener;
import cn.rongcloud.chatroomkit.bean.ActionButton;
import cn.rongcloud.chatroomkit.bean.ChatRoomKitConfig;
import cn.rongcloud.chatroomkit.manager.AudioRecordManager;
import cn.rongcloud.corekit.base.RCLinearLayout;
import cn.rongcloud.corekit.core.RCKitInit;

/**
 * Created by gyn on 2021/11/17
 */
public class ChatRoomView extends RCLinearLayout<ChatRoomKitConfig> {
    private ToolBar toolbar;
    private InputBarDialog inputBarDialog;
    private MessageView messageView;
    private InputBar.InputBarListener inputBarListener;

    public ChatRoomView(Context context) {
        super(context);
    }

    public ChatRoomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public int setLayoutId() {
        return R.layout.rckit_chat_room;
    }

    @Override
    public RCKitInit<ChatRoomKitConfig> getKitInstance() {
        return RCChatRoomKit.getInstance();
    }

    @Override
    public void initView() {
        this.setOrientation(VERTICAL);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        toolbar.setOnClickChatButton(v -> {
            inputBarDialog = new InputBarDialog(getContext(), inputBarListener);
            inputBarDialog.show();
        });
        messageView = (MessageView) findViewById(R.id.message_view);
    }

    @Override
    public void initConfig(ChatRoomKitConfig chatRoomKitConfig) {

    }

    /**
     * 设置消息数据，会替换原有数据
     *
     * @param list 数据集合
     */
    public void setMessages(List<IRCChatroomMessage> list) {
        messageView.setMessages(list);
    }

    /**
     * 添加消息，添加消息到最后一条
     *
     * @param message
     */
    public void addMessage(IRCChatroomMessage message) {
        messageView.addMessage(message);
    }

    /**
     * 添加消息数据，添加到最后
     *
     * @param list 数据集合
     */
    public void addMessages(List<IRCChatroomMessage> list) {
        messageView.addMessages(list);
    }

    /**
     * 设置底部功能键点击事件
     *
     * @param onActionClickListener 事件
     */
    public void setToolbarActionListener(ToolBar.OnActionClickListener onActionClickListener) {
        toolbar.setOnActionClickListener(onActionClickListener);
    }

    /**
     * 设置底部输入框的监听
     *
     * @param inputBarListener 监听
     */
    public void setInputBarListener(InputBar.InputBarListener inputBarListener) {
        this.inputBarListener = inputBarListener;
    }

    /**
     * 消息内容点击事件的监听
     *
     * @param onMessageContentClickListener 监听
     */
    public void setOnMessageContentClickListener(OnMessageContentClickListener onMessageContentClickListener) {
        if (messageView != null) {
            messageView.setOnMessageContentClickListener(onMessageContentClickListener);
        }
    }

    /**
     * 设置录音监听
     *
     * @param onAudioRecordListener
     */
    public void setOnAudioRecordListener(AudioRecordManager.OnAudioRecordListener onAudioRecordListener) {
        if (toolbar != null) {
            toolbar.setOnRecordVoiceListener(onAudioRecordListener);
        }
    }

    /**
     * @return 返回底部集合按钮
     */
    public List<ActionButton> getToolbarActionButtons() {
        if (toolbar != null) {
            return toolbar.getActionButtons();
        } else {
            return null;
        }
    }

    /**
     * 设置底部按钮集合
     *
     * @param actionButtonList 按钮集合
     */
    public void setToolbarActionButtons(List<ActionButton> actionButtonList) {
        if (toolbar != null) {
            toolbar.setActionButtons(actionButtonList);
        }
    }

    /**
     * @return 返回toolbar
     */
    public ToolBar getToolbar() {
        return toolbar;
    }

    /**
     * @return 返回MessageView
     */
    public MessageView getMessageView() {
        return messageView;
    }
}

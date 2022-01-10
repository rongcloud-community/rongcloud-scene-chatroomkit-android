package cn.rongcloud.kitdemo.chatroomkit;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.rongcloud.chatroomkit.api.IRCChatroomVoiceMessage;
import cn.rongcloud.chatroomkit.api.OnMessageContentClickListener;
import cn.rongcloud.chatroomkit.bean.MessageItem;
import cn.rongcloud.chatroomkit.utils.MessageContentUtils;
import cn.rongcloud.corekit.bean.RCColor;
import cn.rongcloud.corekit.bean.RCCorner;

/**
 * Created by gyn on 2021/11/19
 */
public class RCChatroomVoice implements IRCChatroomVoiceMessage {
    /**
     * 语音文件（或文件路径）
     */
    private File file;
    /**
     * 语音时长
     */
    private long duration;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户 id
     */
    private String userId;
    private SpannableStringBuilder spannableStringBuilder;

    public RCChatroomVoice(File file, long duration, String userName, String userId) {
        this.file = file;
        this.duration = duration;
        this.userName = userName;
        this.userId = userId;
    }

    @Override
    public RCColor bubbleColor() {
        return null;
    }

    @Override
    public RCColor bubbleTextColor() {
        return null;
    }

    @Override
    public RCCorner bubbleCorner() {
        return null;
    }

    /**
     * 构建消息体，参考富文本消息
     *
     * @param clickSpan
     * @return
     */
    @Override
    public SpannableStringBuilder buildMessage(OnMessageContentClickListener clickSpan) {
        if (spannableStringBuilder == null) {
            List<MessageItem> messageItemList = new ArrayList<>();
            messageItemList.add(new MessageItem<>(new SpannableString(userName), userId, true, Color.parseColor("#99FF00FF")));
            messageItemList.add(new MessageItem<>(new SpannableString(": ")));
            spannableStringBuilder = MessageContentUtils.buildMessage(messageItemList, clickSpan);
        }
        return spannableStringBuilder;
    }

    /**
     * 返回语音文件路径，本地或网络
     *
     * @return
     */
    @Override
    public String voicePath() {
        return file.getAbsolutePath();
    }

    /**
     * 返回语音时长
     *
     * @return
     */
    @Override
    public long voiceDuration() {
        return duration;
    }
}

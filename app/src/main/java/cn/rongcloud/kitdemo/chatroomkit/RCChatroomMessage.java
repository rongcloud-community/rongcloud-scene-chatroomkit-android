package cn.rongcloud.kitdemo.chatroomkit;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import cn.rongcloud.chatroomkit.api.IRCChatroomMessage;
import cn.rongcloud.chatroomkit.api.OnMessageContentClickListener;
import cn.rongcloud.chatroomkit.bean.MessageItem;
import cn.rongcloud.chatroomkit.utils.MessageContentUtils;
import cn.rongcloud.chatroomkit.widget.CenterAlignImageSpan;
import cn.rongcloud.corekit.bean.RCColor;
import cn.rongcloud.corekit.bean.RCCorner;
import cn.rongcloud.corekit.utils.UiUtils;
import cn.rongcloud.kitdemo.MyApplication;
import cn.rongcloud.kitdemo.R;

/**
 * Created by gyn on 2021/11/19
 */
public class RCChatroomMessage implements IRCChatroomMessage {
    /**
     * 消息发送者的 id
     */
    private String userId;

    /**
     * 消息发送者的名称
     */
    private String userName;

    /**
     * 消息内容
     */
    private String message;
    /**
     * 消息体富文本
     */
    private SpannableStringBuilder spannableStringBuilder;

    /**
     * 构造方法
     *
     * @param message 消息
     */
    public RCChatroomMessage(String userId, String userName, String message) {
        this.userId = userId;
        this.userName = userName;
        this.message = message;
    }

    /**
     * @return 返回气泡背景颜色，返回 null 采用默认配置
     */
    @Override
    public RCColor bubbleColor() {
        return new RCColor(0.5f, 100, 100, 100);
    }

    /**
     * @return 返回气泡文字颜色，返回 null 采用默认配置
     */
    @Override
    public RCColor bubbleTextColor() {
        return new RCColor(0.7f, 255, 255, 255);
    }

    /**
     * @return 返回气泡圆角，返回 null 采用默认配置
     */
    @Override
    public RCCorner bubbleCorner() {
        return new RCCorner(0, 15, 15, 15);
    }

    /**
     * 构建消息体富文本
     * {@link MessageItem} 是消息体富文本中的最小可配置单元，
     * 可单独配置某几个文字的点击事件和文字颜色，不设置采用 {@link #bubbleTextColor()} 的颜色
     * 可以创建一个 MessageItem 集合，
     * 调用 {@link MessageContentUtils#buildMessage(List, OnMessageContentClickListener)}方法构建消息体富文本
     * 可参考以下示例构建一条富文本，例如："😈小明说：大家好"，其中 "小明说：" 可点击，点击后返回 userId
     *
     * @param clickSpan 回传的点击事件监听
     * @return 返回消息体富文本
     */
    @Override
    public SpannableStringBuilder buildMessage(OnMessageContentClickListener clickSpan) {
        if (spannableStringBuilder == null) {
            // MessageItem 集合
            List<MessageItem> messageItemList = new ArrayList<>();
            // 可以添加带icon的富文本
            SpannableString iconString = new SpannableString("  ");
            Drawable drawable = ContextCompat.getDrawable(MyApplication.app, R.drawable.ic_creator);
            int iconSize = UiUtils.dp2px(11);
            drawable.setBounds(0, 0, iconSize, iconSize);
            iconString.setSpan(new CenterAlignImageSpan(drawable), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            messageItemList.add(new MessageItem<>(iconString));
            // 可点击的用户名
            messageItemList.add(new MessageItem(new SpannableString(userName + "说："), userId, true, Color.parseColor("#FF0000"), true));
            // 普通文本
            messageItemList.add(new MessageItem<>(new SpannableString(message)));
            // 使用 MessageContentUtils.buildMessage 构建
            spannableStringBuilder = MessageContentUtils.buildMessage(messageItemList, clickSpan);
        }
        return spannableStringBuilder;
    }
}

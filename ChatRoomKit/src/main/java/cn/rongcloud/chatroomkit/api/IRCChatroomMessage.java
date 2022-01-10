package cn.rongcloud.chatroomkit.api;

import android.text.SpannableStringBuilder;

import cn.rongcloud.corekit.bean.RCColor;
import cn.rongcloud.corekit.bean.RCCorner;

/**
 * Created by gyn on 2021/11/18
 */
public interface IRCChatroomMessage {
    /**
     * 气泡背景色，返回null采用默认背景
     *
     * @return {@link RCColor}颜色
     */
    RCColor bubbleColor();

    /**
     * 气泡背景色，返回null采用默认背景
     *
     * @return {@link RCColor}颜色
     */
    RCColor bubbleTextColor();

    /**
     * 气泡圆角，返回null采用默认圆角
     *
     * @return {@link RCCorner}圆角
     */
    RCCorner bubbleCorner();

    /**
     * 消息内容富文本，可以在富文本中自定义点击事件
     *
     * @return 返回SpannableStringBuilder
     */
    SpannableStringBuilder buildMessage(OnMessageContentClickListener clickSpan);
}

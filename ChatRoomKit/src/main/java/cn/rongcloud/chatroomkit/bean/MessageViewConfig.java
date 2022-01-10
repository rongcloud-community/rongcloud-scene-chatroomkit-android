package cn.rongcloud.chatroomkit.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import cn.rongcloud.corekit.bean.RCColor;
import cn.rongcloud.corekit.bean.RCCorner;
import cn.rongcloud.corekit.bean.RCFont;
import cn.rongcloud.corekit.bean.RCInsets;
import cn.rongcloud.corekit.bean.RCNode;

/**
 * Created by gyn on 2021/11/16
 */
public class MessageViewConfig implements Serializable {

    @SerializedName("contentInsets")
    private RCNode<RCInsets> contentInsets;
    @SerializedName("maxVisibleCount")
    private RCNode<Integer> maxVisibleCount;
    @SerializedName("defaultBubbleColor")
    private RCNode<RCColor> defaultBubbleColor;
    @SerializedName("bubbleInsets")
    private RCNode<RCInsets> bubbleInsets;
    @SerializedName("defaultBubbleCorner")
    private RCNode<RCCorner> defaultBubbleCorner;
    @SerializedName("bubbleSpace")
    private RCNode<Integer> bubbleSpace;
    @SerializedName("defaultBubbleTextColor")
    private RCNode<RCColor> defaultBubbleTextColor;
    @SerializedName("defaultBubbleTextFont")
    private RCNode<RCFont> defaultBubbleTextFont;
    @SerializedName("voiceIconColor")
    private RCNode<RCColor> voiceIconColor;

    public RCInsets getContentInsets() {
        return contentInsets.getValue();
    }

    public Integer getMaxVisibleCount() {
        return maxVisibleCount.getValue();
    }

    public RCColor getDefaultBubbleColor() {
        return defaultBubbleColor.getValue();
    }

    public RCInsets getBubbleInsets() {
        return bubbleInsets.getValue();
    }

    public RCCorner getDefaultBubbleCorner() {
        return defaultBubbleCorner.getValue();
    }

    public Integer getBubbleSpace() {
        return bubbleSpace.getValue();
    }

    public RCColor getDefaultBubbleTextColor() {
        return defaultBubbleTextColor.getValue();
    }

    public RCColor getVoiceIconColor() {
        return voiceIconColor.getValue();
    }

    public RCFont getDefaultBubbleTextFont() {
        return defaultBubbleTextFont.getValue();
    }
}

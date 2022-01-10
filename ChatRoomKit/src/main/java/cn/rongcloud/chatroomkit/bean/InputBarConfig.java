package cn.rongcloud.chatroomkit.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import cn.rongcloud.corekit.bean.RCAttributes;
import cn.rongcloud.corekit.bean.RCColor;
import cn.rongcloud.corekit.bean.RCInsets;
import cn.rongcloud.corekit.bean.RCNode;

/**
 * Created by gyn on 2021/11/12
 */
public class InputBarConfig implements Serializable {
    @SerializedName("backgroundColor")
    private RCNode<RCColor> backgroundColor;
    @SerializedName("contentInsets")
    private RCNode<RCInsets> contentInsets;
    @SerializedName("inputTextMaxLength")
    private RCNode<Integer> inputTextMaxLength;
    @SerializedName("inputAttributes")
    private RCNode<RCAttributes> inputAttributes;
    @SerializedName("emojiEnable")
    private RCNode<Boolean> emojiEnable;

    public RCColor getBackgroundColor() {
        return backgroundColor.getValue();
    }

    public RCInsets getContentInsets() {
        return contentInsets.getValue();
    }

    public RCAttributes getInputAttributes() {
        return inputAttributes.getValue();
    }

    public Integer getInputTextMaxLength() {
        return inputTextMaxLength.getValue();
    }

    public Boolean getEmojiEnable() {
        return emojiEnable.getValue();
    }
}

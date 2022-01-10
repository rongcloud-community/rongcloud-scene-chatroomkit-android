package cn.rongcloud.chatroomkit.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import cn.rongcloud.corekit.bean.RCAttributes;
import cn.rongcloud.corekit.bean.RCColor;
import cn.rongcloud.corekit.bean.RCInsets;
import cn.rongcloud.corekit.bean.RCNode;
import cn.rongcloud.corekit.bean.RCRadio;

/**
 * Created by gyn on 2021/11/12
 */
public class ToolBarConfig implements Serializable {

    @SerializedName("backgroundColor")
    private RCNode<RCColor> backgroundColor;
    @SerializedName("contentInsets")
    private RCNode<RCInsets> contentInsets;
    @SerializedName("chatButtonAttributes")
    private RCNode<RCAttributes> chatButtonAttributes;
    @SerializedName("recordButtonEnable")
    private RCNode<Boolean> recordButtonEnable;
    @SerializedName("recordQuality")
    private RCNode<RCRadio<Integer>> recordQuality;
    @SerializedName("recordPosition")
    private RCNode<RCRadio<Integer>> recordPosition;
    @SerializedName("recordMaxDuration")
    private RCNode<Integer> recordMaxDuration;
    @SerializedName("buttonArray")
    private RCNode<List<ActionButton>> buttonArray;

    public RCColor getBackgroundColor() {
        return backgroundColor.getValue();
    }

    public RCInsets getContentInsets() {
        return contentInsets.getValue();
    }

    public Boolean getRecordButtonEnable() {
        return recordButtonEnable.getValue();
    }

    public Integer getRecordQuality() {
        return recordQuality.getValue().getSelected().getValue();
    }

    public Integer getRecordPosition() {
        return recordPosition.getValue().getSelected().getValue();
    }

    public Integer getRecordMaxDuration() {
        return recordMaxDuration.getValue();
    }

    public List<ActionButton> getButtonArray() {
        return buttonArray.getValue();
    }

    public RCAttributes getChatButtonAttributes() {
        return chatButtonAttributes.getValue();
    }
}

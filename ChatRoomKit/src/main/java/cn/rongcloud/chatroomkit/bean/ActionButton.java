package cn.rongcloud.chatroomkit.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import cn.rongcloud.corekit.bean.RCImage;
import cn.rongcloud.corekit.bean.RCNode;

public class ActionButton implements Serializable {
    @SerializedName("icon")
    private RCNode<RCImage> actionIcon;
    @SerializedName("localIcon")
    private int localIcon;
    @SerializedName("hasBadge")
    private RCNode<Boolean> hasBadge;
    @SerializedName("extra")
    private String extra;
    @SerializedName("badgeNum")
    private int badgeNum;

    public RCImage getActionIcon() {
        return actionIcon.getValue();
    }

    public void setActionIcon(RCImage actionIcon) {
        this.actionIcon.setValue(actionIcon);
    }

    public int getLocalIcon() {
        return localIcon;
    }

    public void setLocalIcon(int localIcon) {
        this.localIcon = localIcon;
    }

    public boolean hasBadge() {
        return hasBadge.getValue();
    }

    public void setHasBadge(boolean hasBadge) {
        if (this.hasBadge == null) {
            this.hasBadge = new RCNode<Boolean>();
        }
        this.hasBadge.setValue(hasBadge);
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getBadgeNum() {
        return badgeNum;
    }

    public void setBadgeNum(int badgeNum) {
        this.badgeNum = badgeNum;
    }
}
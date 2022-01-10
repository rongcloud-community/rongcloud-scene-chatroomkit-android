package cn.rongcloud.chatroomkit.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import cn.rongcloud.corekit.bean.RCNode;


/**
 * Created by gyn on 2021/12/8
 */
public class ChatRoomKitConfig implements Serializable {
    @SerializedName("messageView")
    private RCNode<MessageViewConfig> messageView;
    @SerializedName("toolBar")
    private RCNode<ToolBarConfig> toolBar;
    @SerializedName("inputBar")
    private RCNode<InputBarConfig> inputBar;

    public MessageViewConfig getMessageView() {
        return messageView.getValue();
    }

    public ToolBarConfig getToolBar() {
        return toolBar.getValue();
    }

    public InputBarConfig getInputBar() {
        return inputBar.getValue();
    }
}

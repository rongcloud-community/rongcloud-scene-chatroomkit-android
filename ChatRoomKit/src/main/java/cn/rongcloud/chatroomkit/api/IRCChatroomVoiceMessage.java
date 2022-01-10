package cn.rongcloud.chatroomkit.api;

/**
 * Created by gyn on 2021/11/18
 */
public interface IRCChatroomVoiceMessage extends IRCChatroomMessage {
    /**
     * @return 语音消息路径
     */
    String voicePath();

    /**
     * @return 语音消息时长
     */
    long voiceDuration();
}

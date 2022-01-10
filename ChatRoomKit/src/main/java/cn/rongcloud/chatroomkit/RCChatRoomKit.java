package cn.rongcloud.chatroomkit;

import android.content.Context;

import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.ios.IosEmojiProvider;

import java.io.File;

import cn.rongcloud.chatroomkit.bean.ChatRoomKitConfig;
import cn.rongcloud.corekit.core.RCKitInit;
import cn.rongcloud.corekit.utils.FileUtils;
import cn.rongcloud.corekit.utils.VMLog;

/**
 * Created by gyn on 2021/11/18
 */
public class RCChatRoomKit extends RCKitInit<ChatRoomKitConfig> {
    private static final String TAG = RCChatRoomKit.class.getSimpleName();

    private final static Holder holder = new Holder();

    private String voicePath;

    public static RCChatRoomKit getInstance() {
        return holder.instance;
    }

    private static class Holder {
        private RCChatRoomKit instance = new RCChatRoomKit();
    }

    @Override
    public void init(Context context) {
        super.init(context);
        EmojiManager.install(new IosEmojiProvider());
        setVoicePath(context.getFilesDir().getAbsolutePath() + "/rckit_voice");
        VMLog.d(TAG, TAG + " has init");
    }

    @Override
    public String getKitConfigName() {
        return "ChatRoomKit";
    }

    /**
     * @return 返回音频文件存储路径
     * 默认data/data/packname/files/rckit_voice
     */
    public String getVoicePath() {
        return voicePath;
    }

    /**
     * 设置音频文件存储路径
     *
     * @param voicePath
     */
    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
        File file = new File(voicePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 清除所有本地音频缓存
     */
    public void clearVoiceCache() {
        FileUtils.delAllFile(voicePath);
    }


}

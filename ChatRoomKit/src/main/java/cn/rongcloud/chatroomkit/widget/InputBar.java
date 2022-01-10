package cn.rongcloud.chatroomkit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiPopup;

import cn.rongcloud.chatroomkit.R;
import cn.rongcloud.chatroomkit.RCChatRoomKit;
import cn.rongcloud.chatroomkit.bean.ChatRoomKitConfig;
import cn.rongcloud.chatroomkit.bean.InputBarConfig;
import cn.rongcloud.corekit.base.RCLinearLayout;
import cn.rongcloud.corekit.core.RCKitInit;
import cn.rongcloud.corekit.utils.SoftKeyboardUtils;
import cn.rongcloud.corekit.utils.UiUtils;
import cn.rongcloud.corekit.utils.VMLog;


/**
 * Created by gyn on 2021/11/12
 */
public class InputBar extends RCLinearLayout<ChatRoomKitConfig> {
    private final static String TAG = InputBar.class.getSimpleName();
    private EmojiEditText etInput;
    private Space space1;
    private ImageView ivEmoji;
    private Space space2;
    private TextView tvSend;
    private InputBarListener inputBarListener;
    /**
     * emoji选择框
     */
    private EmojiPopup mEmojiPopup;

    public InputBar(Context context) {
        super(context);
    }

    public InputBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int setLayoutId() {
        return R.layout.rckit_inputbar;
    }

    @Override
    public RCKitInit<ChatRoomKitConfig> getKitInstance() {
        return RCChatRoomKit.getInstance();
    }

    @Override
    public void initView() {
        // init view
        this.setOrientation(HORIZONTAL);
        etInput = (EmojiEditText) findViewById(R.id.et_input);
        space1 = (Space) findViewById(R.id.space1);
        ivEmoji = (ImageView) findViewById(R.id.iv_emoji);
        space2 = (Space) findViewById(R.id.space2);
        tvSend = (TextView) findViewById(R.id.tv_send);

    }

    @Override
    public void initConfig(ChatRoomKitConfig chatRoomKitConfig) {
        InputBarConfig inputBarConfig = chatRoomKitConfig.getInputBar();
        if (inputBarConfig == null) {
            VMLog.e(TAG, "initView failed : inputBarBean is null");
            return;
        }
        // parent
        this.setBackgroundColor(inputBarConfig.getBackgroundColor().getColor());
        UiUtils.setPadding(this, inputBarConfig.getContentInsets());
        // input
        UiUtils.setTextAttributes(etInput, inputBarConfig.getInputAttributes());
        // emoji
        if (inputBarConfig.getEmojiEnable()) {
            space1.setVisibility(VISIBLE);
            ivEmoji.setVisibility(VISIBLE);
        } else {
            space1.setVisibility(GONE);
            ivEmoji.setVisibility(GONE);
        }
        mEmojiPopup = EmojiPopup
                .Builder
                .fromRootView(this)
                .setKeyboardAnimationStyle(R.style.emoji_fade_animation_style)
                .setOnEmojiPopupShownListener(() -> {
                    ivEmoji.setImageResource(R.drawable.rckit_ic_input);
                })
                .setOnEmojiPopupDismissListener(() -> {
                    ivEmoji.setImageResource(R.drawable.rckit_ic_emoji);
                }).build(etInput);
        ivEmoji.setOnClickListener(v -> {
            if (inputBarListener != null) {
                boolean intercept = inputBarListener.onClickEmoji();
                if (intercept) {

                } else {
                    mEmojiPopup.toggle();
                }
            }
        });
        tvSend.setOnClickListener(v -> {
            String message = "";
            if (etInput.getText() != null) {
                message = etInput.getText().toString().trim();
            }
            etInput.setText("");

            if (inputBarListener != null) {
                inputBarListener.onClickSend(message);
            }
        });
    }

    public void showInputBar() {
        this.setVisibility(VISIBLE);
        etInput.requestFocus();
        SoftKeyboardUtils.showSoftKeyboard(etInput, 200);
    }

    public void hideInputBar() {
        mEmojiPopup.dismiss();
        this.setVisibility(GONE);
        etInput.clearFocus();
        SoftKeyboardUtils.hideSoftKeyboard(etInput);
    }

    public void setInputBarListener(InputBarListener inputBarListener) {
        this.inputBarListener = inputBarListener;
    }

    public interface InputBarListener {

        void onClickSend(String message);

        boolean onClickEmoji();
    }
}

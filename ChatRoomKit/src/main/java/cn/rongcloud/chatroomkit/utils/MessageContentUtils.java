package cn.rongcloud.chatroomkit.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.List;

import cn.rongcloud.chatroomkit.api.OnMessageContentClickListener;
import cn.rongcloud.chatroomkit.bean.MessageItem;

/**
 * Created by gyn on 2021/11/18
 */
public class MessageContentUtils {

    public static SpannableStringBuilder buildMessage(List<MessageItem> messageItems, OnMessageContentClickListener onMessageContentClickListener) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        int start = 0;
        for (MessageItem messageItem : messageItems) {
            messageItem.setStart(start);
            start += messageItem.getText().length();
            messageItem.setEnd(start);
            builder.append(messageItem.getText());
            builder.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    if (messageItem.isClickable()) {
                        onMessageContentClickListener.onClickMessageContent(messageItem.getClickParam());
                    }
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(messageItem.hasUnderLine());
                    if (messageItem.getColor() != -1) {
                        ds.setColor(messageItem.getColor());
                    }
                }
            }, messageItem.getStart(), messageItem.getEnd(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }
}

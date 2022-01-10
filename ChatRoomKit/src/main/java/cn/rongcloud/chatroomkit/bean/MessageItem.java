package cn.rongcloud.chatroomkit.bean;

import android.text.SpannableString;

import java.io.Serializable;

/**
 * Created by gyn on 2021/11/18
 */
public class MessageItem<T> implements Serializable {
    private SpannableString text;
    private T clickParam;
    private boolean clickable;
    private int color = -1;
    private boolean hasUnderLine;
    private int start;
    private int end;

    public MessageItem() {
    }

    public MessageItem(SpannableString text) {
        this.text = text;
    }

    public MessageItem(SpannableString text, T clickParam, boolean clickable) {
        this.text = text;
        this.clickParam = clickParam;
        this.clickable = clickable;
    }

    public MessageItem(SpannableString text, T clickParam, boolean clickable, int color) {
        this.text = text;
        this.clickParam = clickParam;
        this.clickable = clickable;
        this.color = color;
    }

    public MessageItem(SpannableString text, T clickParam, boolean clickable, int color, boolean hasUnderLine) {
        this.text = text;
        this.clickParam = clickParam;
        this.clickable = clickable;
        this.color = color;
        this.hasUnderLine = hasUnderLine;
    }

    public SpannableString getText() {
        return text;
    }

    public void setText(SpannableString text) {
        this.text = text;
    }

    public T getClickParam() {
        return clickParam;
    }

    public void setClickParam(T clickParam) {
        this.clickParam = clickParam;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean hasUnderLine() {
        return hasUnderLine;
    }

    public void setHasUnderLine(boolean hasUnderLine) {
        this.hasUnderLine = hasUnderLine;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

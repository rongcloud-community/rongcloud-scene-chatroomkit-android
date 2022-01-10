/*
 * Copyright © 2021 RongCloud. All rights reserved.
 */

package cn.rongcloud.kitdemo.chatroomkit;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import cn.rongcloud.chatroomkit.api.IRCChatroomMessage;
import cn.rongcloud.chatroomkit.api.OnMessageContentClickListener;
import cn.rongcloud.chatroomkit.bean.MessageItem;
import cn.rongcloud.chatroomkit.utils.MessageContentUtils;
import cn.rongcloud.corekit.bean.RCColor;
import cn.rongcloud.corekit.bean.RCCorner;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;


/**
 * 通过消息发送
 * 人到人送礼物
 *
 * @author gyn
 * @Date 2021/06/17
 */
@MessageTag(value = "RC:Chatroom:Gift")
public class RCChatroomGift extends MessageContent implements IRCChatroomMessage {
    public static final Parcelable.Creator<RCChatroomGift> CREATOR = new Parcelable.Creator<RCChatroomGift>() {
        @Override
        public RCChatroomGift createFromParcel(Parcel source) {
            return new RCChatroomGift(source);
        }

        @Override
        public RCChatroomGift[] newArray(int size) {
            return new RCChatroomGift[size];
        }
    };
    private static final String TAG = "RCChatroomGift";
    private String userId;
    private String userName;
    private String targetId;
    private String targetName;
    private String giftId;
    private String giftName;
    private String number;
    private int price;
    private SpannableStringBuilder spannableStringBuilder = null;


    public RCChatroomGift(byte[] data) {
        super(data);
        String jsonStr = null;
        jsonStr = new String(data, StandardCharsets.UTF_8);

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            if (jsonObj.has("userId")) {
                userId = jsonObj.getString("userId");
            }
            if (jsonObj.has("userName")) {
                userName = jsonObj.getString("userName");
            }

            if (jsonObj.has("targetId")) {
                targetId = jsonObj.getString("targetId");
            }
            if (jsonObj.has("targetName")) {
                targetName = jsonObj.getString("targetName");
            }
            if (jsonObj.has("giftId")) {
                giftId = jsonObj.getString("giftId");
            }
            if (jsonObj.has("giftName")) {
                giftName = jsonObj.getString("giftName");
            }
            if (jsonObj.has("number")) {
                number = jsonObj.getString("number");
            }
            if (jsonObj.has("price")) {
                price = jsonObj.getInt("price");
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSONException " + e.getMessage());
        }
    }

    public RCChatroomGift() {
    }

    protected RCChatroomGift(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.targetId = in.readString();
        this.targetName = in.readString();
        this.giftId = in.readString();
        this.giftName = in.readString();
        this.number = in.readString();
        this.price = in.readInt();
    }

    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            if (!TextUtils.isEmpty(userId)) {
                jsonObj.put("userId", userId);
            }
            if (!TextUtils.isEmpty(userName)) {
                jsonObj.put("userName", userName);
            }

            if (!TextUtils.isEmpty(targetId)) {
                jsonObj.put("targetId", targetId);
            }
            if (!TextUtils.isEmpty(targetName)) {
                jsonObj.put("targetName", targetName);
            }
            if (!TextUtils.isEmpty(giftId)) {
                jsonObj.put("giftId", giftId);
            }
            if (!TextUtils.isEmpty(giftName)) {
                jsonObj.put("giftName", giftName);
            }
            if (!TextUtils.isEmpty(number)) {
                jsonObj.put("number", number);
            }
            jsonObj.put("price", price);
            return jsonObj.toString().getBytes(StandardCharsets.UTF_8);
        } catch (JSONException e) {
            Log.e(TAG, "JSONException " + e.getMessage());
        }
        return null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.targetId);
        dest.writeString(this.targetName);
        dest.writeString(this.giftId);
        dest.writeString(this.giftName);
        dest.writeString(this.number);
        dest.writeInt(this.price);
    }

    public void readFromParcel(Parcel source) {
        this.userId = source.readString();
        this.userName = source.readString();
        this.targetId = source.readString();
        this.targetName = source.readString();
        this.giftId = source.readString();
        this.giftName = source.readString();
        this.number = source.readString();
        this.price = source.readInt();
    }

    @Override
    public RCColor bubbleColor() {
        return new RCColor(0.5f, 232, 33, 14);
    }

    @Override
    public RCColor bubbleTextColor() {
        return new RCColor(0.8f, 232, 255, 0);
    }

    @Override
    public RCCorner bubbleCorner() {
        return null;
    }

    @Override
    public SpannableStringBuilder buildMessage(OnMessageContentClickListener clickSpan) {
        if (spannableStringBuilder == null) {
            // 例如：小明 送给 小红 小心心 x99
            // 小明 和 小红 文字可以点击
            List<MessageItem> messageItemList = new ArrayList<>();
            messageItemList.add(new MessageItem<>(new SpannableString(userName), userId, true, Color.parseColor("#0000FF")));
            messageItemList.add(new MessageItem<>(new SpannableString(" 送给 ")));
            messageItemList.add(new MessageItem<>(new SpannableString(targetName), targetId, true, Color.parseColor("#FF0000"), true));
            messageItemList.add(new MessageItem<>(new SpannableString(String.format(" %s x%s", giftName, number))));
            spannableStringBuilder = MessageContentUtils.buildMessage(messageItemList, clickSpan);
        }
        return spannableStringBuilder;
    }
}

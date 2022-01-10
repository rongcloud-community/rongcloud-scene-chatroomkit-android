package cn.rongcloud.chatroomkit.widget;

import android.Manifest;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.rongcloud.chatroomkit.R;
import cn.rongcloud.chatroomkit.RCChatRoomKit;
import cn.rongcloud.chatroomkit.bean.ActionButton;
import cn.rongcloud.chatroomkit.bean.ChatRoomKitConfig;
import cn.rongcloud.chatroomkit.bean.ToolBarConfig;
import cn.rongcloud.chatroomkit.manager.AudioPlayManager;
import cn.rongcloud.chatroomkit.manager.AudioRecordManager;
import cn.rongcloud.chatroomkit.utils.PermissionCheckUtil;
import cn.rongcloud.corekit.base.RCConstraintLayout;
import cn.rongcloud.corekit.core.RCKitInit;
import cn.rongcloud.corekit.utils.GlideUtil;
import cn.rongcloud.corekit.utils.UiUtils;
import cn.rongcloud.corekit.utils.VMLog;


/**
 * Created by gyn on 2021/11/12
 */
public class ToolBar extends RCConstraintLayout<ChatRoomKitConfig> {
    private final static String TAG = ToolBar.class.getSimpleName();
    private LinearLayout llChat;
    private TextView tvChat;
    private RecyclerView rvAction;
    private ImageView ivRecord;
    private AudioRecordManager audioRecordManager;
    private ToolBarConfig toolBarConfig;
    private OnActionClickListener onActionClickListener;
    private ActionAdapter actionAdapter;

    public ToolBar(Context context) {
        this(context, null);
    }

    public ToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public int setLayoutId() {
        return R.layout.rckit_toolbar;
    }

    @Override
    public RCKitInit<ChatRoomKitConfig> getKitInstance() {
        return RCChatRoomKit.getInstance();
    }

    @Override
    public void initView() {
        // init view
        llChat = (LinearLayout) findViewById(R.id.ll_chat);
        tvChat = (TextView) findViewById(R.id.tv_chat);
        rvAction = (RecyclerView) findViewById(R.id.rv_action);
        ivRecord = (ImageView) findViewById(R.id.iv_record);
        // action recyclerview
        actionAdapter = new ActionAdapter();
        rvAction.setAdapter(actionAdapter);
        setRecordOnTouchListener();
    }

    private void setRecordOnTouchListener() {
        ivRecord.setOnTouchListener((v, event) -> {
            String[] permissions = {Manifest.permission.RECORD_AUDIO};
            if (!PermissionCheckUtil.checkPermissions(
                    v.getContext(),
                    permissions
            ) && event.getAction() == MotionEvent.ACTION_DOWN
            ) {
                PermissionCheckUtil.requestPermissions(
                        ((FragmentActivity) v.getContext()),
                        permissions,
                        PermissionCheckUtil.REQUEST_CODE_ASK_PERMISSIONS
                );
                return true;
            }
            int[] location = new int[2];
            ivRecord.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //在这里拦截外部的滑动事件
                v.getParent().requestDisallowInterceptTouchEvent(true);
                if (AudioPlayManager.getInstance().isPlaying()) {
                    AudioPlayManager.getInstance().stopPlay();
                }

                audioRecordManager.startRecord(v.getRootView(), toolBarConfig.getRecordQuality() != 0);
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (event.getRawX() <= 0 || event.getRawX() > x + v.getWidth() || event.getRawY() < y) {
                    audioRecordManager.willCancelRecord();
                } else {
                    audioRecordManager.continueRecord();
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
                audioRecordManager.stopRecord();
            }
            return true;
        });
    }

    @Override
    public void initConfig(ChatRoomKitConfig chatRoomKitConfig) {
        toolBarConfig = chatRoomKitConfig.getToolBar();
        if (toolBarConfig == null) {
            VMLog.e(TAG, "initView failed : toolBarBean is null");
            return;
        }
        // content
        this.setBackgroundColor(toolBarConfig.getBackgroundColor().getColor());
        UiUtils.setPadding(this, toolBarConfig.getContentInsets());
        // chat button
        tvChat.setText(toolBarConfig.getChatButtonAttributes().getText());
        tvChat.setTextColor(toolBarConfig.getChatButtonAttributes().getTextColor().getColor());
        tvChat.setTextSize(toolBarConfig.getChatButtonAttributes().getFont().getSize());
        UiUtils.setViewSize(llChat, toolBarConfig.getChatButtonAttributes().getSize());
        UiUtils.setDrawable(llChat, toolBarConfig.getChatButtonAttributes().getDrawable());
        UiUtils.setPadding(llChat, toolBarConfig.getChatButtonAttributes().getInsets());

        // record image
        if (toolBarConfig.getRecordButtonEnable()) {
            ivRecord.setVisibility(VISIBLE);
        } else {
            ivRecord.setVisibility(GONE);
        }
        audioRecordManager = new AudioRecordManager();
        audioRecordManager.setMaxVoiceDuration(toolBarConfig.getRecordMaxDuration());
        if (toolBarConfig.getRecordPosition() != 0) {
            UiUtils.reverseChild(llChat);
        }

        actionAdapter.setActionList(toolBarConfig.getButtonArray());
    }

    public void setOnClickChatButton(View.OnClickListener l) {
        llChat.setOnClickListener(l);
    }

    public void setOnRecordVoiceListener(AudioRecordManager.OnAudioRecordListener onRecordVoiceListener) {
        if (audioRecordManager != null) {
            audioRecordManager.setOnAudioRecordListener(onRecordVoiceListener);
        }
    }

    public void setOnActionClickListener(OnActionClickListener onActionClickListener) {
        this.onActionClickListener = onActionClickListener;
    }

    public List<ActionButton> getActionButtons() {
        if (toolBarConfig != null) {
            return toolBarConfig.getButtonArray();
        } else {
            return null;
        }
    }

    public void setActionButtons(List<ActionButton> actionButtonList) {
        if (actionAdapter != null) {
            actionAdapter.setActionList(actionButtonList);
        }
    }

    public interface OnActionClickListener {
        void onClickAction(int index, String extra);
    }

    private class ActionAdapter extends RecyclerView.Adapter<ActionViewHolder> {
        private List<ActionButton> actionButtonList = new ArrayList<>();


        public void setActionList(List<ActionButton> actionButtonList) {
            this.actionButtonList = actionButtonList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rckit_item_action, parent, false);
            return new ActionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ActionViewHolder holder, int position) {
            holder.setActionData(actionButtonList.get(position));
            holder.itemView.setOnClickListener(v -> {
                if (onActionClickListener != null) {
                    onActionClickListener.onClickAction(position, actionButtonList.get(position).getExtra());
                }
            });
        }

        @Override
        public int getItemCount() {
            return actionButtonList.size();
        }
    }

    private static class ActionViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivAction;
        private final TextView tvBadge;

        public ActionViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAction = (ImageView) itemView.findViewById(R.id.iv_action);
            tvBadge = (TextView) itemView.findViewById(R.id.tv_badge);
        }

        public void setActionData(ActionButton actionButton) {
            if (actionButton.getLocalIcon() != 0) {
                ivAction.setImageResource(actionButton.getLocalIcon());
            } else {
                GlideUtil.loadImage(ivAction, actionButton.getActionIcon().getUrl(RCChatRoomKit.getInstance().getAssetsPath()));
            }
            if (actionButton.hasBadge()) {
                tvBadge.setVisibility(VISIBLE);
                if (actionButton.getBadgeNum() > 0) {
                    tvBadge.setText(String.valueOf(actionButton.getBadgeNum()));
                } else {
                    tvBadge.setText("");
                }
            } else {
                tvBadge.setVisibility(GONE);
            }
        }
    }
}

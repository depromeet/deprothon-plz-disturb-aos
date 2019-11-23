package com.depromeet.plzdisturb.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.depromeet.plzdisturb.R;
import com.depromeet.plzdisturb.model.User;

public class ProfileView extends LinearLayout {

    private int userId;
    private OnEventListener listener;
    private ImageView ivProfile;
    private TextView tvName;

    public ProfileView(Context context) {
        super(context);
        init();
    }

    public ProfileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProfileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setOnEventListener(OnEventListener listener) {
        this.listener = listener;
        setOnTouchListener(new OnTouchListener() {

            Runnable disturbingRunnable = new Runnable() {
                @Override
                public void run() {
                    listener.onDisturbingEvent(userId);
                    postDelayed(disturbingRunnable, 10000L);
                }
            };

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        post(disturbingRunnable);
                        break;
                    case MotionEvent.ACTION_UP:
                        removeCallbacks(disturbingRunnable);
                        break;
                }

                return true;
            }
        });
    }

    public void setUser(User user) {
        userId = user.getId();
        tvName.setText(user.getName());
        Glide.with(this)
                .load(user.getImageUrl())
                .circleCrop()
                .into(ivProfile);
    }

    private void init() {
        setOrientation(VERTICAL);

        int size = getResources().getDimensionPixelSize(R.dimen.profile_view_width_dp);
        ivProfile = new ImageView(getContext());
        LayoutParams ivProfileLayoutParams = new LayoutParams(size, size);
        ivProfile.setLayoutParams(ivProfileLayoutParams);

        tvName = new TextView(getContext());
        LayoutParams tvNameLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tvNameLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        tvName.setLayoutParams(tvNameLayoutParams);

        addView(ivProfile);
        addView(tvName);
    }
}

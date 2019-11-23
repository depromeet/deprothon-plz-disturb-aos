package com.depromeet.plzdisturb.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.depromeet.plzdisturb.R;
import com.depromeet.plzdisturb.model.User;

import java.util.List;
import java.util.Random;

public class DisturbingView extends FrameLayout {

    private Random random;
    private Rect nextPosition;
    private SparseArray<ProfileView> profileViewMap;
    private SparseArray<Rect> positionMap;
    private OnEventListener listener;

    private int leftMarginLimit;
    private int topMarginLimit;
    private int profileViewWidth;
    private int profileViewHeight;

    public DisturbingView(@NonNull Context context) {
        super(context);
        init();
    }

    public DisturbingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DisturbingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DisturbingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        profileViewWidth = getResources().getDimensionPixelSize(R.dimen.profile_view_width_dp);
        profileViewHeight = getResources().getDimensionPixelSize(R.dimen.profile_view_height_dp);

        profileViewMap = new SparseArray<>();
        positionMap = new SparseArray<>();
        random = new Random();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        leftMarginLimit = MeasureSpec.getSize(widthMeasureSpec) - profileViewWidth;
        topMarginLimit = MeasureSpec.getSize(heightMeasureSpec) - profileViewHeight;
    }

    public void setListener(OnEventListener listener) {
        this.listener = listener;
    }

    public void addUserList(List<User> userList) {
        for (User user : userList) {
            addUser(user);
        }
    }

    public void addUser(User user) {
        if (nextPosition == null) {
            nextPosition = calculateNextPosition();
        }

        ProfileView view = new ProfileView(getContext());
        view.setUser(user);
        view.setOnEventListener(listener);

        Rect rect = new Rect(nextPosition.left, nextPosition.top, nextPosition.right, nextPosition.bottom);
        profileViewMap.append(user.getId(), view);
        positionMap.append(user.getId(), rect);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = nextPosition.left;
        params.topMargin = nextPosition.top;
        view.setLayoutParams(params);

        addView(view);
        nextPosition = calculateNextPosition();
    }

    public void remove(int userId) {
        removeView(profileViewMap.get(userId));
        profileViewMap.remove(userId);
        positionMap.remove(userId);
    }

    private Rect calculateNextPosition() {
        Rect newPosition;
        while (true) {
            int x = random.nextInt(leftMarginLimit);
            int y = random.nextInt(topMarginLimit);
            newPosition = new Rect(x, y, x + profileViewWidth, y + profileViewHeight);
            if (isValidPosition(newPosition)) {
                return newPosition;
            }
        }
    }

    private boolean isValidPosition(Rect newPosition) {
        for (int i = 0; i < positionMap.size(); i++) {
            Rect position = positionMap.get(positionMap.keyAt(i));
            if (position.intersect(newPosition)) {
                return false;
            }
        }
        return true;
    }

}

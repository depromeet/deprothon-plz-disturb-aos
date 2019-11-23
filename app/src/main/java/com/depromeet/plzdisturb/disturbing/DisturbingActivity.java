package com.depromeet.plzdisturb.disturbing;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.depromeet.plzdisturb.R;
import com.depromeet.plzdisturb.custom.DisturbingView;
import com.depromeet.plzdisturb.custom.OnEventListener;
import com.depromeet.plzdisturb.data.DisturbingRepositories;
import com.depromeet.plzdisturb.model.User;

import java.util.ArrayList;
import java.util.List;

public class DisturbingActivity extends AppCompatActivity implements DisturbingContract.View, OnEventListener {

    private DisturbingView view;
    private DisturbingContract.Presenter presenter;

    private ImageView coverView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disturbing);

        presenter = new DisturbingPresenter(DisturbingRepositories.getRepository(), this);

        view = findViewById(R.id.a_disturbing_dv);
        view.setListener(this);

        coverView = findViewById(R.id.a_disturbing_cover_iv);

        ArrayList<User> userList = (ArrayList<User>) getIntent().getSerializableExtra("members");
        presenter.load(userList);
    }

    @Override
    public void onDisturbingEvent(int userId) {
        Toast.makeText(this, userId + "를 괴롭히는 중", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFreeEvent(int userId) {
        Toast.makeText(this, userId + "를 해제", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @Override
    public void addUserList(List<User> userList) {
        view.post(() -> view.addUserList(userList));
    }

    @Override
    public void addUser(User user) {
        view.addUser(user);
    }

    @Override
    public void removeUser(int userId) {
        view.remove(userId);
    }

    @Override
    public void showDisturbingUI() {
        coverView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDisturbingUI() {
        coverView.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

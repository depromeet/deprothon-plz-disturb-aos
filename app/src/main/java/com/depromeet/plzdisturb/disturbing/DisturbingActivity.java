package com.depromeet.plzdisturb.disturbing;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.depromeet.plzdisturb.R;
import com.depromeet.plzdisturb.custom.DisturbingView;
import com.depromeet.plzdisturb.custom.OnEventListener;
import com.depromeet.plzdisturb.model.User;

import java.util.ArrayList;

public class DisturbingActivity extends AppCompatActivity implements OnEventListener {

    private DisturbingView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disturbing);
        Toolbar toolbar = findViewById(R.id.a_disturbing_toolbar);
        setSupportActionBar(toolbar);
        final View actionBar = getLayoutInflater().inflate(R.layout.actionbar, toolbar);


        ArrayList<User> userList = (ArrayList<User>) getIntent().getSerializableExtra("members");

        view = findViewById(R.id.a_disturbing_dv);
        view.setListener(this);

        view.post(() -> view.addUserList(userList));
    }

    @Override
    public void onDisturbingEvent(int userId) {
        Toast.makeText(this, userId + "를 괴롭히는 중", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFreeEvent(int userId) {
        Toast.makeText(this, userId + "를 해제", Toast.LENGTH_SHORT).show();
    }
}

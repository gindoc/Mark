package com.cwenhui.mark.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cwenhui.mark.R;
import com.cwenhui.mark.bean.User;
import com.cwenhui.mark.presenter.UserLoginPresenter;
import com.cwenhui.mark.view.IUserLoginView;

public class LoginActivity extends AppCompatActivity implements IUserLoginView, View.OnClickListener{
    private EditText username, password;
    private Button login, clear;
    private ProgressBar loading;
    private UserLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_fragment_index);
        setSupportActionBar(toolbar);

        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initView() {
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.login);
        clear = (Button) findViewById(R.id.clear);
        loading = (ProgressBar) findViewById(R.id.progressBar);
        presenter = new UserLoginPresenter(this);

        login.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public String getUserName()
    {
        return username.getText().toString();
    }

    @Override
    public String getPassword()
    {
        return password.getText().toString();
    }

    @Override
    public void clearUserName()
    {
        username.setText("");
    }

    @Override
    public void clearPassword()
    {
        password.setText("");
    }

    @Override
    public void showLoading()
    {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user)
    {
        Toast.makeText(this, user.getUsername() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    public void showFailedError()
    {
        Toast.makeText(this,
                "login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                presenter.login();
                break;

            case R.id.clear:
                presenter.clear();
                break;
        }
    }
}

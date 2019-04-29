package cn.icheny.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mTvUsername, mTvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTvUsername = findViewById(R.id.tv_username);
        mTvPassword = findViewById(R.id.tv_password);
    }

    /**
     * 登录
     */
    public void login(View view) {

        String username = mTvUsername.getText().toString();
        String password = mTvPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checkLogin(username, password)) {
            startActivity(new Intent(this, LoginSuccessActivity.class));
        }
    }

    /**
     * 模拟后台服务器校验登录
     *
     * @param username 用户名
     * @param password 密码
     */
    public boolean checkLogin(String username, String password) {

        if ("admin".equals(username) && "admin123".equals(password)) {
            return true;
        }
        Toast.makeText(this, "用户名或密码不正确!", Toast.LENGTH_SHORT).show();
        return false;
    }
}

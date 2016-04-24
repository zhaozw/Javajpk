package cn.edu.hebiace.javajpk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.hebiace.javajpk.R;
import cn.edu.hebiace.javajpk.core.ActionCallBackListener;

/**
 * Created by lpwxs on 15-12-3.
 */
public class LoginActivity extends LBaseActivity {

    //请求码
    private static final int REQUEST_CODE = 1;

    private EditText loginName;
    private EditText passWord;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName = (EditText) findViewById(R.id.login_name);
        passWord = (EditText) findViewById(R.id.login_password);

    }

    public void goregiter(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void goLogin(View view) {
        String name = loginName.getText().toString();
        String pwd = passWord.getText().toString();

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();

//        appAction.login(name, pwd, new ActionCallBackListener<Void>() {
//            @Override
//            public void onSuccess(Void data) {
//                System.out.println("++++++成功+++++" + data);
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                finish();
//            }
//
//            @Override
//            public void onFailure(String errorCode ) {
//                Toast.makeText(LoginActivity.this, R.string.login_fail,Toast.LENGTH_SHORT).show();
//                System.out.println("===========" + errorCode);
//            }
//        });
    }

    public void goMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}

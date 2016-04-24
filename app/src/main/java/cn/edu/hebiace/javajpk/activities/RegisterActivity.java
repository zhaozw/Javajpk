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
 *
 *
 *
 * Created by lpwxs on 15-12-3.
 */
public class RegisterActivity extends LBaseActivity {

    private EditText registName;
    private EditText passWord;
    private EditText codeEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registName = (EditText) findViewById(R.id.registerName);
        passWord = (EditText) findViewById(R.id.registerPassword);
        codeEdit = (EditText) findViewById(R.id.codeEdit);

    }

    /**
     * do register
     * @param view
     */
    public void register(View view) {
        String name = registName.getText().toString();
        String pwd = passWord.getText().toString();
        this.appAction.register(name, pwd, new ActionCallBackListener<Void>() {
            @Override
            public void onSuccess(Void data) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();
            }

            @Override
            public void onFailure(String errorEvent) {
                Toast.makeText(context, R.string.register_fail, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     *获get validating code by random number.
     */
    public void getCode(View view) {
        codeEdit.setText(getRandom(111,1999));
    }
    private String getRandom(int left,int right){
        long code = (long) (Math.random()*(right-left))+left;
        return code+"";
    }

}

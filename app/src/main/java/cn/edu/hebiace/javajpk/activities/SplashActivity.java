package cn.edu.hebiace.javajpk.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import cn.edu.hebiace.javajpk.R;
import cn.edu.hebiace.javajpk.utils.UserUtils;
import cn.edu.hebiace.javajpk.utils.Utils;

public class SplashActivity extends AppCompatActivity {

    private UserUtils userUtils;
    private ImageView imageView;
    private Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        userUtils = UserUtils.getUserUtils(this);
        ctx = SplashActivity.this;
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.activity_splash_img);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1f,0.2f);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                int netType = Utils.getNetworkType(ctx);
                if(netType == 0){
                    Utils.log(ctx,"没有网络，请联网重试!");
                }else{
                    userUtils.setNetType(netType);
                    if(userUtils.isFirst()){
                        userUtils.setIsFirst(false);
                        go(AboutMeActivity.class);
                    }else{
                        go(LoginActivity.class);
                    }
                }
                SplashActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        //动画结束后是否保留结束状态，默认是false
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(3500);
        imageView.startAnimation(alphaAnimation);
    }

    public void go(Class<?> clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}

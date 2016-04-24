package cn.edu.hebiace.javajpk.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cn.edu.hebiace.javajpk.R;
import cn.edu.hebiace.javajpk.adapters.MyPagerAdapter;

/**
 * 该应用程序的主导航界面
 * 底部有四个导航栏，可以动态添加四个不同的Fragment，
 * 实现在同一Activity中动态显示不同内容的效果
 */
public class MainActivity extends LBaseActivity implements ViewPager.OnPageChangeListener {

    private RadioGroup navBarGroup;
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件对象
        initViews();
        addEvent();
    }


    /**
     * 初始化显示的布局内容
     */
    public void initViews() {
        viewPager = (ViewPager) findViewById(R.id.main_container);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        navBarGroup = (RadioGroup) findViewById(R.id.main_nav_group);
    }

    public void addEvent() {
        //为底部导航栏添加点击监听事件
        navBarGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.id_nav_tab_movie:
                        currFrag = 0;
                        break;
                    case R.id.id_nav_tab_cinema:
                        currFrag = 1;
                        break;
                    case R.id.id_nav_tab_sales:
                        currFrag = 2;
                        break;
                    case R.id.id_nav_tab_user:
                        currFrag = 3;
                        break;
                }
                viewPager.setCurrentItem(currFrag);
            }
        });
    }

    private static int[] navId = {
            R.id.id_nav_tab_movie, R.id.id_nav_tab_cinema,
            R.id.id_nav_tab_sales, R.id.id_nav_tab_user
    };

    /**
     * 表示加载的Fragment序号
     * 0表示FragConMovie,
     * 1表示FragConCinema,
     * 2表示FragConSales,
     * 3表示FragConUser
     */
    private int currFrag = 0;


    private static final String TAG = "MainActivity.this=";

    /**
     * 跳转到另一个activity
     *
     * @param clazz
     */
    private void go(Class clazz) {
        startActivity(new Intent(this, clazz));
    }


    private long oldtime;//记录第一次点击返回键的时间点
    private boolean firstPress = true; //记录是否是第一次点击返回键

    /**
     * 重写onKeyDown方法，实现1秒内连续点击两次退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (firstPress) {
                //第一次点击返回按钮记录时间点
                oldtime = System.currentTimeMillis();
                firstPress = false;
                Toast.makeText(this, R.string.exit_again, Toast.LENGTH_SHORT).show();
            } else {
                long time = System.currentTimeMillis() - oldtime;
                if (time < 1000) {
                    finish();
                } else {
                    //连续两次点击间隔超过1秒,给出提示
                    Toast.makeText(this, R.string.exit_again, Toast.LENGTH_SHORT).show();
                    //更新最后一次点击返回键的时间
                    oldtime = System.currentTimeMillis();
                }
            }
        }
        //拦截返回按钮点击事件
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("scroll", "onPageScrolled");
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("select", "onPageSelected");
        RadioButton rd = (RadioButton) navBarGroup.getChildAt(position);
        rd.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("state", "onPageScrollStateChanged");
    }

}

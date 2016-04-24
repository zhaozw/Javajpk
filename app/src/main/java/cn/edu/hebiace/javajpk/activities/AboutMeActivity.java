package cn.edu.hebiace.javajpk.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebiace.javajpk.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by duke on 16-3-15.
 */
public class AboutMeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ImageView indi_0,indi_1,indi_2;
    private Button gologin;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private Context ctx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        setContentView(R.layout.aboutme);
        ctx = AboutMeActivity.this;
        initViews();

    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.about_viewpager);
        pagerAdapter = new MyPagerAdapter() ;
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);

//        viewPager.setCurrentItem(0);

        indi_0 = (ImageView) findViewById(R.id.about_indicator_1);
        indi_1 = (ImageView) findViewById(R.id.about_indicator_2);
        indi_2 = (ImageView) findViewById(R.id.about_indicator_3);

        gologin = (Button) findViewById(R.id.about_gologin);
        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ctx,LoginActivity.class));
                AboutMeActivity.this.finish();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                gologin.setVisibility(View.GONE);
                indi_0.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                indi_1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                indi_2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                break;
            case 1:
                gologin.setVisibility(View.GONE);
                indi_0.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                indi_1.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                indi_2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                break;
            case 2:
                gologin.setVisibility(View.VISIBLE);
                indi_0.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                indi_1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                indi_2.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyPagerAdapter extends PagerAdapter{

        List<ImageView> imgs = new ArrayList<>();
        public MyPagerAdapter(){
            ImageView imageView = null;
            imageView = new ImageView(ctx);
            imageView.setImageResource(R.mipmap.sucai_25);
            imgs.add(imageView);
            imageView = new ImageView(ctx);
            imageView.setImageResource(R.mipmap.sucai_33);
            imgs.add(imageView);
            imageView = new ImageView(ctx);
            imageView.setImageResource(R.mipmap.sucai_34);
            imgs.add(imageView);
        }
        @Override
        public int getCount() {
            return imgs == null ? 0 : imgs.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imgs.get(position));

            return imgs.get(position);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imgs.get(position));

        }

        public void setItem(List<ImageView> item){
            imgs.clear();
            imgs = item;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}

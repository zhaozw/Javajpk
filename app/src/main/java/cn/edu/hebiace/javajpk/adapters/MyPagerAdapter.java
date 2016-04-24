package cn.edu.hebiace.javajpk.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.edu.hebiace.javajpk.activities.frags.FragKe;
import cn.edu.hebiace.javajpk.activities.frags.FragNews;
import cn.edu.hebiace.javajpk.activities.frags.FragVideos;
import cn.edu.hebiace.javajpk.activities.frags.FragUser;

/**
 * Created by lpwxs on 15-12-10.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    Fragment[] fragments;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[]{
                FragNews.newInstance(),
                FragKe.newInstance(),
                FragVideos.newInstance(),
                FragUser.newInstance()
        };
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

}

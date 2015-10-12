package me.chenfuduo.myjobforcontest.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.chenfuduo.myjobforcontest.R;
import me.chenfuduo.myjobforcontest.fragment.FragmentFactory;

/**
 * Created by chenfuduo on 2015/10/9.
 */
public class SchoolFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[];
    private Context context;

    public SchoolFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        tabTitles = context.getResources().getStringArray(R.array.tab_names);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}

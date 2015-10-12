package me.chenfuduo.myjobforcontest.fragment;

import android.support.v4.util.ArrayMap;
import android.util.Log;


/**
 * Created by chenfuduo on 2015/10/1.
 */
public class FragmentFactory {

    private static final String TAG = FragmentFactory.class.getSimpleName();
    private static ArrayMap<Integer, BaseFragment> fragmentArrayMap = new ArrayMap<>();

    //ArrayMap和SparseArray都能达到优化
   // private static SparseArray<Fragment> sparseFragments = new SparseArray<>();

    public static BaseFragment createFragment(int position) {
        BaseFragment fragment;
        fragment = fragmentArrayMap.get(position);
        //sparseFragments.get(position);
        // sparseFragments.put(position,fragment);
        if (fragment == null) {

            Log.e(TAG, "createFragment " + "Fragment为null执行");
            
            if (position == 0) {
                //中国科学技术大学
                fragment = new USTCFragment();
            } else if (position == 1) {
                //合肥工业大学
                fragment = new HFUTFragment();
            } else if (position == 2) {
                //安徽大学
                fragment = new AHUFragment();
            }
            if (fragment != null) {
                fragmentArrayMap.put(position, fragment);
            }
        }
        return fragment;
    }
}

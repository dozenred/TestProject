package cn.bupt.dozenpiggy.testproject.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Dozen on 2017/7/19.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
   // private String tabTitles[] = new String[]{"tab1","tab2","tab3"};
    private Context context;
    public MyFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, Context context){
        super(fragmentManager);
        this.fragmentList = fragmentList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getClass().getSimpleName();
    }
}

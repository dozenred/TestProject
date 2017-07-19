package cn.bupt.dozenpiggy.testproject.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Dozen on 2017/7/19.
 */

public class MyPagerAdapter extends PagerAdapter {
    private List<View> viewList;

    public MyPagerAdapter(List<View> viewList){
        this.viewList = viewList;
    }
    //显示多少个页面
    @Override
    public int getCount() {
        return viewList.size();
    }

    //判断初始化返回的Object是不是一个View对象
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //初始化显示的条目对象
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);

        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    //销毁条目对象
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View)object);
    }
}

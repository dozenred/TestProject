package cn.bupt.dozenpiggy.testproject.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bupt.dozenpiggy.testproject.R;
import cn.bupt.dozenpiggy.testproject.adapter.MyFragmentPagerAdapter;
import cn.bupt.dozenpiggy.testproject.adapter.MyPagerAdapter;
import cn.bupt.dozenpiggy.testproject.animation.ZoomOutPageTransformer;
import cn.bupt.dozenpiggy.testproject.databinding.ActivityViewPagerBinding;
import cn.bupt.dozenpiggy.testproject.fragment.PageFragment;

public class ViewPagerActivity extends AppCompatActivity {
    private static final String TAG = "ViewPagerActivity";
    ActivityViewPagerBinding mBinding;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_pager);
        //initViewPager();
        initFragmentViewPager();
    }

    private void initFragmentViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            fragmentList.add(PageFragment.newInstance(i));
        }
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, this);
        mBinding.viewpager.setAdapter(myFragmentPagerAdapter);
        mBinding.slidingTabs.setupWithViewPager(mBinding.viewpager);
        mBinding.slidingTabs.setTabMode(TabLayout.MODE_FIXED);
        mBinding.viewpager.setPageTransformer(true,new ZoomOutPageTransformer());
    }

    private void initViewPager() {
        List<View> viewList = new ArrayList<>();
        TextView tv1 = new TextView(this);
        tv1.setText("天才1");
        TextView tv2 = new TextView(this);
        tv2.setText("天才2");
        TextView tv3 = new TextView(this);
        tv3.setText("天才3");
        TextView tv4 = new TextView(this);
        tv4.setText("天才4");
        viewList.add(tv1);
        viewList.add(tv2);
        viewList.add(tv3);
        viewList.add(tv4);

        mBinding.viewpager.setAdapter(new MyPagerAdapter(viewList));
//        mBinding.viewpager.setAdapter(new PagerAdapter() {
//            public int getCount() {
//                return 4;
//            }
//
//            //判断初始化返回的Object是不是一个View对象
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//                return view == object;
//            }
//
//            //初始化显示的条目对象
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                //return super.instantiateItem(container, position);
//
//                TextView textView = new TextView(ViewPagerActivity.this);
//                textView.setGravity(Gravity.CENTER);
//                textView.setTextSize(20);
//                textView.setText("我是天才" + position + "号");
//
//                container.addView(textView);
//                return textView;
//            }
//
//            //销毁条目对象
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                //super.destroyItem(container, position, object);
//                container.removeView((View) object);
//            }
//        });
   }
}

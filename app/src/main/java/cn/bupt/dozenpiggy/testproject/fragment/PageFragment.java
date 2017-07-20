package cn.bupt.dozenpiggy.testproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.bupt.dozenpiggy.testproject.R;

/**
 * Created by Dozen on 2017/7/20.
 */

/**
 * (1)Activity继承了V4包下的FragmentActivity
 * (2)数据源中的List<View>   变成了List<Fragment>
 *初始化数据源的时候需要将数据使用fragment.setArguments(bundle)传入fragment中
 * (3)V4包下在Activity中获取FragmentManager时需要使用getSupportFragmentManager()方法
 * (4)自定义的PagerAdapter需要继承FragmentPagerAdapter
 * (5)在Fragment中需要在Activity加载完成的方法onActivityCreated(Bundle savedInstanceState)中 获取数据并更新UI
 */

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        return view;
    }

}

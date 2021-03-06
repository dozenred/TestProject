package cn.bupt.dozenpiggy.testproject;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.bupt.dozenpiggy.testproject.bean.Student;
import cn.bupt.dozenpiggy.testproject.databinding.ActivityTestBinding;
import cn.bupt.dozenpiggy.testproject.ui.StatusBarActivity;
import cn.bupt.dozenpiggy.testproject.ui.ViewPagerActivity;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";
    ActivityTestBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        Student student = new Student("Dozen", "Piggy");
        //mBinding.setStudent(student);
        //setContentView(R.layout.activity_test);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        jumpToPermissionActivity();
        jumpToStatusBarActivity();
        jumpToViewPagerActivity();
    }

    private void jumpToViewPagerActivity() {
        mBinding.jumpToViewpagerActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewPagerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void jumpToStatusBarActivity() {
        mBinding.jumpToStatusActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StatusBarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void jumpToPermissionActivity() {
        mBinding.jumpToPermissionActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StatusBarActivity.class);
                startActivity(intent);
            }
        });
    }


}

package cn.bupt.dozenpiggy.testproject;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.bupt.dozenpiggy.testproject.bean.Student;
import cn.bupt.dozenpiggy.testproject.databinding.ActivityTestBinding;
import cn.bupt.dozenpiggy.testproject.ui.PermissionActivity;

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


        jumpToPermissionActivity();
    }

    private void jumpToPermissionActivity() {
        mBinding.jumpToPermissionActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PermissionActivity.class);
                startActivity(intent);
            }
        });
    }


}

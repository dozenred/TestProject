package cn.bupt.dozenpiggy.testproject.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.bupt.dozenpiggy.testproject.R;
import cn.bupt.dozenpiggy.testproject.databinding.ActivityPermissionBinding;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = "PermissionActivity";
    ActivityPermissionBinding mBinding;

    private static final String[] perms = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int RC_CAMERA_AND_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_permission);
        loadBackground();
        methodRequiresTwoPermission();
    }

    private void loadBackground() {
        Glide.with(this).load(R.drawable.launch).into(mBinding.launchPictureIv);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    /**有了注解之后就会再次调用if方法，类似于回调
     *没有注解的话，通过实现EasyPermisson的回调函数来处理已经获得的权限和未获得的权限之后的逻辑
     * requestPermissions 中第二个参数是当应用申请的权限被拒绝后 再次打开应用会优先弹出的提示对话框内容（为什么需要这组权限）
     */
    //@AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    private void methodRequiresTwoPermission() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            Toast.makeText(this, "ooooooo", Toast.LENGTH_SHORT).show();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.camera_and_location_rationale),
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

    //    private void methodRequiresWritePermission() {
    //        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //        if(EasyPermissions.hasPermissions(this, perms)) {
    //            Toast.makeText(this, "write external storage granted", Toast.LENGTH_SHORT).show();
    //        } else {
    //            EasyPermissions.requestPermissions(this, getString(R.string.write_external_rationale),
    //                    RC_EXTERNAL_STORAGE, perms);
    //        }
    //    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        for (int i = 0; i < perms.size(); i++) {
            Log.d(TAG, "onPermissionsGranted:" + perms.get(i));
        }

        if (perms.size() == 3) {
            //if all granted......do the thing
            Toast.makeText(this, "all permission granted", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("此应用需要使用权限")
                    .setRationale("永远禁止将退出此应用！")
                    .setNegativeButton("取消")
                    .setPositiveButton("确定").build().show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("此应用缺少必要的运行权限，将退出！");
            builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.show().setCancelable(false);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            //String[] perms = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(this, perms)) {
                // Already have permission, do the thing
                Toast.makeText(this, R.string.returned_from_app_settings_to_activity  , Toast.LENGTH_SHORT)
                        .show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("缺少权限");
                builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                builder.show().setCancelable(false);
            }

        }

    }
}

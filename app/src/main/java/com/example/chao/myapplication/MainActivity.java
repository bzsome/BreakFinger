package com.example.chao.myapplication;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ComponentName mDeviceAdminSample;
    private DevicePolicyManager mDPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_active = (Button) findViewById(R.id.btn_active);
        Button btn_lock = (Button) findViewById(R.id.btn_lock);
        Button btn_setPass = (Button) findViewById(R.id.btn_setPass);
        Button btn_security = (Button) findViewById(R.id.btn_security);
        Button btn_clearPass = (Button) findViewById(R.id.btn_clearPass);

        mDeviceAdminSample = new ComponentName(this, LockReceiver.class);
        mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

        btn_active.setOnClickListener(new View.OnClickListener() {
            //调用设备管理器的页面显示,激活需要自己点击
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "设备管理器");
                startActivity(intent);
            }
        });

        btn_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDPM.isAdminActive(mDeviceAdminSample)) {//判断定义好的组件是否开启激活了
                    mDPM.lockNow();
                } else {
                    Toast.makeText(getApplicationContext(), "还没有激活设备管理器", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_setPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDPM.isAdminActive(mDeviceAdminSample)) {
                    mDPM.resetPassword("123456", 0);
                    Toast.makeText(getApplicationContext(), "已设置密码为：123456", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "还没有激活设备管理器", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "验证密码后，添加指纹", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                startActivity(intent);
            }
        });

        btn_clearPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDPM.isAdminActive(mDeviceAdminSample)) {
                    mDPM.resetPassword(null, 0);
                    Toast.makeText(getApplicationContext(), "已清除锁屏密码！", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "还没有激活设备管理器", Toast.LENGTH_LONG).show();
                }
            }
        });


        Button btn_startTest = (Button) findViewById(R.id.btn_startTest);
        btn_startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                //启动
                startActivity(intent);
            }
        });

    }

}

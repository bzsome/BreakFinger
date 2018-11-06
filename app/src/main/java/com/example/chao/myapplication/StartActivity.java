package com.example.chao.myapplication;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    String packageSetting = "com.android.settings";
    String className = "fingerprint.FingerprintSettings";
    String setting = "com.android.settings.fingerprint.FingerprintSettings";
    String setting2 = "com.android.settings.fingerprint.FingerprintEnrollFindSensor";

    String qq = "com.tencent.mobileqq.activity.SplashActivity";

    EditText edit_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        edit_log = findViewById(R.id.edit_log);

        //启动app
        Button btn_startApp = this.findViewById(R.id.btn_startApp);
        btn_startApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.edit_01);
                String packageName = editText.getText().toString();
                startAPP(packageName);

            }
        });

        //启动Activity
        Button btn_startActivity = this.findViewById(R.id.btn_startActivity);
        btn_startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText02 = findViewById(R.id.edit_02);
                String packageName = editText02.getText().toString();
                EditText editText03 = findViewById(R.id.edit_03);
                String className = editText03.getText().toString();
                startActivity(packageName, className);
            }
        });  //启动Activity
        Button btn_startFrame = this.findViewById(R.id.btn_startFrame);
        btn_startFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startfragment();
            }
        });
    }


    /**
     * @param :String 包名
     * @throws :启动apk
     **/
    public void startAPP(String appPackageName) {
        try {
            Intent intent = this.getPackageManager().getLaunchIntentForPackage(appPackageName);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "启动出错", Toast.LENGTH_LONG).show();
            edit_log.append(e.toString());
        }
    }


    /**
     * @param :String 包名
     * @param :String 类名
     * @throws :启动apk
     **/
    public void startActivity(String packageName, String className) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "启动出错", Toast.LENGTH_LONG).show();
            edit_log.append(e.toString());
        }
    }

    public void startfragment() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //添加启动外部应用的Flag，不然会报错
        intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, "com.android.settings.SubSettings.SecuritySettings");//要启动的fragment
        intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true);
        intent.setClassName("com.android.settings", "com.android.settings.SubSettings");//包名，要启动fragment所依赖的Activity

        getApplicationContext().startActivity(intent);
    }
}

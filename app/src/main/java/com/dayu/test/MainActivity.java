package com.dayu.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PackageManager pm = getPackageManager();
        boolean hasHce = pm.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);
        TextView textView = findViewById(R.id.text);

        if (hasHce) {
            textView.setText("设备支持 Host Card Emulation");
            // 设备支持 Host Card Emulation
            // 进行相应的操作
        } else {
            textView.setText("设备不支持 Host Card Emulation");
            // 设备不支持 Host Card Emulation
            // 提示用户或执行其他逻辑
        }
    }
}
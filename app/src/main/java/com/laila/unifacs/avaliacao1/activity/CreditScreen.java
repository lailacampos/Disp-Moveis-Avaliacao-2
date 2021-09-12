package com.laila.unifacs.avaliacao1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.laila.unifacs.avaliacao1.BuildConfig;
import com.laila.unifacs.avaliacao1.R;

public class CreditScreen extends AppCompatActivity {

    private TextView androidVersionTextView, androidSDKVersionTextView, appVersionTextView;
    private String androidVersion, androidVersionSDK, appVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_screen);

        this.androidVersion = getString(R.string.versao_android_content, Build.VERSION.RELEASE);
        this.androidVersionSDK = getString(R.string.versao_sdk_content, Build.VERSION.SDK_INT);
        this.appVersion = BuildConfig.VERSION_NAME;

        this.androidVersionTextView = findViewById(R.id.versao_android_TextView);
        this.androidSDKVersionTextView = findViewById(R.id.versao_sdk_TextView);
        this.appVersionTextView = findViewById(R.id.app_version_TextView);

        this.androidVersionTextView.setText(androidVersion);
        this.androidSDKVersionTextView.setText(androidVersionSDK);
        this.appVersionTextView.setText(appVersion);

    }
}
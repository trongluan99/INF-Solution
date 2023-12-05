package com.inf.module.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ads.inf.ads.InfAd;
import com.ads.inf.ads.InfAdCallback;
import com.ads.inf.ads.wrapper.ApAdError;
import com.inf.module.BuildConfig;
import com.inf.module.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        InfAd.getInstance().loadSplashInterstitialAds(SplashActivity.this, BuildConfig.ad_interstitial_splash, 5000, 25000, new InfAdCallback() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startMain();
            }

            @Override
            public void onAdFailedToLoad(@Nullable ApAdError adError) {
                super.onAdFailedToLoad(adError);
                startMain();
            }

            @Override
            public void onAdFailedToShow(@Nullable ApAdError adError) {
                super.onAdFailedToShow(adError);
                startMain();
            }
        }, null);
    }

    private void startMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
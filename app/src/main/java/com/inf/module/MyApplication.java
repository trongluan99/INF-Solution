package com.inf.module;

import com.ads.inf.ads.InfAd;
import com.ads.inf.config.AdjustConfig;
import com.ads.inf.config.InfAdConfig;
import com.ads.inf.application.AdsMultiDexApplication;
import com.ads.inf.billing.AppPurchase;
import com.ads.inf.admob.Admob;
import com.ads.inf.admob.AppOpenManager;
import com.inf.module.activity.MainActivity;
import com.inf.module.activity.SplashActivity;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends AdsMultiDexApplication {
    private final String APPSFLYER_TOKEN = "";
    private final String ADJUST_TOKEN = "";
    private final String EVENT_PURCHASE_ADJUST = "";
    private final String EVENT_AD_IMPRESSION_ADJUST = "";
    protected StorageCommon storageCommon;
    private static MyApplication context;
    public static MyApplication getApplication() {
        return context;
    }
    public StorageCommon getStorageCommon() {
        return storageCommon;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Admob.getInstance().setNumToShowAds(0);

        storageCommon = new StorageCommon();
        initBilling();
        initAds();

    }

    private void initAds() {
        String environment = BuildConfig.env_dev ? InfAdConfig.ENVIRONMENT_DEVELOP : InfAdConfig.ENVIRONMENT_PRODUCTION;
        infAdConfig = new InfAdConfig(this, InfAdConfig.PROVIDER_ADMOB, environment);

        AdjustConfig adjustConfig = new AdjustConfig(true,ADJUST_TOKEN);
        adjustConfig.setEventAdImpression(EVENT_AD_IMPRESSION_ADJUST);
        adjustConfig.setEventNamePurchase(EVENT_PURCHASE_ADJUST);
        infAdConfig.setAdjustConfig(adjustConfig);
        infAdConfig.setIdAdResume(BuildConfig.ads_open_app);

        listTestDevice.add("EC25F576DA9B6CE74778B268CB87E431");
        infAdConfig.setListDeviceTest(listTestDevice);
        infAdConfig.setIntervalInterstitialAd(15);
        InfAd.getInstance().init(this, infAdConfig, false);
        Admob.getInstance().setDisableAdResumeWhenClickAds(true);
        Admob.getInstance().setOpenActivityAfterShowInterAds(true);
        AppOpenManager.getInstance().disableAppResumeWithActivity(SplashActivity.class);
    }

    private void initBilling() {
        List<String> listINAPId = new ArrayList<>();
        listINAPId.add(MainActivity.PRODUCT_ID);
        List<String> listSubsId = new ArrayList<>();

        AppPurchase.getInstance().initBilling(getApplication(), listINAPId, listSubsId);
    }

}

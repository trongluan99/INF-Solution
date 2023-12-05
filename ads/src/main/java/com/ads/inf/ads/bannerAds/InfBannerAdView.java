package com.ads.inf.ads.bannerAds;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ads.inf.R;
import com.ads.inf.admob.Admob;
import com.ads.inf.ads.InfAd;
import com.ads.inf.ads.InfAdCallback;
import com.ads.inf.funtion.AdCallback;

public class InfBannerAdView extends RelativeLayout {

    private String TAG = "InfBannerAdView";

    public InfBannerAdView(@NonNull Context context) {
        super(context);
        init();
    }

    public InfBannerAdView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public InfBannerAdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    public InfBannerAdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_banner_control, this);
    }

    public void loadBanner(Activity activity, String idBanner,String tokenAdjust) {
        loadBanner(activity, idBanner, new InfAdCallback(), tokenAdjust);
    }

    public void loadBanner(Activity activity, String idBanner, InfAdCallback adCallback, String tokenAdjust) {
        InfAd.getInstance().loadBanner(activity, idBanner, adCallback, tokenAdjust);
    }

    public void loadInlineBanner(Activity activity, String idBanner, String inlineStyle, String tokenAdjust) {
        Admob.getInstance().loadInlineBanner(activity, idBanner, inlineStyle, tokenAdjust);
    }

    public void loadInlineBanner(Activity activity, String idBanner, String inlineStyle, AdCallback adCallback, String tokenAdjust) {
        Admob.getInstance().loadInlineBanner(activity, idBanner, inlineStyle, adCallback, tokenAdjust);
    }

    public void loadBannerFragment(Activity activity, String idBanner, String tokenAdjust) {
        InfAd.getInstance().loadBannerFragment(activity, idBanner, getRootView(), tokenAdjust);
    }

    public void loadBannerFragment(Activity activity, String idBanner, AdCallback adCallback, String tokenAdjust) {
        InfAd.getInstance().loadBannerFragment(activity, idBanner, getRootView(), adCallback, tokenAdjust);
    }

    public void loadInlineBannerFragment(Activity activity, String idBanner, String inlineStyle, String tokenAdjust) {
        Admob.getInstance().loadInlineBannerFragment(activity, idBanner, getRootView(), inlineStyle, tokenAdjust);
    }

    public void loadInlineBannerFragment(Activity activity, String idBanner, String inlineStyle, AdCallback adCallback, String tokenAdjust) {
        Admob.getInstance().loadInlineBannerFragment(activity, idBanner, getRootView(), inlineStyle, adCallback, tokenAdjust);
    }
}
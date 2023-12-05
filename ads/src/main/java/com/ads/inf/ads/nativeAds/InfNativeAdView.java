package com.ads.inf.ads.nativeAds;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ads.inf.R;
import com.ads.inf.ads.InfAd;
import com.ads.inf.ads.InfAdCallback;
import com.ads.inf.ads.wrapper.ApNativeAd;
import com.facebook.shimmer.ShimmerFrameLayout;

public class InfNativeAdView extends RelativeLayout {

    private int layoutCustomNativeAd = 0;
    private ShimmerFrameLayout layoutLoading;
    private FrameLayout layoutPlaceHolder;
    private String TAG = "InfNativeAdView";

    public InfNativeAdView(@NonNull Context context) {
        super(context);
        init();
    }

    public InfNativeAdView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public InfNativeAdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    public InfNativeAdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.InfNativeAdView, 0, 0);
        // Get layout native view custom and  layout loading
        layoutCustomNativeAd = typedArray.getResourceId(R.styleable.InfNativeAdView_layoutCustomNativeAd, 0);
        int idLayoutLoading = typedArray.getResourceId(R.styleable.InfNativeAdView_layoutLoading, 0);
        if (idLayoutLoading != 0)
            layoutLoading = (ShimmerFrameLayout) LayoutInflater.from(getContext()).inflate(idLayoutLoading, null);

        init();
    }

    private void init() {
        layoutPlaceHolder = new FrameLayout(getContext());
        addView(layoutPlaceHolder);
        if (layoutLoading != null)
            addView(layoutLoading);

    }

    public void setLayoutCustomNativeAd(int layoutCustomNativeAd) {
        this.layoutCustomNativeAd = layoutCustomNativeAd;
    }

    public void setLayoutLoading(int idLayoutLoading) {
        this.layoutLoading = (ShimmerFrameLayout) LayoutInflater.from(getContext()).inflate(idLayoutLoading, null);
        addView(layoutLoading);
    }

    public void populateNativeAdView(Activity activity, ApNativeAd nativeAd) {
        if (layoutLoading == null) {
            Log.e(TAG, "populateNativeAdView error : layoutLoading not set");
            return;
        }
        InfAd.getInstance().populateNativeAdView(activity, nativeAd, layoutPlaceHolder, layoutLoading);
    }

    public void loadNativeAd(Activity activity, String idAd, String tokenAdjust) {
        loadNativeAd(activity, idAd, new InfAdCallback() {
        }, tokenAdjust);
    }

    public void loadNativeAd(Activity activity, String idAd, InfAdCallback adCallback, String tokenAdjust) {
        if (layoutLoading == null) {
            setLayoutLoading(R.layout.loading_native_medium);
        }
        if (layoutCustomNativeAd == 0) {
            layoutCustomNativeAd = R.layout.custom_native_admod_medium_rate;
            setLayoutCustomNativeAd(layoutCustomNativeAd);
        }
        InfAd.getInstance().loadNativeAd(activity, idAd, layoutCustomNativeAd, layoutPlaceHolder, layoutLoading, adCallback, tokenAdjust);
    }

    public void loadNativeAd(Activity activity, String idAd, int layoutCustomNativeAd, int idLayoutLoading, String tokenAdjust) {
        setLayoutLoading(idLayoutLoading);
        setLayoutCustomNativeAd(layoutCustomNativeAd);
        loadNativeAd(activity, idAd, tokenAdjust);
    }

    public void loadNativeAd(Activity activity, String idAd, int layoutCustomNativeAd, int idLayoutLoading, InfAdCallback adCallback, String tokenAdjust) {
        setLayoutLoading(idLayoutLoading);
        setLayoutCustomNativeAd(layoutCustomNativeAd);
        loadNativeAd(activity, idAd, adCallback, tokenAdjust);
    }
}
package com.inf.module.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ads.inf.admob.Admob;
import com.ads.inf.ads.InfAd;
import com.ads.inf.config.InfAdConfig;
import com.ads.inf.funtion.AdCallback;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.inf.module.R;
import com.inf.module.activity.ContentActivity;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public class BlankFragment extends Fragment {
    InterstitialAd mInterstitialAd;
    Button button;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.btnNextFragment);
        button.setEnabled(false);
        View view1 = view.findViewById(R.id.include).getRootView();
        String idBanner;
        if (InfAd.getInstance().getMediationProvider() == InfAdConfig.PROVIDER_ADMOB) {
            idBanner = getString(R.string.admod_banner_id);
        } else {
            idBanner = getString(R.string.applovin_test_banner);
        }

        InfAd.getInstance().loadBannerFragment(requireActivity(), idBanner, view1, new AdCallback() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }
        }, null);

        button.setOnClickListener(v -> {
            Admob.getInstance().forceShowInterstitial(getActivity(), mInterstitialAd, new AdCallback() {
                @Override
                public void onAdClosed() {
                    ((ContentActivity) getActivity()).showFragment(new InlineBannerFragment(), "BlankFragment2");
                }
            });
        });

        FrameLayout flPlaceHolder = view.findViewById(R.id.fl_adplaceholder);
        ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shimmer_container_native);
        InfAd.getInstance().loadNativeAd(requireActivity(), getString(R.string.admod_native_id), com.ads.inf.R.layout.custom_native_admob_free_size, flPlaceHolder, shimmerFrameLayout, null);
    }
}
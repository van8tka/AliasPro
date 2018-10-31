package com.devprogram.aliaspro.Helpers;

import android.content.Context;
import android.view.View;

import com.devprogram.aliaspro.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdMobCreater implements IAdMobCreater {
    @Override
    public void InitAdMobBanner(AdView adViewBanner , Context context, String admobPubId) {
        MobileAds.initialize(context, admobPubId);
        AdRequest request = new AdRequest.Builder().build();
        adViewBanner.loadAd(request);
    }
}

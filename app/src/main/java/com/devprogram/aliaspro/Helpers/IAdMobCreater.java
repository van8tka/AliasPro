package com.devprogram.aliaspro.Helpers;

import android.content.Context;

import com.google.android.gms.ads.AdView;

public interface IAdMobCreater {
    void InitAdMobBanner(AdView view , Context context, String admobPubId);
}

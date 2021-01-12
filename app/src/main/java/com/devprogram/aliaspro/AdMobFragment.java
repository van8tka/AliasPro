package com.devprogram.aliaspro;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdMobFragment extends Fragment {


    public AdMobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admob, container, false);
        InitAdMob(view);
        return view;
    }

    private void InitAdMob(View view) {
        try
        {
            MobileAds.initialize(getActivity().getApplicationContext(),  getResources().getString(R.string.admob_pub_id));
            AdView adViewBanner = view.findViewById(R.id.bannerAdmobFragment);
            AdRequest request = new AdRequest.Builder().build();
            adViewBanner.loadAd(request);
        }
        catch (Exception e)
        {
            String sr = e.getMessage();
        }

    }
}

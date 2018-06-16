package com.devprogram.aliaspro;


import android.app.Activity;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ComandItemFragment extends Fragment {

    TextView tvNameComand;
   Button btnDeleteComand;
   String nameComand;
    public ComandItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_comand_item, container, false);
       nameComand = getArguments().getString("comandName");
      tvNameComand = view.findViewById(R.id.tvComandName);
      tvNameComand.setText(nameComand);
        btnDeleteComand = view.findViewById(R.id.btnRemoveComand);
        btnDeleteComand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRemoveComand_onClick();
            }
        });
      return view;
    }


    public void btnRemoveComand_onClick() {
       getActivity().getFragmentManager().beginTransaction().remove(this).commit();
        ((SettingsGameActivity)getActivity()).RemoveComand( nameComand);
    }
}

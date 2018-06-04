package com.devprogram.aliaspro;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComandsActivity extends AppCompatActivity {

    ArrayList<String> listComandsDefault = new ArrayList<String>(){{
        add("Мангусты");
        add("Шоколадные зайцы");}};


    ArrayList<String> listComands = new ArrayList<String>(){{
                add("Орлы");
                add("Непобедимые улитки");
                add("Ромашки");
                add("Дикие волки");
                add("Знатоки");
    }} ;


    ComandsAdapter adapter;
    ListView list;

    TextView tvTime;
    TextView tvWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comands);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new ComandsAdapter(this,listComandsDefault);

        list = findViewById(R.id.lvComands);
        list.setAdapter(adapter);

        tvTime = findViewById(R.id.tvTimeSet);
        tvWord = findViewById(R.id.tvWordSet);

        SeekBar sbTime = findViewById(R.id.sbTime);
        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvTime.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar sbWord = findViewById(R.id.sbWord);
        sbWord.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvWord.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }


   int index=0;
   public void btnAddComand_click(View v)
   {
       if(index<listComands.size())
       {
           listComandsDefault.add(listComands.get(index));
           adapter.notifyDataSetChanged();
           index++;
       }

   }

    public void btnNextToChooseWords_Click(View v)
    {

    }

}

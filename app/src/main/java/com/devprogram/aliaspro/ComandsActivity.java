package com.devprogram.aliaspro;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComandsActivity extends AppCompatActivity {

    ArrayList<String> listComands = new ArrayList<String>(){{
        add("Мангусты");
                add("Шоколадные зайцы");
                add("Орлы");
                add("Непобедимые улитки");
                add("Ромашки");
                add("Дикие волки");
                add("Знатоки");
    }} ;
    ComandsAdapter adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comands);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new ComandsAdapter(this,listComands);

        list = findViewById(R.id.lvComands);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }


}

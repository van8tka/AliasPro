package com.devprogram.aliaspro;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingsGameActivity extends AppCompatActivity {




    ArrayList<String> listComands = new ArrayList<String>(){{
                add("Орлы");
                add("Непобедимые улитки");
                add("Ромашки");
                add("Дикие волки");
                add("Знатоки");
                add("Мангусты");
                add("Шоколадные зайцы");
    }} ;





    TextView tvTime;
    TextView tvWord;
    LinearLayout containerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        containerView = findViewById(R.id.llContainerComandItemsFragment);
        CreateDefaultComands();
        tvTime = findViewById(R.id.tvTimeSet);
        tvWord = findViewById(R.id.tvWordSet);
    }
//for create fragments default with comands
    private void CreateDefaultComands()
    {
        CreateItemFragment(listComands.get(0));
        CreateItemFragment(listComands.get(1));
    }
//create fragments command
    private void CreateItemFragment(String comandName) {
        Bundle bnd= new Bundle();
        bnd.putString("comandName",comandName);
        ComandItemFragment fr = new ComandItemFragment();
        FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
        transaction.add(containerView.getId(), fr,"FragmentComandDefault");
        transaction.commit();
        fr.setArguments(bnd);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }


   int index=2;
   public void btnAddComand_click(View v)
   {
       if(index<listComands.size())
       {
           CreateItemFragment(listComands.get(index));
           index++;
       }
   }

   public void RemoveComand(String name)
   {
       index--;
   }


    public void btnNextToChooseWords_Click(View v)
    {
        Toast.makeText(this,"Play the game",Toast.LENGTH_LONG).show();
    }

    public void addDictionaryToGame_Click(View view)
    {
        Toast.makeText(this,"Choose dictionary to play the game",Toast.LENGTH_LONG).show();
    }

    int increment = 10;
   //word count
    public void minusWords_onClick(View view) {

       String count = tvWord.getText().toString();
       int c = Integer.parseInt(count);
       if(c>10)
       {
           c-=increment;
           tvWord.setText(String.valueOf(c));
       }
    }
    public void plusWords_onClick(View view) {

        String count = tvWord.getText().toString();
        int c = Integer.parseInt(count);
        if(c<1000)
        {
            c+=increment;
            tvWord.setText(String.valueOf(c));
        }
    }
    //second count
    public void minusSeconds_onClick(View view) {

        String count = tvTime.getText().toString();
        int c = Integer.parseInt(count);
        if(c>10)
        {
            c-=increment;
            tvTime.setText(String.valueOf(c));
        }
    }
    public void plusSeconds_onClick(View view) {

        String count = tvTime.getText().toString();
        int c = Integer.parseInt(count);
        if(c<1000)
        {
            c+=increment;
            tvTime.setText(String.valueOf(c));
        }
    }
}

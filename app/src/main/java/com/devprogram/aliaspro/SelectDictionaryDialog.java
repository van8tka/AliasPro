package com.devprogram.aliaspro;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.devprogram.aliaspro.DAL.Implementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Dictionary;
import com.devprogram.aliaspro.Models.Difficulty;
import com.devprogram.aliaspro.Models.Language;


import java.util.List;



public class SelectDictionaryDialog extends Dialog {

 ListView listView;
 IDbService dbService;
 Activity activity;
 TextView tvSelectedDictionaryName;

 public SelectDictionaryDialog(@NonNull Activity activity, IDbService dbService, TextView tvSelectedDictionaryName)
 {
     super(activity);
     this.activity = activity;
     this.dbService = dbService;
     this.tvSelectedDictionaryName = tvSelectedDictionaryName;
 }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dictionary_list);
            listView = findViewById(R.id.lvdictionary);
          //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            dbService = new DbService();
            List<Dictionary> listDictionary = dbService.getEDictionaryService().getDicitionaries();
            DictionaryViewAdapter adapter = new DictionaryViewAdapter(this.getContext(),listDictionary, dbService);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ((SettingsGameActivity)activity).dictionary = listDictionary.get(position);
                    tvSelectedDictionaryName.setText(listDictionary.get(position).getName());
                     cancel();
                }
            });
            Button btnCancel = findViewById(R.id.btnBackDialog);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
        }
        catch (Exception er)
        {
            Crashlytics.logException(er);
            Log.e("Create_SEL_DIC", er.getMessage());
        }
    }

}

////
////ADAPTER DICTIONARY LIST
////


 class DictionaryViewAdapter extends BaseAdapter {

    private final List<Dictionary> items;
    private final Context context;
    private IDbService dbService;

    public DictionaryViewAdapter(Context context,List<Dictionary> items, IDbService dbService) {
        this.items = items;
        this.context = context;
        this.dbService=dbService;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try{
            View view = convertView;
            Dictionary dictionary = (Dictionary)getItem(position);
            Language langDictionary = dbService.getELanguageService().getLanguage(dictionary.getLanguage());
            Difficulty difficultyDictionary = dbService.getEDifficultyService().getDifficulty(dictionary.getDifficulty());
            if(view==null)
            {
                view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_dictionary, parent, false);
            }
            TextView name = view.findViewById(R.id.tvDictionaryName);
            TextView description = view.findViewById(R.id.tvDictionaryDescription);
            ImageView lang = view.findViewById(R.id.imgDictionaryLanguage);
            TextView difficulty = view.findViewById(R.id.tvDictionaryDifficulty);
            TextView count = view.findViewById(R.id.tvDictionaryCounts);
            TextView price = view.findViewById(R.id.tvDictionaryPrice);
            ImageView img = view.findViewById(R.id.imgDictionary);
            int idImg = context.getResources().getIdentifier(dictionary.getAvatar(),"drawable",context.getPackageName());
            img.setImageResource(idImg);
            name.setText(dictionary.getName());
            description.setText(dictionary.getDescription());
            int idImgLang = context.getResources().getIdentifier(langDictionary.getAvatar(),"drawable",context.getPackageName());
            lang.setImageResource(idImgLang);
            difficulty.setText(difficultyDictionary.getName());
            int countWords = dbService.getEDictionaryService().getWordsCount(dictionary.getIddictionary());
            count.setText( Integer.toString(countWords));
            if(dictionary.getPrice()!=null){
                price.setText(dictionary.getPrice()+"$");
            }
            else
            {
                price.setText("");
            }
            return view;
        }
        catch (Exception er)
        {
            Crashlytics.logException(er);
            Log.e("SEL_DICT", er.getMessage());
            return  convertView;
        }
    }
}



package com.devprogram.aliaspro;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.Models.Dictionary;


import java.util.List;


public class DictionaryActivity  extends AppCompatActivity  {



 ListView listView;
 DbService dbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_list);
        listView = findViewById(R.id.lvdictionary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<Dictionary> listDictionary = dbService.getEDictionaryService().getDicitionaries();
        listView.setAdapter(new DictionaryViewAdapter(this,listDictionary));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onSupportNavigateUp();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }

    @Override
    public void onDestroy()
    {
        dbService.Close();
        super.onDestroy();
    }
}

////
////ADAPTER DICTIONARY LIST
////


 class DictionaryViewAdapter extends BaseAdapter {

    private final List<Dictionary> items;
    private final Context context;

    public DictionaryViewAdapter(Context context,List<Dictionary> items) {
        this.items = items;
        this.context = context;
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
        View view = convertView;
        Dictionary dictionary = (Dictionary)getItem(position);
        if(view==null)
        {
            view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.row_dictionary, parent, false);
        }

        TextView name = view.findViewById(R.id.tvDictionaryName);
        TextView description = view.findViewById(R.id.tvDictionaryDescription);
        TextView lang = view.findViewById(R.id.tvDictionaryLanguage);
        TextView difficulty = view.findViewById(R.id.tvDictionaryDifficulty);
        TextView count = view.findViewById(R.id.tvDictionaryCounts);
        TextView price = view.findViewById(R.id.tvDictionaryPrice);
        ImageView img = view.findViewById(R.id.imgDictionary);
        int idImg = context.getResources().getIdentifier(dictionary.getAvatar(),"drawable",context.getPackageName());
        img.setImageResource(idImg);

        name.setText(dictionary.getName());
        description.setText(dictionary.getDescription());
        lang.setText(dictionary.getLanguage().getName());
        difficulty.setText(dictionary.getDifficulty().getName());
        count.setText(dictionary.getCountWords());
        if(dictionary.getPrice()==null){
            price.setText("free");
        }
        else
        {
            price.setText(dictionary.getPrice());
        }
        return view;
    }
}



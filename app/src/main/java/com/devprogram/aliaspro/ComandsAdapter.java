package com.devprogram.aliaspro;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ComandsAdapter extends BaseAdapter implements ListAdapter {

    Context context;
    ArrayList<String> listComands;

    public ComandsAdapter(Context context, ArrayList<String> listComands) {
        this.context = context;
        this.listComands = listComands;
    }

    @Override
    public int getCount() {
        return listComands.size();
    }

    @Override
    public Object getItem(int position) {
        return listComands.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final String item = getItem(position).toString();

        View view = convertView;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_comand_name, null);
        }
        TextView tvName = view.findViewById(R.id.tvComandName);
        tvName.setText(item);
        Button btnDell = view.findViewById(R.id.btnRemoveComand);

      if(getCount()>2)
      {
          btnDell.setVisibility(View.VISIBLE);
      }
      else
      {
          btnDell.setVisibility(View.INVISIBLE);
      }

        btnDell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listComands.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}

package com.devprogram.aliaspro;

import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.devprogram.aliaspro.DAL.Emplementations.DbService;
import com.devprogram.aliaspro.DAL.Interfaces.IDbService;
import com.devprogram.aliaspro.Models.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.realm.RealmResults;

public class DialogFragmentSetTeam extends DialogFragment implements View.OnClickListener{

    Team selectedTeam;
    IDbService dbService;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
          View v = inflater.inflate(R.layout.dialogfragment_teamsetforgame,null);
          dbService = new DbService();
          v.findViewById(R.id.btnBackTeam).setOnClickListener(this);
          final ListView listView = v.findViewById(R.id.lvSetTeamList);
         List<Team> inGameTeam =  ((SettingsGameActivity)this.getActivity()).teamListInGame;
         List<Team> listTeams =  dbService.getETeamService().getTeams();
            for(int i=0;i<inGameTeam.size();i++)
            {
                for (int j = 0;j<listTeams.size();j++)
                {
                    if( listTeams.get(j).getIdteam().equals(inGameTeam.get(i).getIdteam())) {
                        listTeams.remove(j);
                    }

                }
            }
        listView.setAdapter(new ListTeamAdapter(this.getActivity(),listTeams));
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        SettingsGameActivity currentAct = ((SettingsGameActivity)this.getActivity());
          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 selectedTeam = listTeams.get(position);
                 listView.setSelected(true);
                 currentAct.AddComand(selectedTeam);
                 dismiss();
             }
         });
         return v;
    }

    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.btnBackTeam)
       {
           dismiss();
       }
    }
    @Override
    public void onDestroy()
    {
        dbService.CloseDb();
        super.onDestroy();
    }
}





/////
///// ADAPTER TEAM LIST
/////



class ListTeamAdapter extends BaseAdapter
{
    Context context;
    List<Team> items;

    public ListTeamAdapter(Context context, List<Team> items)
    {
        this.context = context;
        this.items = items;
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
        View v = convertView;
        Team team = (Team)getItem(position);
        if(v==null)
        {
            v = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.fragment_comand_item,parent,false);
        }
        ImageView imgAvatar = v.findViewById(R.id.imgTeamAvatar);
        TextView tvNameTeam = v.findViewById(R.id.tvComandName);
        v.findViewById(R.id.btnRemoveComand).setVisibility(View.GONE);
        tvNameTeam.setText(team.getName());
        int idImg = context.getResources().getIdentifier(team.getAvatar(),"drawable",context.getPackageName());
        imgAvatar.setImageResource(idImg);
        return v;
    }



}

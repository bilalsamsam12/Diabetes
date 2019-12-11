package com.example.diabetes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {
    ArrayList list= new ArrayList();
    public ListAdapter( Context context, int resource) {
        super(context, resource);
    }
    static class LayoutHandler{
        TextView type,val,dat,mes,fas;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {


        View row=convertView;
        LayoutHandler layouthandler;
        if (row == null) {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            layouthandler=new LayoutHandler();
            layouthandler.dat=(TextView)row.findViewById(R.id.date);
            layouthandler.type=(TextView)row.findViewById(R.id.typep);
            layouthandler.fas=(TextView)row.findViewById(R.id.fastep);
            layouthandler.val=(TextView)row.findViewById(R.id.valuep);
            layouthandler.mes=(TextView)row.findViewById(R.id.mesp);


        }else{
            layouthandler= (LayoutHandler) row.getTag();

        }
        row.setTag(layouthandler);
        Dataprovider dataprovider = (Dataprovider) this.getItem(position);
        layouthandler.type.setText(""+dataprovider.getType());
        layouthandler.val.setText(""+dataprovider.getValue()+"  mg/L");
        layouthandler.dat.setText(dataprovider.getDate());
        layouthandler.mes.setText(dataprovider.getMess());
        layouthandler.fas.setText(dataprovider.getFast());

        return row;

    }
}

package com.kaivanshah.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kaivanrasiklal.s on 06-06-2017.
 */

public class MyAdapter extends BaseAdapter {

    int TotalCount;
    Context myContext;
    public ArrayList<ToDoList_CLASS> ToDoListColl;

    public MyAdapter(int Count, Context oContext, ArrayList<ToDoList_CLASS> oToDoListColl)
    {
        TotalCount = Count;
        myContext =  oContext;
        ToDoListColl = oToDoListColl;
    }

    public void UpdateToDoListColl(ArrayList<ToDoList_CLASS> newlist) {
        ToDoListColl.clear();
        ToDoListColl.addAll(newlist);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return TotalCount;
    }

    @Override
    public Object getItem(int position) {
        return ToDoListColl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(myContext);
            row = inflater.inflate(R.layout.list_row, parent, false);
        }
        TextView tv_DateTitle = (TextView)(row.findViewById(R.id.tv_DateTitle));
        TextView tv_Date = (TextView)(row.findViewById(R.id.tv_Date));
        TextView tv_Title = (TextView)(row.findViewById(R.id.tv_Title));
        TextView tv_Desc = (TextView)(row.findViewById(R.id.tv_Desc));
        ToDoList_CLASS objToDoList = (ToDoList_CLASS) getItem(position);
        ToDoList_CLASS objCustomer = (ToDoList_CLASS) getItem(position);
        tv_DateTitle.setText(objCustomer.getDueDate());
        tv_Date.setText(objCustomer.getDueDate());
        tv_Title.setText(objCustomer.getTitle());
        tv_Desc.setText(objCustomer.getDescription());

        return  row;
    }
}

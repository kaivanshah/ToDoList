package com.kaivanshah.todolist;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static com.kaivanshah.todolist.R.id.btn_Save;

public class MainActivity extends AppCompatActivity  {

    private ArrayList<ToDoList_CLASS> oToDoList;
    private MyAdapter oAdapter;

    private ListView lv_Customers;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_Customers = (ListView) this.findViewById(R.id.lv_Customers);

        db = new DBHelper(this);
        oToDoList = db.getAllItems();

        oAdapter = new MyAdapter(oToDoList.size(), this, oToDoList);
        lv_Customers.setAdapter(oAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,Menu.NONE,0, "Add").setIcon(R.drawable.add_new_task).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View dialogView = inflater.inflate(R.layout.list_item, null);

                final AlertDialog alert =  new AlertDialog.Builder(MainActivity.this).create();

                final EditText DTitle = (EditText) dialogView.findViewById(R.id.tv_DTitle);
                final EditText DDesc = (EditText) dialogView.findViewById(R.id.tv_DDesc);
                final DatePicker DDue_Date = (DatePicker) dialogView.findViewById(R.id.dp_DueDate);
                final Button  btn_Save = (Button) (dialogView.findViewById(R.id.btn_Save));
                final Button  btn_Cancel = (Button) (dialogView.findViewById(R.id.btn_Cancel));




                btn_Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int day = DDue_Date.getDayOfMonth();
                        int month = DDue_Date.getMonth() + 1;
                        int year = DDue_Date.getYear();


                        final String formatedDate = day + "/" + month + "/" + year;


                        ToDoList_CLASS obj=new ToDoList_CLASS(1, DTitle.getText().toString(), DDesc.getText().toString(),formatedDate,1);
                        db.addToDoItem(obj);
                        oToDoList.add(obj);
                        oAdapter.ToDoListColl = oToDoList;
                        oAdapter.TotalCount = oToDoList.size();
                        oAdapter.notifyDataSetChanged();
                        alert.hide();
                    }
                });



                btn_Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.hide();
                    }
                });

                alert.setView(dialogView);
                alert.show();
                return true;
            }
        }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(Menu.NONE,Menu.NONE,0, "Completed Tasks").setIcon(R.drawable.complete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }
}

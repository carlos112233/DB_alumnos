package com.wanosoft.db_student;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DinamicTable extends AppCompatActivity {
TableLayout TL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinamic_table);

        TL=(TableLayout) findViewById(R.id.Tlayout);

        TableRow tr_head= new TableRow(this);
        tr_head.setId(123);
        tr_head.setBackgroundColor(Color.BLACK);
        tr_head.setLayoutParams(new ActionBar.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));

        TextView label = new TextView(this);
        label.setText(124);
        label.setText("NC");
        label.setTextColor(Color.WHITE);
        label.setPadding(5,5,5,5);
        tr_head.addView(label);

        TextView label2 = new TextView(this);
        label2.setText(125);
        label2.setText("NC");
        label2.setTextColor(Color.WHITE);
        label2.setPadding(5,5,5,5);
        tr_head.addView(label2);

        TL.addView(tr_head, new  TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        int count =0;
        AdminSliteHelper admin= new AdminSliteHelper(this, "DB_student",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from student",null);
        if(cursor.moveToFirst()){
            while (cursor.isAfterLast()==false){
                String nc =cursor.getString(cursor.getColumnIndex("num_control"));
                String name =cursor.getString(cursor.getColumnIndex("name"));
                TableRow tr= new TableRow(this);
                if (count%2!=0)tr.setBackgroundColor(Color.GRAY);
                else tr.setBackgroundColor(Color.DKGRAY);

                tr.setId(100+count);
                tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));

                //crear a textView to add NC
                TextView labelNC = new TextView(this);
                labelNC.setText(126);
                labelNC.setText(nc);
                labelNC.setTextColor(Color.WHITE);
                labelNC.setPadding(2,0,5,0);
                tr_head.addView(labelNC);

                TextView labelName = new TextView(this);
                labelName.setText(127);
                labelName.setText(name);
                labelName.setTextColor(Color.WHITE);
                labelName.setPadding(2,0,5,0);
                tr_head.addView(labelName);

                TL.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));
                count++;
                cursor.moveToFirst();
            }
            }


    }
}

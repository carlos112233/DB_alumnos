package com.wanosoft.db_student;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Relave extends AppCompatActivity {
 TextView tlable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relave);
        tlable= (TextView) findViewById(R.id.textView2);

        AdminSliteHelper admin = new AdminSliteHelper(this, "DB_students", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from student",null);
        String label="";
        if(cursor.moveToFirst()){
            while (cursor.isAfterLast()==false){
                label+=cursor.getColumnName(cursor.getColumnIndex("num_control"));
                label+=cursor.getColumnName(cursor.getColumnIndex("name"));
                label+=cursor.getColumnName(cursor.getColumnIndex("major"));
                label+=cursor.getColumnName(cursor.getColumnIndex("year"))+"\n";


                cursor.moveToFirst();
            }

        }
        tlable.setText(label);
    }
}

package com.wanosoft.db_student;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Region;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    EditText name, major, year_birth, number_control;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editText2);
        major = (EditText) findViewById(R.id.editText3);
        year_birth = (EditText) findViewById(R.id.editText4);
        number_control = (EditText) findViewById(R.id.editText);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void add(View view) {
        AdminSliteHelper admin = new AdminSliteHelper(this, "DB_students", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String n1 = name.getText().toString();
        String M = major.getText().toString();
        String nc = number_control.getText().toString();
        String yb = year_birth.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("numer_control", nc);//nombre del atributo
        registro.put("year", yb);
        registro.put("major", M);
        registro.put("name", n1);

        db.insert("student", null, registro);
        db.close();
        number_control.setText("");
        major.setText("");
        year_birth.setText("");
        name.setText("");
        Toast.makeText(this, "Insert Complete", Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        AdminSliteHelper admin = new AdminSliteHelper(this, "DB_students", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String nc = number_control.getText().toString();

        int cant=db.delete("student","numer_control="+nc,null);

        if (cant==1) {
            Toast.makeText(this,"Success delete", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"error delete", Toast.LENGTH_SHORT).show();
        }

    }

    public void update(View view) {
        AdminSliteHelper admin = new AdminSliteHelper(this, "DB_students", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String n1 = name.getText().toString();
        String M = major.getText().toString();
        String nc = number_control.getText().toString();
        String yb = year_birth.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("number_control", nc);//nombre del atributo
        registro.put("year", yb);
        registro.put("major", M);
        registro.put("name", n1);

        int cantidad= db.update("student",registro,"numer_control="+nc,null);
        db.close();

        if (cantidad==1) {
            Toast.makeText(this,"Success delete", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"error delete", Toast.LENGTH_SHORT).show();
        }

    }

    public void query(View view) {
        AdminSliteHelper admin = new AdminSliteHelper(this, "DB_students", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String nc = number_control.getText().toString();
        Cursor fila = db.rawQuery("select name, major from student where number_control=" + nc, null);

        if (fila.moveToFirst()) {
            name.setText(fila.getString(0));
            major.setText(fila.getString(1));
        } else {
            Toast.makeText(this, "Error query", Toast.LENGTH_SHORT).show();

        }
        db.close();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void next(View v){
        Intent i = new Intent(this, Relave.class);
        startActivity(i);

    }

}

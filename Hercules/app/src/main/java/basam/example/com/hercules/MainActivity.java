package basam.example.com.hercules;

import android.database.Cursor;
import android.content.Intent;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    Button prop, plan;
    private ArrayList<String> name;
    //Переменная для работы с БД
    private BDHelper mDBHelper;
    private SQLiteDatabase mDb;
    // Параметры для таблиц
    EditText high, weight1, exp3, wrist;
    //Базовые параметры
    EditText neck, biceps, forearm, chest, waist, basin, hips, shin;
    TextView textView;

    //БД
    //BDHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new BDHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        prop = (Button) findViewById(R.id.prop);
        // prop.setOnClickListener(this);
        plan = (Button) findViewById(R.id.plan);
        //plan.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.textView);
        high = (EditText) findViewById(R.id.high);
        weight1 = (EditText) findViewById(R.id.ex);
        name = new ArrayList<String>();
        //dbHelper = new BDHelper(this);
        Cursor cursor = mDb.rawQuery("SELECT * FROM stand", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            name.add(cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        textView.setText(name.get(1));



    }
}
      /*  prop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = "";

                Cursor cursor = mDb.rawQuery("SELECT * FROM stand", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(1) + " | ";
                    cursor.moveToNext();
                }
                cursor.close();
                textView.setText(product);
            }
        });

    }

}
 /*   @Override
    public void onClick(View v){
        String h = high.getText().toString();
        String w = weight1.getText().toString();
        String value0 = exp3.getText().toString();
        float e = Float.parseFloat(value0);
        String value1 = wrist.getText().toString();
        float r = Float.parseFloat(value1);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()){
            case  R.id.prop:

            break;
            case  R.id.plan:

            break;
        }

    }
}*/

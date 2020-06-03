package basam.example.com.hercules;

//import android.database.Cursor;
//import android.content.Intent;
//import android.content.ContentValues;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    Button prop, plan;
    //Базовые параметры
    EditText high, weight, exp, wrist;
   //стартовые, желаемые, время в неделях
    EditText neck, biceps, forearm, chest, waist, basin, hips, shin;
    EditText biceps2, forearm2, chest2, waist2, basin2, hips2, shin2;
    EditText neck3,biceps3, forearm3, chest3, waist3, basin3, hips3, shin3;
    TextView neck2;
    boolean isPressed = false;
    double k;
    double[] p1;
    //БД
    BDHelper helper = new DbEmulation();
    //ArrayList<Integer> list = helper.GetData();

    TimeCalc cTime = new TimeCalc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        high =  findViewById(R.id.high);
        weight =  findViewById(R.id.ex);
        exp =  findViewById(R.id.exp3);
        wrist =  findViewById(R.id.wrist);

        neck =  findViewById(R.id.neck);
        biceps =  findViewById(R.id.biceps);
        forearm =  findViewById(R.id.forearm);
        chest =  findViewById(R.id.chest);
        waist =  findViewById(R.id.waist);
        basin =  findViewById(R.id.basin);
        hips =  findViewById(R.id.hips);
        shin =  findViewById(R.id.shin);

        neck2 =  findViewById(R.id.neck2);
        biceps2 =  findViewById(R.id.biceps2);
        forearm2 =  findViewById(R.id.forearm2);
        chest2 =  findViewById(R.id.chest2);
        waist2 =  findViewById(R.id.waist2);
        basin2 =  findViewById(R.id.basin2);
        hips2 =  findViewById(R.id.hips2);
        shin2 =  findViewById(R.id.shin2);

        neck3 =  findViewById(R.id.neck3);
        biceps3 =  findViewById(R.id.biceps3);
        forearm3 =  findViewById(R.id.forearm3);
        chest3 =  findViewById(R.id.chest3);
        waist3 =  findViewById(R.id.waist3);
        basin3 =  findViewById(R.id.basin3);
        hips3 =  findViewById(R.id.hips3);
        shin3 =  findViewById(R.id.shin3);


        prop =  findViewById(R.id.prop);
        plan =  findViewById(R.id.plan);

        // нахождение пропорций
        OnClickListener planBtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String e1 = exp.getText().toString();
                Integer e = Integer.valueOf(e1);
                String wr = wrist.getText().toString();
                Double w = Double.valueOf(wr);

                //заполнение массива базовых параметров для передачи
                double[] p2 = new double[8];
                String r = neck.getText().toString();
                p2[0]= Double.valueOf(r);
                r = biceps.getText().toString();
                p2[1]= Double.valueOf(r);
                r = forearm.getText().toString();
                p2[2]= Double.valueOf(r);
                r = chest.getText().toString();
                p2[3]= Double.valueOf(r);
                r = waist.getText().toString();
                p2[4]= Double.valueOf(r);
                r = basin.getText().toString();
                p2[5]= Double.valueOf(r);
                r = hips.getText().toString();
                p2[6]= Double.valueOf(r);
                r = shin.getText().toString();
                p2[7]= Double.valueOf(r);

                //запрос на возврат времени
                double[] time = cTime.calcTime(p1,p2,w,e);

                String d = new DecimalFormat("#0.00").format(time[0]);
                //заполнение ряда пропорции из БД
                r = String.valueOf(d);
                neck3.setText(r);
                d = new DecimalFormat("#0.00").format(time[1]);
                r = String.valueOf(d);
                biceps3.setText(r);
                d = new DecimalFormat("#0.00").format(time[2]);
                r = String.valueOf(d);
                forearm3.setText(r);
                d = new DecimalFormat("#0.00").format(time[3]);
                r = String.valueOf(d);
                chest3.setText(r);
                d = new DecimalFormat("#0.00").format(time[4]);
                r = String.valueOf(d);
                waist3.setText(r);
                d = new DecimalFormat("#0.00").format(time[5]);
                r = String.valueOf(d);
                basin3.setText(r);
                d = new DecimalFormat("#0.00").format(time[6]);
                r = String.valueOf(d);
                hips3.setText(r);
                d = new DecimalFormat("#0.00").format(time[7]);
                r = String.valueOf(d);
                shin3.setText(r);
            }
        };

        // составление прогноза
        OnClickListener propBtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String h1 = high.getText().toString();
                Double h = Double.valueOf(h1);
                String w1 = weight.getText().toString();
                Double w = Double.valueOf(w1);

                //кэф вес/рост для поиска в таблице
                k = w/h;

                //запрос на возврат значений по кэфу k
                double[] p = helper.GetData(k);
                p1 = p;
                //заполнение ряда пропорции из БД
                String r = String.valueOf(p[0]);
                neck2.setText(r);
                r = String.valueOf(p[1]);
                biceps2.setText(r);
                r = String.valueOf(p[2]);
                forearm2.setText(r);
                r = String.valueOf(p[3]);
                chest2.setText(r);
                r = String.valueOf(p[4]);
                waist2.setText(r);
                r = String.valueOf(p[5]);
                basin2.setText(r);
                r = String.valueOf(p[6]);
                hips2.setText(r);
                r = String.valueOf(p[7]);
                shin2.setText(r);
                isPressed = true;
            }
        };

        // кнопка нахождения пропорций
        prop.setOnClickListener(propBtn);
            // кнопка нахождения плана
            plan.setOnClickListener(planBtn);

    }
}




    /*
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

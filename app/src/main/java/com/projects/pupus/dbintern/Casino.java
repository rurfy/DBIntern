package com.projects.pupus.dbintern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Casino extends AppCompatActivity {

    private String TAG = Casino.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sperrliste);

        Bundle b = getIntent().getExtras();
        String name = b.getString("titel");

        Button zurueck = (Button) findViewById(R.id.zurueck);
        TextView titel = (TextView) findViewById(R.id.titel);

        titel.setText(name);


        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

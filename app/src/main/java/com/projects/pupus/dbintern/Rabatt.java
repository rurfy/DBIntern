package com.projects.pupus.dbintern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Rabatt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casrab);

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

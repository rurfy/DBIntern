package com.projects.pupus.dbintern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Anmerkung extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anmerkung);

        Button zurueck = findViewById(R.id.zurueck);
        TextView title = findViewById(R.id.titel);

        zurueck.setText(R.string.ueberdieapp);
        title.setText("");

        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

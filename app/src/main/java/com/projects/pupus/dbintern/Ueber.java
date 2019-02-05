package com.projects.pupus.dbintern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ueber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ueber);

        Button zurueck = (Button) findViewById(R.id.zurueck);
        TextView title = (TextView) findViewById(R.id.titel);

        zurueck.setText("Hauptmenü");
        title.setText("Über diese App");

        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button anmerkung = (Button) findViewById(R.id.anmerkungbutton);
        Button version = (Button) findViewById(R.id.versionenbutton);

        anmerkung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Ueber.this, Anmerkung.class);
                startActivity(intent);
            }
        });

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ueber.this, Changelog.class);
                startActivity(intent);
            }
        });
    }
}

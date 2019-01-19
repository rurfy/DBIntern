package com.projects.pupus.dbintern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Hauptmenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hauptmenue);

        TextView sperrliste = (TextView) findViewById(R.id.sperrliste);
        TextView casinos = (TextView) findViewById(R.id.casinos);
        TextView rabatte = (TextView) findViewById(R.id.rabatte);
        TextView reisemarkt = (TextView) findViewById(R.id.reisemarkt);
        TextView planet = (TextView) findViewById(R.id.planet);
        TextView ueber = (TextView) findViewById(R.id.ueber);

        sperrliste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hauptmenue.this, Sperrliste.class);
                startActivity(intent);
            }
        });
        casinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("titel", getString(R.string.casinos));
                Intent intent = new Intent (Hauptmenue.this, Casino.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        rabatte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("titel", getString(R.string.rabatte));
                Intent intent = new Intent (Hauptmenue.this, Rabatt.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}

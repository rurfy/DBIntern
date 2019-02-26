package com.projects.pupus.dbintern;

import android.content.Intent;
import android.net.Uri;
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

        TextView sperrliste = findViewById(R.id.entwicklerhg);
        TextView casinos = findViewById(R.id.kontakthg);
        TextView rabatte = findViewById(R.id.versionhg);
        TextView reisemarkt = findViewById(R.id.anmerkungenhg);
        TextView planet = findViewById(R.id.planet);
        final TextView ueber = findViewById(R.id.ueber);

        Button zurueck = findViewById(R.id.zurueck);
        zurueck.setVisibility(View.GONE);


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
                Intent intent = new Intent (Hauptmenue.this, Casino.class);
                startActivity(intent);
            }
        });
        rabatte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Hauptmenue.this, Rabatt.class);
                startActivity(intent);
            }
        });
        planet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse("https://db-planet.deutschebahn.com/home/startseite"));
                startActivity(intent);
            }
        });
        reisemarkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.db-reisemarkt.de/service/security/reisemarkt/2344194"));
                startActivity(intent);
            }
        });
        ueber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hauptmenue.this, Ueber.class);
                startActivity(intent);
            }
        });
    }
}

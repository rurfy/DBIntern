package com.projects.pupus.dbintern;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Hauptmenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hauptmenue);

        TextView sperrliste = (TextView) findViewById(R.id.entwicklerhg);
        TextView casinos = (TextView) findViewById(R.id.kontakthg);
        TextView rabatte = (TextView) findViewById(R.id.versionhg);
        TextView reisemarkt = (TextView) findViewById(R.id.anmerkungenhg);
        TextView planet = (TextView) findViewById(R.id.planet);
        final TextView ueber = (TextView) findViewById(R.id.ueber);

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

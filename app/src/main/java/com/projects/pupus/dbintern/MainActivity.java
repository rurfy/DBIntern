package com.projects.pupus.dbintern;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText benutzer =(EditText) findViewById(R.id.benutzername2);
        final EditText passwort = (EditText) findViewById(R.id.passwort2);
        Button login = (Button) findViewById(R.id.einloggen);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(benutzer.getText().toString().equals("db") && passwort.getText().toString().equals("exklusiv")){
                    System.out.println("korrekt");
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Login fehlgeschlagen!");
                    alertDialog.setMessage("Benutzername und/oder Passwort sind falsch.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok.",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        Button hilfe = (Button) findViewById(R.id.hilfe);
        hilfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Hilfe");
                alertDialog.setMessage("Zum Login nutzen Sie bitte die selben Benutzderdaten, die Sie auch im Reisemarkt oder im Auslastungsradar nutzen.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }
}

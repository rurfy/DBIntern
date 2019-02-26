package com.projects.pupus.dbintern;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.content.res.ResourcesCompat;
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

        final EditText benutzer = findViewById(R.id.benutzername2);
        final EditText passwort = findViewById(R.id.passwort2);
        Button login = findViewById(R.id.einloggen);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(benutzer.getText().toString().equalsIgnoreCase("db") && passwort.getText().toString().equals("exklusiv")){
                    Intent intent = new Intent(MainActivity.this, Hauptmenue.class);
                    startActivity(intent);
                    DeviceID deviceID = new DeviceID();
                    deviceID.execute();
                } else {
                    final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Login fehlgeschlagen!");
                    alertDialog.setMessage("Benutzername und/oder Passwort sind falsch.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Ok.",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface arg0) {
                            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorDeutscheBahn, null));
                        }
                    });
                    alertDialog.show();
                }
            }
        });

        Button hilfe = findViewById(R.id.hilfe);
        hilfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Hilfe");
                alertDialog.setMessage("Zum Login nutzen Sie bitte die selben Benutzderdaten, die Sie auch im Reisemarkt oder im Auslastungsradar nutzen.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Ok.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorDeutscheBahn, null));
                    }
                });
                alertDialog.show();
            }
        });
    }

    private class DeviceID extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String android_id = Settings.Secure.getString(MainActivity.this.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            HttpHandler handler = new HttpHandler();
            handler.makeServiceCall("http://dbintern.appshost.net/api.php?pass=db&db=rabatte&AndroidID=(" + android_id + ")");
            return null;
        }
    }
}

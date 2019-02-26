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

public class Sperrliste extends AppCompatActivity{

    private String TAG = Sperrliste.class.getSimpleName();
    private ListView lvSperrliste;

    ArrayList<HashMap<String, String>> ortList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sperrliste);

        ortList = new ArrayList<>();
        lvSperrliste = findViewById(R.id.list);


        TextView title = findViewById(R.id.titel);
        Button zurueck = findViewById(R.id.zurueck);

        title.setText(R.string.sperrliste);
        zurueck.setText(R.string.hauptmenue);

        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        JSONParser getContacts = new JSONParser(TAG, ortList, new String[]{"ID", "Typ", "Nummer", "Von", "Bis", "Gueltig"}, new String[]{"Zug", "Strecke", "Tage"}, "http://dbintern.appshost.net/api.php?pass=db&db=sperrliste") {
            @Override
            protected void onPostExecute(Void Result) {
                super.onPostExecute(Result);
                ListAdapter adapter = new SimpleAdapter(Sperrliste.this, ortList,
                        R.layout.sperr_list_item, new String[]{ "Zug","Strecke","Tage"},
                        new int[]{R.id.tV_Zug, R.id.tV_Strecke, R.id.tV_Tage});
                lvSperrliste.setAdapter(adapter);
            }

            @Override
            protected void putAll(HashMap<String, String> map, String[] listTags, String[] content) {
                map.put(listTags[0], content[1] + " " + content[2]);
                map.put(listTags[1], content[3] + " >> " + content[4]);
                map.put(listTags[2], content[5]);
            }

        };

        getContacts.execute();
    }

}


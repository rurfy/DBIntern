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

public class SelectedRabatt extends AppCompatActivity {
    private String TAG = SelectedRabatt.class.getSimpleName();
    private ListView lvRabatt;

    ArrayList<HashMap<String, String>> ortList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casrab);


        ortList = new ArrayList<>();
        lvRabatt = (ListView) findViewById(R.id.lvCasrab);

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

        Bundle extras = getIntent().getExtras();
        String ortID = extras.getString("ortID");

        JSONParser getContacts = new com.projects.pupus.dbintern.JSONParser(SelectedRabatt.this, TAG, ortList, new String[] {"ID", "Ort", "Ort_ID", "Name_Geschaeft", "Rabatt", "Legitimation", "Wo"}, new String[] {"Name","Rabatt","Legitimation","Wo"},  "http://dbintern.appshost.net/api.php?pass=db&db=rabatte&ort=" + ortID) {
            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                ListAdapter adapter = new SimpleAdapter(SelectedRabatt.this, ortList,
                        R.layout.rabatt_list_item, new String[]{"Name","Rabatt","Legitimation","Wo"},
                        new int[]{R.id.tVName,R.id.tVRabatt,R.id.tVLegi,R.id.tVWo});
                lvRabatt.setAdapter(adapter);
            }

            @Override
            protected void putAll(HashMap<String, String> map, String[] listTags, String[] content) {
                map.put(listTags[0], content[3]);
                map.put(listTags[1], content[4]);
                map.put(listTags[2], content[5]);
                map.put(listTags[3], content[6]);
            }
        };
        getContacts.execute();


    }
}

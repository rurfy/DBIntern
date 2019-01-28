package com.projects.pupus.dbintern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Casino extends AppCompatActivity {

    private String TAG = Casino.class.getSimpleName();
    private ListView lvCasino;

    ArrayList<HashMap<String, String>> ortList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casrab);

        ortList = new ArrayList<>();
        lvCasino = (ListView) findViewById(R.id.lvCasrab);

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

        lvCasino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                finish();
            }
        });

        JSONParser getContacts = new com.projects.pupus.dbintern.JSONParser(Casino.this, TAG, ortList, new String[] {"Ort", "Ort_ID"}, new String[] {"Ort"},  "http://dbintern.appshost.net/api.php?pass=db&db=casino_ort") {
            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                ListAdapter adapter = new SimpleAdapter(Casino.this, ortList,
                        R.layout.casrab_list_item, new String[]{"Ort"},
                        new int[]{R.id.tvOrt});
                lvCasino.setAdapter(adapter);
            }

            @Override
            protected void putAll(HashMap<String, String> map, String[] listTags, String[] content) {
                map.put(listTags[0], content[0]);
            }
        };
        getContacts.execute();

    }
}

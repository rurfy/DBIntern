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

public class SelectedCasino extends AppCompatActivity {
    private String TAG = SelectedCasino.class.getSimpleName();
    private ListView lvCas;
    private TextView title;

    ArrayList<HashMap<String, String>> ortList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casrab);

        ortList = new ArrayList<>();
        lvCas = (ListView) findViewById(R.id.lvCasrab);

        Button zurueck = (Button) findViewById(R.id.zurueck);
        title = (TextView) findViewById(R.id.titel);

        zurueck.setText("Casinos");

        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        String ortID = extras.getString("ortID");

        JSONParser getContacts = new com.projects.pupus.dbintern.JSONParser(SelectedCasino.this, TAG, ortList, new String[] {"ID", "Ort", "Ort_ID", "Name", "Adresse","Telefon","EMail","OZ_Mo", "OZ_Di", "OZ_Mi", "OZ_Do","OZ_Fr", "OZ_Sa", "OZ_So"}, new String[] {"Name","Adresse","Telefon","EMail","OZ_Mo", "OZ_Di", "OZ_Mi", "OZ_Do","OZ_Fr", "OZ_Sa", "OZ_So"},  "http://dbintern.appshost.net/api.php?pass=db&db=casino&ort=" + ortID) {
            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                ListAdapter adapter = new SimpleAdapter(SelectedCasino.this, ortList,
                        R.layout.casino_list_item, new String[]{"Name","Adresse","Telefon","EMail","OZ_Mo", "OZ_Di", "OZ_Mi", "OZ_Do","OZ_Fr", "OZ_Sa", "OZ_So"},
                        new int[]{R.id.tVSelCas,R.id.tVAdresse,R.id.tVTel,R.id.tVMail,R.id.tVMo,R.id.tVDi,R.id.tVMi,R.id.tVDo,R.id.tVFr,R.id.tVSa,R.id.tVSo});
                lvCas.setAdapter(adapter);
            }

            @Override
            protected void putAll(HashMap<String, String> map, String[] listTags, final String[] content) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        title.setText(content[1]);
                        // Stuff that updates the UI

                    }
                });
                map.put(listTags[0], content[3]);
                map.put(listTags[1], content[4]);
                map.put(listTags[2], content[5]);
                map.put(listTags[3], content[6]);
                map.put(listTags[4], content[7]);
                map.put(listTags[5], content[8]);
                map.put(listTags[6], content[9]);
                map.put(listTags[7], content[10]);
                map.put(listTags[8], content[11]);
                map.put(listTags[9], content[12]);
                map.put(listTags[10], content[13]);
            }
        };
        getContacts.execute();


    }
}

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

public class Changelog extends AppCompatActivity {

    private String TAG = Changelog.class.getSimpleName();
    private ListView lvChangelog;

    ArrayList<HashMap<String, String>> ortList;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version);

        ortList = new ArrayList<>();
        lvChangelog = findViewById(R.id.lvChangelog);

        Button zurueck = findViewById(R.id.zurueck);
        TextView title = findViewById(R.id.titel);

        zurueck.setText(R.string.ueberdieapp);
        title.setText(R.string.changelog);

        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        JSONParser getContacts = new com.projects.pupus.dbintern.JSONParser(TAG, ortList, new String[] {"Version", "ChangeText"}, new String[] {"Version", "Text"},  "http://dbintern.appshost.net/api.php?pass=db&db=Changelog") {
            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                ListAdapter adapter = new SimpleAdapter(Changelog.this, ortList,
                        R.layout.change_list_item, new String[]{ "Version","Text"},
                        new int[]{R.id.tV_Version, R.id.tV_Text});
                lvChangelog.setAdapter(adapter);
            }

            @Override
            protected void putAll(HashMap<String, String> map, String[] listTags, String[] content) {
                map.put(listTags[0], "Version: " + content[0]);
                map.put(listTags[1], content[1]);
            }
        };
        getContacts.execute();
    }

}

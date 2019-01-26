package com.projects.pupus.dbintern;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Changelog extends AppCompatActivity {

    private String TAG = JSONTest.class.getSimpleName();
    private ListView lvChangelog;

    ArrayList<HashMap<String, String>> ortList;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version);

        ortList = new ArrayList<>();
        lvChangelog = (ListView) findViewById(R.id.lvChangelog);

        Button zurueck = (Button) findViewById(R.id.zurueck);

        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Changelog.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://dbintern.appshost.net/api.php?pass=db&db=Changelog";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            //Log.e(TAG, jsonStr);
            if (jsonStr != null) {
                try {
                    // JSONArray is created
                    JSONArray jsonOrte = new JSONArray(jsonStr);

                    // looping through all values
                    for (int i = 0; i < jsonOrte.length(); i++) {
                        JSONObject c = jsonOrte.getJSONObject(i);
                        String version = c.getString("Version");
                        String text = c.getString("ChangeText");

                        // tmp hash map for single object
                        HashMap<String, String> ort = new HashMap<>();

                        // adding each child node to HashMap key => value
                        ort.put("Version", "Version: " + version);
                        ort.put("Text", text);

                        // adding object to arrayList
                        ortList.add(ort);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(Changelog.this, ortList,
                    R.layout.change_list_item, new String[]{ "Version","Text"},
                    new int[]{R.id.tV_Version, R.id.tV_Text});
            lvChangelog.setAdapter(adapter);
        }
    }
}

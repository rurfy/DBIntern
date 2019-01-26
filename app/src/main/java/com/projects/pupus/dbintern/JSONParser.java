package com.projects.pupus.dbintern;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONParser extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String TAG;
    private ArrayList<HashMap<String, String>> ortList;
    private String[] tags;
    private String[] tagList;
    private String url;

    public JSONParser(Context context, String TAG, ArrayList<HashMap<String, String>> ortList, String[] namesOnWebsite, String[] tagsOnList, String url) {
        this.context = context;
        this.TAG = TAG;
        this.ortList = ortList;
        this.tags = namesOnWebsite;
        this.tagList = tagsOnList;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Json Data is downloading", Toast.LENGTH_LONG).show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        //Log.e(TAG, jsonStr);
        if (jsonStr != null) {
            try {
                // JSONArray is created
                JSONArray jsonOrte = new JSONArray(jsonStr);

                String[] content = new String[tags.length];
                // looping through all values
                for (int i = 0; i < jsonOrte.length(); i++) {
                    JSONObject c = jsonOrte.getJSONObject(i);
                    for (int j = 0; j < tags.length; j++) {
                        content[j] = c.getString(tags[j]);
                    }

                    // tmp hash map for single object
                    HashMap<String, String> ort = new HashMap<>();

                    // adding each child node to HashMap key => value
                    putAll(ort, tagList, content);

                    // adding object to arrayList
                    ortList.add(ort);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Json parsing error: " + e.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                    }
//                });

            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(getApplicationContext(),
//                            "Couldn't get json from server. Check LogCat for possible errors!",
//                            Toast.LENGTH_LONG).show();
//                }
//            });
        }

        return null;
    }

    protected void putAll (HashMap<String, String> map, String[] listTags, String[] content) {
    }

}

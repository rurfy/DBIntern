package com.projects.pupus.dbintern;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONParser extends AsyncTask<Void, Void, Void> {

    private String TAG;
    private ArrayList<HashMap<String, String>> objectList;
    private String[] tags;
    private String[] tagList;
    private String url;

    JSONParser(String TAG, ArrayList<HashMap<String, String>> objectList, String[] namesOnWebsite, String[] tagsOnList, String url) {

        this.TAG = TAG;
        this.objectList = objectList;
        this.tags = namesOnWebsite;
        this.tagList = tagsOnList;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e(TAG, "Extracting JSON Data");

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                // JSONArray is created
                JSONArray jsonObjects = new JSONArray(jsonStr);

                String[] content = new String[tags.length];
                // looping through all values
                for (int i = 0; i < jsonObjects.length(); i++) {
                    JSONObject c = jsonObjects.getJSONObject(i);
                    for (int j = 0; j < tags.length; j++) {
                        content[j] = c.getString(tags[j]);
                    }

                    // tmp hash map for single object
                    HashMap<String, String> ort = new HashMap<>();

                    // adding each child node to HashMap key => value
                    putAll(ort, tagList, content);

                    // adding object to arrayList
                    objectList.add(ort);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

        return null;
    }

    protected void putAll (HashMap<String, String> map, String[] listTags, String[] content) {
    }

}

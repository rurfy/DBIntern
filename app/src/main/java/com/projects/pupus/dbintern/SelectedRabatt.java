package com.projects.pupus.dbintern;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectedRabatt extends AppCompatActivity {
    private String TAG = SelectedRabatt.class.getSimpleName();
    private ListView lvRabatt;
    private TextView title;
    private ImageView image;

    ArrayList<HashMap<String, String>> ortList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selrab);


        ortList = new ArrayList<>();
        lvRabatt = (ListView) findViewById(R.id.lvSelRab);

        Button zurueck = (Button) findViewById(R.id.zurueck);
        title = (TextView) findViewById(R.id.titel);
        zurueck.setText("Rabatte");

        zurueck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        image = (ImageView) findViewById(R.id.bahnhofImage);

        Bundle extras = getIntent().getExtras();
        String ortID = extras.getString("ortID");

        new DownloadImageTask(image)
                .execute("http://dbintern.appshost.net/bahnhofsfotos/" + ortID + ".jpg");


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
            }
        };
        getContacts.execute();


    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            double width = size.x;

            bmImage.getLayoutParams().height = ((int) ((double) result.getHeight()*(width/ (double) result.getWidth())));
            bmImage.setImageBitmap(result);
        }
    }
}

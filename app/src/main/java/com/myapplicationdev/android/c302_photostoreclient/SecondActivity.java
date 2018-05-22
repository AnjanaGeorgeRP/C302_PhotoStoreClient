package com.myapplicationdev.android.c302_photostoreclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ListView lvPhotos;
    ArrayList<Photo> alPhotos = new ArrayList<Photo>();
    ArrayAdapter<Photo> aaPhotos;
    Category data;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvPhotos = (ListView) findViewById(R.id.listViewPhotoDetails);
        aaPhotos = new CustomAdapterPhoto(SecondActivity.this, R.layout.row_photo, alPhotos);
        lvPhotos.setAdapter(aaPhotos);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        data = (Category) i.getSerializableExtra("data");
        id = data.getId();
        String url = "https://codemusically.000webhostapp.com/C302_P06_PhotoStoreWS/getPhotoStoreByCategory.php?category_id="+id;
        Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
        // Code for step 1 start
        HttpRequest request = new HttpRequest(url);
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end
    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int photo_id = jsonObj.getInt("photo_id");
                            String title = jsonObj.getString("title");
                            String description = jsonObj.getString("description");
                            String created_by = jsonObj.getString("created_by");
                            String image = jsonObj.getString("image");

                            String displayResults = description + "\n\nCreated by "
                                    + created_by + "\n\n"+ image + "\n";
                            Photo photo = new Photo(photo_id,title,displayResults);
                            alPhotos.add(photo);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    aaPhotos.notifyDataSetChanged();
                }
            };
    // Code for step 2 end
}

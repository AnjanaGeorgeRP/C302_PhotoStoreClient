package com.myapplicationdev.android.c302_photostoreclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvCategories;
    ArrayList<Category> alCategories = new ArrayList<Category>();
    ArrayAdapter<Category> aaCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCategories = (ListView) findViewById(R.id.listViewCategories);
        aaCategories = new CustomAdapter(MainActivity.this, R.layout.row, alCategories);
        lvCategories.setAdapter(aaCategories);

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                Category data = alCategories.get(position);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("https://codemusically.000webhostapp.com/C302_P06_PhotoStoreWS/getCategories.php");
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

                            int categoryId = jsonObj.getInt("category_id");
                            String categoryName = jsonObj.getString("name");
                            String description = jsonObj.getString("description");

                            String displayResults = "Category Id: " + categoryId + "\n\nCategory Name: "
                                    + categoryName + "\n\nDescription: " + description + "\n";
                            Category category = new Category(categoryId,categoryName,description);
                            alCategories.add(category);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    aaCategories.notifyDataSetChanged();
                }
            };
    // Code for step 2 end
}

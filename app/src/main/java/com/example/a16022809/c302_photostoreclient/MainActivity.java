package com.example.a16022809.c302_photostoreclient;

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
    //ArrayList<String> alCategories = new ArrayList<String>();
    ArrayAdapter<Category> aaCategories;
    private ArrayList<Category> category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lvCategories = (ListView) findViewById(R.id.listViewCategories);
        category = new ArrayList<Category>();
        aaCategories = new CategoryAdapter(this, R.layout.category_row, category);
        lvCategories.setAdapter(aaCategories);

        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedCategory = category.get(position).getCategoryId();
                Intent intent = new Intent(MainActivity.this, PhotoDetails.class);
                intent.putExtra("Category",selectedCategory);
                startActivity(intent);

            }
        });


        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://192.168.43.225/C302_P06_PhotoStoreWS/getCategories.php");
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

                            Category category2 = new Category(categoryId, categoryName, description);
                            category.add(category2);
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


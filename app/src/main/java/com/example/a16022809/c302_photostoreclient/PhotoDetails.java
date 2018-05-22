package com.example.a16022809.c302_photostoreclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotoDetails extends AppCompatActivity {

    private ListView lvPhotos;
    //ArrayList<String> alCategories = new ArrayList<String>();
    ArrayAdapter<Photo> aaPhoto;
    private ArrayList<Photo> photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        lvPhotos = (ListView) findViewById(R.id.listViewCategories);
        photo = new ArrayList<Photo>();
        aaPhoto = new PhotoAdapter(this, R.layout.photo_row, photo);
        lvPhotos.setAdapter(aaPhoto);

        Intent i = getIntent();
        int category = i.getIntExtra("Category", 0);
        Log.i("info", "onResume: ");
        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://192.168.43.225/C302_P06_PhotoStoreWS/getPhotoStoreByCategory.php?category_id="+category);
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

                            int photoId = jsonObj.getInt("photo_id");
                            String title = jsonObj.getString("title");
                            String description = jsonObj.getString("description");
                            String image = jsonObj.getString("image");
                            int categoryId = jsonObj.getInt("category_id");
                            String createdBy = jsonObj.getString("created_by");

                            Photo details = new Photo(photoId, title, description, image, categoryId, createdBy);
                            photo.add(details);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    aaPhoto.notifyDataSetChanged();
                }
            };
    // Code for step 2 end

}



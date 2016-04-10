package com.yantur.proj;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    MyView myView;
    MyAdapter adapter;
    List<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = getImagesPath();

        for (String s: data ) {
            Log.d("ART",s);
        }
        ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new MyAdapter(getApplication(),data);
        listView.setAdapter(adapter);
        myView = (MyView) findViewById(R.id.my_view);
        listView.setOnItemClickListener(this);
    }

    public ArrayList<String> getImagesPath() {
        Uri uri;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        String PathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = getContentResolver().query(uri,null, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(projection[0]);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(projection[1]);
        while (cursor.moveToNext()) {
            PathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(PathOfImage);
        }
        return listOfAllImages;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String image = data.get(position);
        Uri uri = Uri.fromFile(new File(image));
        Bitmap bitmap= null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap == null) {
            Log.d("ART",image);
        }
        myView.setCurrentBitmap(bitmap);
        Log.d("ART","ok");
    }
}

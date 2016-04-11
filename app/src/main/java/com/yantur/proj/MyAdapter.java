package com.yantur.proj;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {

    private Context context;
    private ContentResolver contentResolver;

    public MyAdapter(ContentResolver cs, Context context, List<String> resource) {
        super(context, R.layout.custom_row, resource);
        this.contentResolver = cs;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        String image = getItem(position);
        Uri uri = Uri.parse(image);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap curr = Bitmap.createScaledBitmap(bitmap,300,300,true);

        ImageView imageView = (ImageView) customView.findViewById(R.id.image);

        imageView.setImageBitmap(curr);

        return customView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
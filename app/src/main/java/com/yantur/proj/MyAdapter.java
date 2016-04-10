package com.yantur.proj;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Artur on 4/4/2016.
 */
public class MyAdapter extends ArrayAdapter<String> {

    private Context context;

    public MyAdapter(Context context, List<String> resource) {
        super(context,R.layout.custom_row, resource);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        String image = getItem(position);
        Uri uri = Uri.parse(image);

        ImageView imageView = (ImageView) customView.findViewById(R.id.image);

        imageView.setImageURI(uri);

        return customView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
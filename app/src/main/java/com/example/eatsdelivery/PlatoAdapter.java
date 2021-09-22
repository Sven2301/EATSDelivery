package com.example.eatsdelivery;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import  android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eatsdelivery.SQLite.Tables.Plato;

import java.util.ArrayList;

public class PlatoAdapter extends ArrayAdapter<Plato> {

    private Context mContext;
    private  int mResource;

    public PlatoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Plato> objects){
        super(context,resource,objects);
        this.mContext =  context;
        this.mResource =  resource;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.image);

        TextView textName = convertView.findViewById(R.id.txtName);

        TextView textDescrip = convertView.findViewById(R.id.txtSub);

        int idD = mContext.getResources().getIdentifier("android:drawable/"+ getItem(position).getImage(),null, mContext.getPackageName());
        System.out.println("android:drawable/"+ getItem(position).getImage());

        imageView.setImageResource(idD);

        textName.setText(getItem(position).getNombre());

        textDescrip.setText(getItem(position).getCosto());

        return convertView;
    }
}

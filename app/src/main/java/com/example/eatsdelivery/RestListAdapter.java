package com.example.eatsdelivery;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eatsdelivery.SQLite.Model;
import com.example.eatsdelivery.SQLite.Tables.Direccion;
import com.example.eatsdelivery.SQLite.Tables.Restaurante;

import java.util.ArrayList;

public class RestListAdapter extends ArrayAdapter<Restaurante> {

    private Context mContext;
    private  int mResource;

    public RestListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Restaurante> objects){
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

        TextView  textView = convertView.findViewById(R.id.txtSub);

        TextView  direccion = convertView.findViewById(R.id.txtSub2);

        Model model = new Model();

        Cursor cursor = model.selectInfoDireccion(mContext, getItem(position).getDireccionID());

        cursor.moveToFirst();

        Direccion rest = new Direccion();


        int index;

        index = cursor.getColumnIndexOrThrow("id");
        rest.setNombre(String.valueOf(cursor.getInt(index)));
        index = cursor.getColumnIndexOrThrow("Nombre");
        rest.setNombre(cursor.getString(index));
        index = cursor.getColumnIndexOrThrow("Descripcion");
        rest.setDescripcion(cursor.getString(index));

        /*
        String uri = "@drawable/" + getItem(position).getImage();
        int idD = mContext.getResources().getIdentifier(uri,null, mContext.getPackageName());

        imageView.setImageResource(idD);
        */
        textName.setText(getItem(position).getNombre());
        textView.setText("Dirección: ");
        direccion.setText(rest.getDescripcion());

        return convertView;
    }
}
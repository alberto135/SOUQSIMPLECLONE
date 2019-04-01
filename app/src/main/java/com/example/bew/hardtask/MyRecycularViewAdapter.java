package com.example.bew.hardtask;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRecycularViewAdapter extends RecyclerView.Adapter<MyRecycularViewAdapter.MyViewHolder> {

    private static final String TAG = "recviewactivity";
    List<CategResponse> recresponsearray ;
    int Counter ;
    Context context ;

    public MyRecycularViewAdapter()
    {

    }

   public MyRecycularViewAdapter(List<CategResponse> categResponse , int Counter1 ) {
        recresponsearray = categResponse;
        Counter=Counter1 ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.cat_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder ;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.catname.setText(recresponsearray.get(position).getTitleEN());
        holder.productnum.setText(recresponsearray.get(position).getProductCount());
        Picasso.get().load(recresponsearray.get(position).getPhoto()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return Counter ;
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView ;
        TextView catname ;
        TextView productnum ;
        CardView cardview ;
        public MyViewHolder(View itemView ) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            catname =itemView.findViewById(R.id.catname) ;
            productnum = itemView.findViewById(R.id.productnum);
            cardview = itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(this);
        }
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                int id = recresponsearray.get(position).getId();
                AppCompatActivity activity = (AppCompatActivity)view.getContext() ;
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                SubCatFragment f = new  SubCatFragment();
                Bundle bundle =new Bundle();
                bundle.putInt("ID",id);
                bundle.putString("cat_name",recresponsearray.get(position).getTitleEN());
                f.setArguments(bundle);
                fragmentTransaction.add(R.id.frame , f) ;
                fragmentTransaction.replace(R.id.frame ,f ) ;
                FrameLayout fram = view.findViewById(R.id.frame);
                fragmentTransaction.commit();
                Log.e(TAG, "good" + "counter is " + id);
            }
        }
    }
}

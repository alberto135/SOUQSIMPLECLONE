package com.example.bew.hardtask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SubCatRecycularViewAdapter extends RecyclerView.Adapter<SubCatRecycularViewAdapter.MyViewHolder> {
    List<CategResponse> recresponsearray ;
    int Counter ;
    Context context ;


    public SubCatRecycularViewAdapter(List<CategResponse> categResponse , int Counter1 ) {
        recresponsearray = categResponse;
        Counter=Counter1 ;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.cat_layout,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.catname.setText(recresponsearray.get(position).getTitleEN());
        holder.productnum.setText(recresponsearray.get(position).getProductCount());
        Picasso.get().load(recresponsearray.get(position).getPhoto()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return recresponsearray.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView catname ;
        TextView productnum ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            catname =itemView.findViewById(R.id.catname) ;
            productnum = itemView.findViewById(R.id.productnum);
        }
    }

}

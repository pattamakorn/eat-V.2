package com.example.eat_master;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class loadresAdapter extends RecyclerView.Adapter<loadresAdapter.MyViewHolder>{

    Context rmcontext;
    List<loadres> rmres;
    View view;
    Dialog myDialogres;

    public loadresAdapter(Context rmcontext, List<loadres> rmres){
        this.rmcontext = rmcontext;
        this.rmres = rmres;
    }

    @NonNull
    @Override
    public loadresAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(rmcontext).inflate(R.layout.item_res,parent,false);
        MyViewHolder vHolder = new MyViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull loadresAdapter.MyViewHolder holder, int position) {

        holder.rmnameres.setText(rmres.get(position).getNameresre());
        holder.mdeadline.setText(rmres.get(position).getDeadline());
        Glide.with(view.getContext()).load(rmres.get(position).getImgpromote()).into(holder.img_promote);

    }

    @Override
    public int getItemCount() {
        return rmres.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rmnameres,mdeadline;
        public ImageView img_promote;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rmnameres = itemView.findViewById(R.id.nameresrecy);
            mdeadline = itemView.findViewById(R.id.deadline);
            img_promote = itemView.findViewById(R.id.remem);
        }
    }
}

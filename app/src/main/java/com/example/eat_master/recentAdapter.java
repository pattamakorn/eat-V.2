package com.example.eat_master;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class recentAdapter extends RecyclerView.Adapter<recentAdapter.MyViewHolder> {

    Context reccontext;
    List<recentfood> rmrec;
    View view;
    Dialog myDialogrec;

    public ImageView dialog_imgre;
    public TextView dialog_namefoodagain,dialog_canagain,dialog_okagain,lagain,bagain,sumagain,pricefood;
    public int sum,cnum,cprice,converted;
    public String stprice,idagain;

    public recentAdapter(Context reccontext,List<recentfood> rmrec) {
        this.reccontext = reccontext;
        this.rmrec = rmrec;
    }

    @NonNull
    @Override
    public recentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(reccontext).inflate(R.layout.item_recent,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(view);

        myDialogrec = new Dialog(reccontext);
        myDialogrec.setContentView(R.layout.ordersagain);

        vHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idagain = rmrec.get(vHolder.getAdapterPosition()).getIdfoodrecent();
                String namefoodr = rmrec.get(vHolder.getAdapterPosition()).getNamefrecent();
                String imgfoodagain = rmrec.get(vHolder.getAdapterPosition()).getImgfoodrecent();

                stprice = rmrec.get(vHolder.getAdapterPosition()).getPrice();
                dialog_imgre = myDialogrec.findViewById(R.id.imgfoodagain);
                dialog_namefoodagain = myDialogrec.findViewById(R.id.namefoodagain);
                dialog_canagain = myDialogrec.findViewById(R.id.canagain);
                dialog_okagain = myDialogrec.findViewById(R.id.okagain);
                lagain = myDialogrec.findViewById(R.id.lagain);
                bagain = myDialogrec.findViewById(R.id.bagain);
                sumagain = myDialogrec.findViewById(R.id.nagain);
                pricefood = myDialogrec.findViewById(R.id.spricefood);

                pricefood.setText(stprice+" บาท");

                Glide.with(view.getContext()).load(imgfoodagain).into(dialog_imgre);


                dialog_namefoodagain.setText(namefoodr);
                myDialogrec.show();

                lagain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sumagain.setText(String.valueOf(sum--));
                    }
                });
                bagain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sumagain.setText(String.valueOf(sum++));
                    }
                });

                dialog_okagain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        insertorder();
                        myDialogrec.cancel();
                    }

                });

                dialog_canagain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialogrec.cancel();
                    }
                });
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recentAdapter.MyViewHolder holder, int position) {
        holder.foodnamerecent.setText(rmrec.get(position).getNamefrecent());
        holder.foodresrent.setText(rmrec.get(position).getNameresrec());
        holder.daterecent.setText(rmrec.get(position).getDatefoodrecent());
        Glide.with(view.getContext()).load(rmrec.get(position).getImgfoodrecent()).into(holder.foodimgrecent);
    }

    @Override
    public int getItemCount() {
        return rmrec.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView foodnamerecent,foodresrent,daterecent;
        public ImageView foodimgrecent;
        public CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodnamerecent = itemView.findViewById(R.id.recentnamefood);
            foodresrent = itemView.findViewById(R.id.recentnameres);
            daterecent = itemView.findViewById(R.id.recentdate);
            foodimgrecent = itemView.findViewById(R.id.recentimage);
            cardView = itemView.findViewById(R.id.cardrecent);

        }
    }

    public void insertorder(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/eat/order.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResponseStudent",response.toString());
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("abc",error.toString());
                        // Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_SHORT).show();
                    }

                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                SharedPreferences sp = reccontext.getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                String mid = sp.getString("IdKey","No ID");
                cnum = Integer.parseInt(sumagain.getText().toString());
                cprice = Integer.parseInt(stprice);
                converted = cnum * cprice;
                params.put("mail",mid);
                params.put("idfood",idagain);
                params.put("num",sumagain.getText().toString());
                params.put("price",String.valueOf(converted));
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(reccontext);
        requestQueue.add(stringRequest);
    }

}

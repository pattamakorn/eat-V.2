package com.example.eat_master;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class foodhitAdapter extends RecyclerView.Adapter<foodhitAdapter.MyViewHolder>  {


    Context mcontext;
    List<footHit> mfoothit;
    View view;
    Dialog myDialog;
    private ImageView img;
    public String idfood,namefood,resfood,pricefood;
    public TextView dialog_nameres,dialog_namefood,dialog_count,dialog_l,dialog_b,dialog_can,dilog_ok;
    public int numcount,sum,cnum,cprice,converted;

    public foodhitAdapter(Context mcontext, List<footHit> mfoothit) {
        this.mcontext = mcontext;
        this.mfoothit = mfoothit;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_home,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(view);

        myDialog = new Dialog(mcontext);
        myDialog.setContentView(R.layout.order_dialog);

        vHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idfood = mfoothit.get(vHolder.getAdapterPosition()).getIdfood();
                namefood = mfoothit.get(vHolder.getAdapterPosition()).getFoodname();
                resfood = mfoothit.get(vHolder.getAdapterPosition()).getFoodres();
                pricefood = mfoothit.get(vHolder.getAdapterPosition()).getPrice();
                numcount = 1;
                dialog_nameres = myDialog.findViewById(R.id.dialogname);
                dialog_namefood = myDialog.findViewById(R.id.dialogprice);
                dialog_count = myDialog.findViewById(R.id.dialogcount);
                dialog_l = myDialog.findViewById(R.id.dialogL);
                dialog_b = myDialog.findViewById(R.id.dialogB);
                dilog_ok = myDialog.findViewById(R.id.ok);
                dialog_can = myDialog.findViewById(R.id.cancle);


                dialog_nameres.setText(resfood);
                dialog_namefood.setText(namefood+" "+pricefood);
                dialog_count.setText("0");

                dialog_can.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.cancel();
                    }
                });

                dilog_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tol_rest = dialog_nameres.getText().toString();
                        String tol_name = dialog_namefood.getText().toString();
                        String tol_sum = dialog_count.getText().toString();
                        //Toast.makeText(mcontext, "OK =>"+tol_sum, Toast.LENGTH_SHORT).show();
                        insertorder();
                        myDialog.cancel();
                    }
                });

                dialog_l.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sum = numcount--;
                        dialog_count.setText(String.valueOf(sum));
                    }
                });

                dialog_b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sum = numcount++;
                        dialog_count.setText(String.valueOf(sum));
                    }
                });
                myDialog.show();


            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rnamefoot.setText(mfoothit.get(position).getFoodname());
        holder.namerest.setText(mfoothit.get(position).getFoodres());
        holder.price.setText(mfoothit.get(position).getPrice());
        Glide.with(view.getContext()).load(mfoothit.get(position).getIma()).into(holder.url_ima);



    }

    @Override
    public int getItemCount() {
        return mfoothit.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_click;
        public TextView rnamefoot,namerest,price,order;
        public ImageView url_ima;
        public CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //item_click = itemView.findViewById(R.id.Mitem_home);
            rnamefoot = itemView.findViewById(R.id.namefood);
            namerest = itemView.findViewById(R.id.nameres);
            price = itemView.findViewById(R.id.pricefood);
            url_ima = itemView.findViewById(R.id.imagefood);
            order = itemView.findViewById(R.id.orderFood);
            cardView = itemView.findViewById(R.id.Mitem_home);




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
                SharedPreferences sp = mcontext.getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                String mid = sp.getString("IdKey","No ID");
                cnum = Integer.parseInt(dialog_count.getText().toString());
                cprice = Integer.parseInt(pricefood);
                converted = cnum * cprice;
                params.put("mail",mid);
                params.put("idfood",idfood);
                params.put("num",dialog_count.getText().toString());
                params.put("price",String.valueOf(converted));
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
        requestQueue.add(stringRequest);
    }

}

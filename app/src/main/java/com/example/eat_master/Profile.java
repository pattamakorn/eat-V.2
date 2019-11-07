package com.example.eat_master;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
    View v;
    private ImageView iprofile;
    private TextView name,address,mail,tel;


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        name = v.findViewById(R.id.nameprofile);
        address = v.findViewById(R.id.addressprofile);
        mail = v.findViewById(R.id.mailprofile);
        tel = v.findViewById(R.id.telprofile);
        iprofile = v.findViewById(R.id.ImageProfile);


        loadprofile();


        return v;
    }


    public void loadprofile(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://203.154.83.137/eat/loadprofile.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String namee = posts.getString("name");
                        String addresss = posts.getString("address");
                        String tell = posts.getString("tel");
                        String emaill = posts.getString("email");
                        String imggg = posts.getString("img");
                        name.setText(namee);
                        address.setText(addresss);
                        mail.setText(emaill);
                        tel.setText(tell);
                        Glide.with(v.getContext()).
                                load(imggg).
                                into(iprofile);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                SharedPreferences sp = getActivity().getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                String mid = sp.getString("IdKey","No ID");
                params.put("user",mid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}

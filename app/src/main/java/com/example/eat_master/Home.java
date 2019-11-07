package com.example.eat_master;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    View v;
    private RecyclerView recyclerView,recyclerViewres;
    private List<footHit> listfoot;
    private List<loadres> listres;
    private String Url_Loadfood = "http://203.154.83.137/eat/loadfood.php";


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.recycler);
        recyclerViewres = v.findViewById(R.id.recyclerrent);

        loadresAdapter LoadresAdapter = new loadresAdapter(getContext(),listres);
        recyclerViewres.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false));
        recyclerViewres.setAdapter(LoadresAdapter);

        foodhitAdapter FoodhitAdapter = new foodhitAdapter(getContext(),listfoot);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(FoodhitAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listfoot = new ArrayList<>();
        listres = new ArrayList<>();
        loadfood();
        loadres();
    }

    public void loadfood(){
        StringRequest stringRequest = new StringRequest(
                Url_Loadfood, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        listfoot.add(new footHit(
//                                posts.getString("Ffood")
                                posts.getString("Ffood"),
                                "ร้าน"+posts.getString("res"),
                                        posts.getString("price"),
                                posts.getString("img"),
                                posts.getString("Fid"))
                        );
                        foodhitAdapter FoodhitAdapter = new foodhitAdapter(getContext(),listfoot);
                        recyclerView.setAdapter(FoodhitAdapter);
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
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void loadres(){
        StringRequest stringRequest = new StringRequest(
                "http://203.154.83.137/eat/loadren.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        listres.add(new loadres(
                                posts.getString("RNres"),
                                "30 นาที",
                                posts.getString("Rid"),
                                posts.getString("img"))
                        );
                        loadresAdapter LoadresAdapter = new loadresAdapter(getContext(),listres);
                        recyclerViewres.setAdapter(LoadresAdapter);
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
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


}

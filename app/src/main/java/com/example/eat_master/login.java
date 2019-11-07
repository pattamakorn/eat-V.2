package com.example.eat_master;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private EditText email,pass;
    private Button login;
    private TextView tregister;
    private String URL_LOGIN = "http://203.154.83.137/eat/login.php";

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Id = "IdKey";
    public static final String mFullname = "fullnameKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        email = findViewById(R.id.emaillogin);
        pass = findViewById(R.id.passwordlogin);
        login = findViewById(R.id.login_btn);
        tregister = findViewById(R.id.TextRegister);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String showidpre = sharedpreferences.getString("IdKey","No ID");
        if (showidpre != "No ID"){
            Intent intent = new Intent(login.this,MainActivity.class);
            startActivity(intent);
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String muser = email.getText().toString().trim();
                String mpass = pass.getText().toString().trim();
                if (!muser.isEmpty() || !mpass.isEmpty()){
                    login(muser,mpass);
//                    Intent intent = new Intent(login.this,MainActivity.class);
//                    startActivity(intent);
                }else {
                    email.setError("Please Insert username");
                    pass.setError("Please Insert password");
                }
            }
        });

        tregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,register.class));
            }
        });

    }

    private void login(final String user, final String pass){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_LOGIN,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")){
                                for (int i = 0;i < jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String cusid = object.getString("email").trim();
                                    String name = object.getString("name").trim();
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString(Id, cusid);
                                    editor.putString(mFullname, name);
                                    editor.commit();
                                    Intent intent = new Intent(login.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, "ไม่สามารถเข้าสู่ระบบได้กรุณาเช็ค \n E-mail\n Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "Error:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",user);
                params.put("password",pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

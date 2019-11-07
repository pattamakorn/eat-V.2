package com.example.eat_master;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    private EditText eemail,epassword,ename,eaddress,etel;
    private Button bregis;
    private TextView backlogin;
    private String Url_register = "http://203.154.83.137/eat/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        backlogin = findViewById(R.id.Textbacklogin);
        eemail = findViewById(R.id.editemai);
        epassword = findViewById(R.id.editpassword);
        ename = findViewById(R.id.editname);
        eaddress = findViewById(R.id.editaddress);
        etel = findViewById(R.id.edittel);
        bregis = findViewById(R.id.register_btn);

        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this,login.class));
            }
        });

        bregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url_register,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        Toast.makeText(register.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(register.this,login.class));
                        Intent intent = new Intent(register.this, login.class);
                        intent.putExtra("mail", eemail.getText().toString().trim());
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(register.this, volleyError.toString(), Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", ename.getText().toString());
                params.put("address", eaddress.getText().toString());
                params.put("tel", etel.getText().toString());
                params.put("email", eemail.getText().toString());
                params.put("password", epassword.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(register.this);
        requestQueue.add(stringRequest);

    }
}

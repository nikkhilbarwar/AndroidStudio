package com.nkas.newapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txtname, txtemail, txtnumber;
    MaterialButton submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtname = findViewById(R.id.Name);
        txtemail = findViewById(R.id.Email);
        txtnumber = findViewById(R.id.Number);

        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }

    private void insertData() {
        String name = txtname.getText().toString().trim();
        String email = txtemail.getText().toString().trim();
        String number = txtnumber.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        if (name.isEmpty()){
            Toast.makeText(this, "Name is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (email.isEmpty()){
            Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (number.isEmpty()){
            Toast.makeText(this, "Number is empty", Toast.LENGTH_SHORT).show();
            return;
        }else {
            progressDialog.show();

            StringRequest request = new StringRequest(Request.Method.POST, "https://loquacious-lengths.000webhostapp.com/insert.php"
                    , new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equalsIgnoreCase("Data Submitted")){
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                        Intent nIntent = new Intent(MainActivity.this, Sec.class);
                        startActivity(nIntent);

                    }else{
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
                //New Error Listener implement
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            ){
                //Ctrl+o getParams map
/*                @Nullable
                @org.jetbrains.annotations.Nullable*/
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", name);
                    params.put("email", email);
                    params.put("number", number);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);

        }
    }
}
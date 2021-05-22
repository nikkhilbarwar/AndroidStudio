package com.nkas.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void goToMain(View view) {
        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToSec(View view) {
        Intent secIntent = new Intent(FirstActivity.this, Sec.class);
        startActivity(secIntent);
    }

    public void goToUpdate(View view) {
        Intent upIntent = new Intent(FirstActivity.this, UpdateActivity.class);
        startActivity(upIntent);
    }

    public void goToDelete(View view) {
        Intent delIntent = new Intent(FirstActivity.this, DeleteActivity.class);
        startActivity(delIntent);
    }
}
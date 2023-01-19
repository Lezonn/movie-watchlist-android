package com.example.final_project;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.final_project.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splash.start();
    }

    Thread splash = new Thread(() -> {
        try {
            sleep(3000);
            Intent loginActivity = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(loginActivity);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}
package com.fourier.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.fourier.application.activity.AuthorizationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, GeneralActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        Intent intent = new Intent(MainActivity.this, AuthorizationActivity.class);
        startActivity(intent);
=======

        // prevent rotation
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
>>>>>>> 53bdadf683589ba6c5e07b507d8cd9b4b5695e11
    }

}
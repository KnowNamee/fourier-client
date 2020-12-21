package com.fourier.application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.fourier.application.activity.AuthorizationActivity;
import com.fourier.application.activity.GeneralActivity;

import java.sql.SQLOutput;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent;
        if (checkToken()) {
            intent = new Intent(MainActivity.this, GeneralActivity.class);
        } else {
            intent = new Intent(MainActivity.this, AuthorizationActivity.class);
        }
        startActivity(intent);
        // Prevent rotation
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private boolean checkToken() {
        SharedPreferences sPref = getSharedPreferences(
                getResources().getString(R.string.credentials_sp), MODE_PRIVATE);
        return sPref.contains(getResources().getString(R.string.token));
    }

//    @SuppressWarnings("unchecked")
//    public void loadPreferences() {
//        Map<String, ?> prefs =  getSharedPreferences(
//                getResources().getString(R.string.credentials_sp), MODE_PRIVATE).getAll();
//        for (String key : prefs.keySet()) {
//            Object pref = prefs.get(key);
//            String printVal = "";
//            if (pref instanceof Boolean) {
//                printVal =  key + " : " + (Boolean) pref;
//            }
//            if (pref instanceof Float) {
//                printVal =  key + " : " + (Float) pref;
//            }
//            if (pref instanceof Integer) {
//                printVal =  key + " : " + (Integer) pref;
//            }
//            if (pref instanceof Long) {
//                printVal =  key + " : " + (Long) pref;
//            }
//            if (pref instanceof String) {
//                printVal =  key + " : " + (String) pref;
//            }
//            if (pref instanceof Set<?>) {
//                printVal =  key + " : " + (Set<String>) pref;
//            }
//            ;
//        }
//        System.out.println(prefs);
//    }

}
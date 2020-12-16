package com.fourier.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fourier.application.generel.OnGetFileButtonClickListener;
import com.fourier.application.generel.OnLoadWAVButtonClickListener;
import com.fourier.application.generel.OnRecButtonClickListener;

public class GeneralActivity extends AppCompatActivity {

    private Button btnGetFile;
    private Button btnLoadWAV;
    private Button btnRecStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.general_activity);

        initControls();
        AddListners();
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    public void onBackPressed(){
        // Just leave
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initControls() {
        btnGetFile = (Button) findViewById(R.id.get_file_button);
        btnLoadWAV = (Button) findViewById(R.id.load_botton);
        btnRecStop = (Button) findViewById(R.id.rec_botton);
    }

    private void AddListners() {
        btnGetFile.setOnClickListener((View.OnClickListener) new OnGetFileButtonClickListener(this));
        btnRecStop.setOnClickListener((View.OnClickListener) new OnLoadWAVButtonClickListener(this));
        btnRecStop.setOnClickListener((View.OnClickListener) new OnRecButtonClickListener(this));
    }
}



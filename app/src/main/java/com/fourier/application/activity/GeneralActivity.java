package com.fourier.application.activity;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.fourier.application.R;
import com.fourier.application.listener.general.OnGetPDFButtonClickListener;
import com.fourier.application.listener.general.OnLoadFileButtonClickListener;
import com.fourier.application.listener.general.OnRecButtonClickListener;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;

public class GeneralActivity extends Activity {

    private Button btnGetPDF;
    private Button btnLoadFile;
    private Button btnRecStop;
    private TextView txtLoadFilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_activity);
        initControls();

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
        btnGetPDF = (Button) findViewById(R.id.btnGetPDF);
        btnLoadFile = (Button) findViewById(R.id.btnLoadFile);
        btnRecStop = (Button) findViewById(R.id.rec_botton);
        txtLoadFilename = (TextView) findViewById(R.id.txtLoadFilename);

        btnGetPDF.setOnClickListener((View.OnClickListener) new OnGetPDFButtonClickListener(
                this, txtLoadFilename));
        btnLoadFile.setOnClickListener((View.OnClickListener) new OnLoadFileButtonClickListener(
                this));
        btnRecStop.setOnClickListener((View.OnClickListener) new OnRecButtonClickListener(
                this));
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 10: {
                if (resultCode == RESULT_OK) {
//                    String absolutePath = Uri.decode(data.getDataString());
//                    String correctPath = String.format("%s/%s",
//                            Environment.getExternalStorageDirectory(),
//                            absolutePath.substring(absolutePath.lastIndexOf(":") + 1));
                    txtLoadFilename.setText(data.getDataString());
                }
                break;
            }
        }
    }


}



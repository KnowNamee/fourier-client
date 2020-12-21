package com.fourier.application.listener.general;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.fourier.application.R;
import com.fourier.application.activity.GeneralActivity;
import com.fourier.application.network.http.HttpMultipartRequest;
import com.fourier.application.network.request.UploadRequest;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class OnGetPDFButtonClickListener implements View.OnClickListener {

    private GeneralActivity activity;
    private TextView txtLoadFilepath;

    public OnGetPDFButtonClickListener(GeneralActivity activity, TextView txtLoadFilepath) {
        this.activity = activity;
        this.txtLoadFilepath = txtLoadFilepath;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        SharedPreferences sPref = v.getContext().getSharedPreferences(
                v.getResources().getString(R.string.credentials_sp), Context.MODE_PRIVATE);
        String token = sPref.getString("token", "");
        Uri uri = Uri.parse(txtLoadFilepath.getText().toString());
        String filename = uri.getPath().substring(uri.getPath().lastIndexOf("/") + 1);
        ParcelFileDescriptor parcelFileDescriptor;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            parcelFileDescriptor =
                    activity.getContentResolver().openFileDescriptor(uri, "r", null);
            fileOutputStream = new FileOutputStream(
                    new File(activity.getCacheDir(), filename));
            fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
            IOUtils.copy(fileInputStream, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UploadRequest request = new UploadRequest(
                "https://fourier-server-dev.herokuapp.com/bucket/upload",
                token, activity.getCacheDir() + "/" + filename);
        Thread getPDFThread = new Thread(request);
        getPDFThread.start();
        try {
            getPDFThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(request.getResponse());
    }

}

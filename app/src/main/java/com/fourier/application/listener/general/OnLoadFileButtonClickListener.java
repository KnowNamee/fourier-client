package com.fourier.application.listener.general;
import android.app.Dialog;
import android.content.Intent;
import android.os.Environment;
import android.view.View;

import com.fourier.application.activity.GeneralActivity;
import com.fourier.application.utils.FileDialog;

import java.io.File;

public class OnLoadFileButtonClickListener implements View.OnClickListener{

    private GeneralActivity activity;

    public OnLoadFileButtonClickListener(GeneralActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        activity.startActivityForResult(intent, 10);
    }

}

package com.fourier.application.listener.general;
import android.view.View;

import com.fourier.application.activity.GeneralActivity;

public class OnLoadWAVButtonClickListener implements View.OnClickListener{

    private GeneralActivity activity;

    public OnLoadWAVButtonClickListener(GeneralActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        String path;
        // ...
        // assume here we get
    }
}

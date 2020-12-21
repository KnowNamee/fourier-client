package com.fourier.application.listener.authorization;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.fourier.application.R;
import com.fourier.application.activity.AuthorizationActivity;
import com.fourier.application.activity.GeneralActivity;
import com.fourier.application.network.request.LoginRequest;
import com.fourier.application.utils.CredentialsValidator;

public class OnSignInButtonClickListener implements View.OnClickListener {

    private AuthorizationActivity activity;
    private EditText username;
    private EditText password;
    private Button btnSignUp;
    private TextView text;

    private boolean isStateVisible = false;

    public OnSignInButtonClickListener(AuthorizationActivity activity,
                                       EditText username,
                                       EditText password,
                                       Button btnSignUp,
                                       TextView text) {
        this.activity = activity;
        this.username = username;
        this.password = password;
        this.btnSignUp = btnSignUp;
        this.text = text;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        // Hide keyboard
        InputMethodManager inputManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if (!isStateVisible) {
            username.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            btnSignUp.setVisibility(View.INVISIBLE);
            text.setText(R.string.sign_up);
            isStateVisible = true;
        } else if (validateCredentials()) {
            LoginRequest signIn = new LoginRequest(
                    "https://fourier-server-dev.herokuapp.com/login",
                    username.getText().toString(),
                    password.getText().toString());
            Thread signInThread = new Thread(signIn);
            signInThread.start();
            try {
                signInThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String token = signIn.getAuthorizationToken();
            if (token != null) {
                SharedPreferences sPref = activity.getSharedPreferences(
                        v.getContext().getResources().getString(R.string.credentials_sp), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sPref.edit();
                editor.putString(v.getContext().getResources().getString(R.string.token), token);
                editor.apply();
                Intent intent = new Intent(activity, GeneralActivity.class);
                activity.startActivity(intent);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean validateCredentials() {
        // TODO Tell user the format of credentials
        boolean isPasswordValid =
                CredentialsValidator.isPasswordValid(password.getText().toString());
        boolean isUsernameValid =
                CredentialsValidator.isUsernameValid(username.getText().toString());

        if (!isPasswordValid) {
            password.setText("");
        }
        if (!isUsernameValid) {
            username.setTextColor(Color.RED);
        }

        return isPasswordValid && isUsernameValid;
    }

}

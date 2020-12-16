package com.fourier.application.listener.authorization;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.fourier.application.network.request.RegistrationRequest;
import com.fourier.application.R;
import com.fourier.application.activity.AuthorizationActivity;
import com.fourier.application.utils.CredentialsValidator;

public class OnSignUpButtonClickListener implements View.OnClickListener {

    private AuthorizationActivity activity;
    private EditText email;
    private EditText username;
    private EditText password;
    private Button btnSignIn;
    private TextView text;

    private boolean isStateVisible = false;

    public OnSignUpButtonClickListener(AuthorizationActivity activity,
                                       EditText email,
                                       EditText username,
                                       EditText password,
                                       Button btnSignIn,
                                       TextView text) {
        this.activity = activity;
        this.email = email;
        this.username = username;
        this.password = password;
        this.btnSignIn = btnSignIn;
        this.text = text;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        // Hide input method manager
        InputMethodManager inputManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if (!isStateVisible) {
            btnSignIn.setVisibility(View.INVISIBLE);
            email.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            text.setText(R.string.sign_up);
            isStateVisible = true;
        } else if (validateCredentials()) {
            // TODO handle server errors and report user
            RegistrationRequest signUp = new RegistrationRequest(
                    "https://fourier-server.herokuapp.com/registration/user",
                    email.getText().toString(),
                    username.getText().toString(),
                    password.getText().toString());
            Thread signUpThread = new Thread(signUp);
            signUpThread.start();
            try {
                signUpThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            activity.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean validateCredentials() {
        // TODO Tell user the format of credentials
        boolean isEmailValid =
                CredentialsValidator.isEmailValid(email.getText().toString());
        boolean isPasswordValid =
                CredentialsValidator.isPasswordValid(password.getText().toString());
        boolean isUsernameValid =
                CredentialsValidator.isUsernameValid(username.getText().toString());

        if (!isEmailValid) {
            email.setTextColor(Color.RED);
        }
        if (!isPasswordValid) {
            password.setText("");
        }
        if (!isUsernameValid) {
            username.setTextColor(Color.RED);
        }

        return isEmailValid && isPasswordValid && isUsernameValid;
    }



}

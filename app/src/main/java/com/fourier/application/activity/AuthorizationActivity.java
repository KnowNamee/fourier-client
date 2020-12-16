package com.fourier.application.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fourier.application.R;
import com.fourier.application.listener.authorization.OnSignInButtonClickListener;
import com.fourier.application.listener.authorization.OnSignUpButtonClickListener;

public class AuthorizationActivity extends Activity {

    private Button btnSignIn;
    private Button btnSignUp;
    private EditText email;
    private EditText username;
    private EditText password;
    private TextView text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        initControls();
    }

    @Override
    public void onBackPressed() {
        if (text.getText().equals(getResources().getText(R.string.welcome_text))) {
            // This code hides the application
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        } else {
            // This code sets default authorization_activity parameters
            text.setText(R.string.welcome_text);
            email.setVisibility(View.INVISIBLE);
            username.setVisibility(View.INVISIBLE);
            password.setVisibility(View.INVISIBLE);
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignUp.setVisibility(View.VISIBLE);
        }
    }

    private void initControls() {
        // Assign controls
        btnSignIn = (Button) findViewById(R.id.signInButton);
        btnSignUp = (Button) findViewById(R.id.signUpButton);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        username = (EditText) findViewById(R.id.editTextTextPersonName);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        text = (TextView) findViewById(R.id.welcomeText);

        // Listeners
        btnSignIn.setOnClickListener((View.OnClickListener) new OnSignInButtonClickListener());
        btnSignUp.setOnClickListener((View.OnClickListener) new OnSignUpButtonClickListener(
                this, email, username, password, btnSignIn, text));
    }

}

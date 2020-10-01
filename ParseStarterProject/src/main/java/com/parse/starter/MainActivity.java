/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {


    EditText usernameText, passwordText;
    TextView signUpText;
    Button loginButton;
    boolean loginState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        loginState = true;

        signUpText = (TextView) findViewById(R.id.sign_up_text);
        signUpText.setOnClickListener(this);

        ImageView logo = (ImageView) findViewById(R.id.imgView);
        RelativeLayout backgroundLayout = (RelativeLayout) findViewById(R.id.backgroundLayout);

        logo.setOnClickListener(this);
        backgroundLayout.setOnClickListener(this);

        usernameText = (EditText) findViewById(R.id.username_text);
        passwordText = (EditText) findViewById(R.id.password_text);
        passwordText.setOnKeyListener(this);

        if (checkUser()) {
            ParseUser.logOutInBackground();
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    public void signUpOrLogin(View view) {
        ParseUser user = new ParseUser();

        if ((usernameText.getText().toString().equals("") || usernameText.getText().toString().isEmpty()) &&
                (passwordText.getText().toString().isEmpty() || passwordText.getText().toString().equals(""))) {
            Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_LONG).show();
        } else {
            if (loginState == false) {
                user.setUsername(usernameText.getText().toString());
                user.setPassword(passwordText.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("sign up status", "Sign up successful");
                        } else {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                loginState = true;
                ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {

                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Log.i("Success", "Logged in");
                        } else {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }

    public boolean checkUser() {
        if (ParseUser.getCurrentUser() != null) {
            Log.i("Signed in", ParseUser.getCurrentUser().getUsername());
            return true;
        } else {
            Log.i("Not logged in", "No users signed in");
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        loginButton = (Button) findViewById(R.id.login_button);
        switch (v.getId()) {
            case R.id.sign_up_text:
                if (signUpText.getText().equals("Or Sign Up")) {
                    signUpText.setText("Login");
                    loginButton.setText("Sign Up");
                    loginState = false;

                } else if (signUpText.getText().equals("Login")) {
                    signUpText.setText("Or Sign Up");
                    loginButton.setText("Login");
                    loginState = true;
                }
        }
        if (v.getId() == R.id.backgroundLayout || v.getId() == R.id.imgView) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            signUpOrLogin(view);
        }

        return false;
    }
}
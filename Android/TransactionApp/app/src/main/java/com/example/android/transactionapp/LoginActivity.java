package com.example.android.transactionapp;

/**
 * Created by Bogdan on 12/30/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import models.AppDatabase;
import models.Repo;
import models.User;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;
    Repo repo = new Repo(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        repo.CreateDb();

        List<User> users = repo.getDb().userDao().getAll();

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        boolean ok = true;

        AppDatabase db = repo.getDb();

        User user = db.userDao().findByEmail(email);

        final Integer userId = db.userDao().findByEmail(email).getId();

        if (user == null)
            ok = false;
        else
            if (!user.getPassword().equals(password))
                ok = false;

        final boolean complete = ok;
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        if (complete)
                            onLoginSuccess(userId);
                        else
                            onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                Integer userId = data.getIntExtra("userId",0);
                if (repo.getDb().userDao().findById(userId).getRole().equals("client")) {
                    Intent i = new Intent(LoginActivity.this, DisplayListOfItems.class);
                    i.putExtra("userId", userId);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(LoginActivity.this, DisplayCategories.class);
                    i.putExtra("userId", userId);
                    startActivity(i);
                }
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess(Integer userId) {
        _loginButton.setEnabled(true);
        if (repo.getDb().userDao().findById(userId).getRole().equals("client")) {
            Intent i = new Intent(LoginActivity.this, DisplayListOfItems.class);
            i.putExtra("userId", userId);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(LoginActivity.this, DisplayCategories.class);
            i.putExtra("userId", userId);
            startActivity(i);
        }
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
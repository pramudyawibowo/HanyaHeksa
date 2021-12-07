package org.aplas.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    TextInputEditText username, password;
    DatabaseHelper db;
    String usernameKey, passwordKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        username = findViewById(R.id.usernametext);
        password = findViewById(R.id.passwordtext);

        Button btnLogin = findViewById(R.id.btnOnLog);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(Login.this, Home.class);
                usernameKey = password.getText().toString();
                passwordKey = password.getText().toString();
                if (validateLogin()) {
                    goHome.putExtra("username", usernameKey);
                    startActivity(goHome);
                } else {
                    return;
                }
            }
        });

        Button btnBack = findViewById(R.id.backlogin);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHome = new Intent(Login.this, MainActivity.class);
                goHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                goHome.putExtra("EXIT", true);
                startActivity(goHome);
            }
        });

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    private boolean validateUsername() {
        String usernameInput = username.getText().toString();
        if (usernameInput.isEmpty()) {
            username.setError("Username tidak boleh kosong!");
            return false;
        } else if (usernameInput.length() > 15) {
            username.setError("Username terlalu panjang!");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString();
        if (passwordInput.isEmpty()) {
            password.setError("Password tidak boleh kosong");
            return false;
        }  else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateLogin(){

        if (validateUsername() && validatePassword() && db.checkUser(usernameKey, passwordKey)) {
            return true;
        } else {
            username.setError(null);
            return false;
        }
    }
}
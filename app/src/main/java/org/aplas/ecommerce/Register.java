package org.aplas.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
    TextInputEditText username, password, name;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        username = findViewById(R.id.usernametext);
        password = findViewById(R.id.passwordtext);
        name = findViewById(R.id.namalengkaptext);

        Button btnRegister = findViewById(R.id.btnOnReg);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();
                String namaKey = name.getText().toString();
                Context context = getApplicationContext();
                if(!usernameKey.isEmpty() && !passwordKey.isEmpty()){
                    if(db.checkUser(usernameKey, passwordKey)){
                        username.setError("Username sudah terdaftar");
                        password.setError("Password sudah terdaftar");
                        Toast.makeText(context, "Akun telah terdaftar, silahkan LOGIN", Toast.LENGTH_SHORT).show();
                    } else {
                        db.tambahUser(usernameKey, passwordKey, namaKey);
                        Toast.makeText(context, "Akun anda berhasil didaftarkan!", Toast.LENGTH_SHORT).show();
                        Intent goLogin = new Intent(Register.this, Login.class);
                        startActivity(goLogin);
                    }
                } else {
                    username.setError("Username tidak boleh kosong");
                    password.setError("Password tidak boleh kosong");
                }
            }
        });
    }
}
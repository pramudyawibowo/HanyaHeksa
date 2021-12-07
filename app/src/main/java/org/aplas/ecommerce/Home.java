package org.aplas.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView rvProduk;
    DatabaseHelper myDB;
    ArrayList<String> id, nama, gambar, harga, deskripsi;
    AdapterProduk adapterproduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button buttonAdd = findViewById(R.id.button_add);
        String username = getIntent().getStringExtra("username");
        if(username.equals("admin")){
            buttonAdd.setVisibility(View.VISIBLE);
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goAdd = new Intent(Home.this, AddProduk.class);
                    startActivity(goAdd);
                }
            });
        }


        rvProduk = findViewById(R.id.rvProduk);
        myDB = new DatabaseHelper(Home.this);
        id = new ArrayList<>();
        nama = new ArrayList<>();
        gambar = new ArrayList<>();
        harga = new ArrayList<>();
        deskripsi = new ArrayList<>();

        takeProduk();

        adapterproduk = new AdapterProduk(Home.this, id, nama, harga, deskripsi, gambar);
        rvProduk.setLayoutManager(new LinearLayoutManager(Home.this));
        rvProduk.setAdapter(adapterproduk);

    }

    void takeProduk(){
        Cursor cursor = myDB.readAllProduk();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Belum ada produk", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                nama.add(cursor.getString(1));
                harga.add(cursor.getString(2));
                gambar.add(cursor.getString(3));
                deskripsi.add(cursor.getString(4));
            }
        }
    }
}
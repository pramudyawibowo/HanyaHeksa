package org.aplas.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailProduk extends AppCompatActivity {

    TextView nama_produk, harga_produk, deskripsi_produk;
    ImageView gambar_produk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        nama_produk = findViewById(R.id.dtl_name);
        harga_produk = findViewById(R.id.dtl_harga);
        deskripsi_produk = findViewById(R.id.desc);
        gambar_produk = findViewById(R.id.dtl_image);

        nama_produk.setText(getIntent().getStringExtra("nama"));
        harga_produk.setText(getIntent().getStringExtra("harga"));
        deskripsi_produk.setText(getIntent().getStringExtra("deskripsi"));
        gambar_produk.setImageURI(Uri.parse(getIntent().getStringExtra("gambar")));
    }
}
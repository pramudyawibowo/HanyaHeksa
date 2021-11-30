package org.aplas.ecommerce;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList nama, harga, gambar, deskripsi;

    AdapterProduk(Context context, ArrayList nama, ArrayList harga, ArrayList gambar) {
        this.context = context;
        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_produk, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nama_txt.setText(String.valueOf(nama.get(position)));
        holder.harga_txt.setText(String.valueOf(harga.get(position)));
        holder.gambar_txt.setText(String.valueOf(gambar.get(position)));
    }

    @Override
    public int getItemCount() {
        return nama.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nama_txt, harga_txt, gambar_txt, deskripsi_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_txt = itemView.findViewById(R.id.book_title_txt);
            harga_txt = itemView.findViewById(R.id.book_author_txt);
            gambar_txt = itemView.findViewById(R.id.book_pages_txt);
        }
    }
}

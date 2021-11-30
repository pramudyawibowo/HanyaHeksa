package org.aplas.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, "hanyaheksa", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username VARCHAR(50) NOT NULL," +
                "password VARCHAR(50) NOT NULL," +
                "name VARCHAR(50) NOT NULL)";
        sqLiteDatabase.execSQL(query);
        String queryproduk = "CREATE TABLE produk (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nama VARCHAR(50) NOT NULL," +
                "harga VARCHAR(50) NOT NULL," +
                "gambar VARCHAR(50) NOT NULL, " +
                "deskripsi TEXT NOT NULL)";
        sqLiteDatabase.execSQL(queryproduk);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    public long tambahUser(String username, String password, String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", nama);
        long res = db.insert("users", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String password) {
        String[] columns = { "id" };
        SQLiteDatabase db = getReadableDatabase();
        String selection = "username=? AND password=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean hapusUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = "username=? AND password=?";
        String[] selectionArgs = { username, password };
        db.delete("users", selection, selectionArgs);
        return false;
    }

    public void tambahProduk(String nama, String harga, String gambar, String deskripsi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nama", nama);
        cv.put("harga", harga);
        cv.put("gambar", gambar);
        cv.put("deskripsi", deskripsi);
        long result = db.insert("produk", null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllProduk(){
        String query = "SELECT nama, harga, gambar FROM produk";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}

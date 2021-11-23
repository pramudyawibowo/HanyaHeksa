package org.aplas.ecommerce;

import android.app.Service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ecommerce";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "user";
}

package com.example.dpitangui.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dpitangui on 27/02/2018.
 */

public class SQLiteHandler extends SQLiteOpenHelper{
    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_api";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";
    private static final String KEY_CREATED_AT = "created_at";

    //TABELA PRODUTO
    private static  final String TABLE_PRODUTO = "produto";

    //COLUNAS PRODUTO
    private static final String COD_ID = "cod_id";
    private static final String COD_EMPRESA = "cod_empresa";
    private static final String COD_PROD = "cod_prod";
    private static final String DESCRICAO = "descricao";
    private static final String EMBALAGEM = "embalagem";
    private static final String IPI = "ipi";
    private static final String PRECO = "preco";
    private static final String PESO = "peso";
    private static final String PREC_S_IPI = "prec_s_ipi";
    private static final String QT_CAIXA = "qt_caixa";
    private static final String UNIDADE = "unidade";
    private static final String PRECO_CAIXA_S_IPI = "preco_caixa_s_ipi";
    private static final String QT_MIN_CAIXA = "qt_min_caixa";
    private static final String DT_ATUALIZA = "dt_atualiza";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_UID + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        String CREATE_PRODUTO_TABLE = "CREATE TABLE " + TABLE_PRODUTO + "("
                                        + COD_ID + " TEXT,"
                                        + COD_EMPRESA + " TEXT,"
                                        + COD_PROD + " TEXT,"
                                        + DESCRICAO + " TEXT,"
                                        + EMBALAGEM + " TEXT,"
                                        + IPI + " TEXT,"
                                        + PRECO + " TEXT,"
                                        + PESO + " TEXT,"
                                        + PREC_S_IPI + " TEXT,"
                                        + QT_CAIXA + " TEXT,"
                                        + UNIDADE + " TEXT,"
                                        + PRECO_CAIXA_S_IPI + " TEXT,"
                                        + QT_MIN_CAIXA + " TEXT,"
                                        + DT_ATUALIZA + " TEXT" + ")";
        db.execSQL(CREATE_PRODUTO_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String name, String email, String uid, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_UID, uid); // Email
        values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("uid", cursor.getString(3));
            user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

    public void atualizaProdutos(List<Produto> listProduto){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            deleteProdutos(db);
            for (Produto produto : listProduto) {
                addProduto(db, produto);
            }
        }catch (Exception e){

        }finally {
            db.close();
        }
    }

    private void addProduto(SQLiteDatabase db, Produto produto) {
        ContentValues values = new ContentValues();
        values.put(COD_ID, produto.cod_id);
        values.put(COD_EMPRESA, produto.cod_empresa);
        values.put(COD_PROD, produto.cod_prod);
        values.put(DESCRICAO, produto.descricao);
        values.put(EMBALAGEM, produto.embalagem);
        values.put(IPI, produto.ipi);
        values.put(PRECO, produto.preco);
        values.put(PESO, produto.peso);
        values.put(PREC_S_IPI, produto.qt_caixa);
        values.put(QT_CAIXA, produto.qt_caixa);
        values.put(UNIDADE, produto.unidade);
        values.put(PRECO_CAIXA_S_IPI, produto.preco_caixa_s_ipi);
        values.put(QT_MIN_CAIXA, produto.qt_min_caixa);
        values.put(DT_ATUALIZA, produto.dt_atualiza);

        // Inserting Row
        long id = db.insert(TABLE_PRODUTO, null, values);

        Log.d(TAG, "Novo produto inserido");
    }

    private void deleteProdutos(SQLiteDatabase db) {
        // Delete All Rows
        db.delete(TABLE_PRODUTO, null, null);
    }
}

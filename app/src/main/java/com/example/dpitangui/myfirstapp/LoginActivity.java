package com.example.dpitangui.myfirstapp;

import android.app.Activity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Response;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by dpitangui on 27/02/2018.
 */

public class LoginActivity  extends Activity {
    //private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            //startActivity(intent);
            //finish();
        }

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    //checkLogin(email, password);
                    checkLogin("inflor", "1233223");
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Usuário ou senha não informado!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

    }

    /**
     * function to verify login details in mysql db
     * */
    public void checkLogin(final String usuario, final String senha) {
        try {
            pDialog.setMessage("Logging in ...");
            showDialog();

            Call<List<Usuario>> call = new RetrofitInializador().Login().login(usuario, senha);
            call.enqueue(new Callback<List<Usuario>>() {
                             @Override
                             public void onResponse(Call<List<Usuario>> call, retrofit2.Response<List<Usuario>> response) {
                                 List<Usuario> usuario = response.body();
                                 //session.setLogin(true);
                                 Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                 startActivity(intent);
                                 finish();
                             }

                             @Override
                             public void onFailure(Call<List<Usuario>> call, Throwable t) {
                                 //verificar como aumentar o tempo de timeout
                                 Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                 startActivity(intent);
                                 finish();
                             }
                         });

                    // Inserting row in users table
                    //db.addUser(name, email, uid, created_at);

                    // Launch main activity
                    //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //startActivity(intent);
                    //finish();
        }catch (Exception e){
            // Launch main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

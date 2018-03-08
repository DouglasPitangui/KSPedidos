package com.example.dpitangui.myfirstapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardOle, cardGaborardi, cardSunguirder;
    private ProgressDialog pDialog;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardOle = (CardView) findViewById(R.id.cardOle);
        cardGaborardi = (CardView) findViewById(R.id.cardGaborardi);
        cardSunguirder = (CardView) findViewById(R.id.cardSunguirder);
        cardOle.setOnClickListener(this);
        cardGaborardi.setOnClickListener(this);
        cardSunguirder.setOnClickListener(this);
        db = new SQLiteHandler(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.cardOle:
                i = new Intent(MainActivity.this, SolicitarPedido.class);
                i.putExtra("Empresa", "Olé");
                startActivity(i);
                break;
            case R.id.cardGaborardi:
                i = new Intent(MainActivity.this, SolicitarPedido.class);
                i.putExtra("Empresa", "Gaborardi");
                startActivity(i);
                break;
            case R.id.cardSunguirder:
                i = new Intent(MainActivity.this, SolicitarPedido.class);
                i.putExtra("Empresa", "Sun Guirder");
                startActivity(i);
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_refresh:
                atualizaProdutos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void atualizaProdutos() {
        try {

            pDialog = new ProgressDialog(this);
            pDialog.setCancelable(false);
            pDialog.setMessage("Carregando produtos...");
            showDialog();

            Call<List<Produto>> call = new RetrofitInializador().Produto().obterProduto();
            call.enqueue(new Callback<List<Produto>>() {
                @Override
                public void onResponse(Call<List<Produto>> call, retrofit2.Response<List<Produto>> response) {
                    List<Produto> produto = response.body();
                    db.atualizaProdutos(produto);
                    Toast.makeText(getApplicationContext(),
                            "Produtos atualizados.", Toast.LENGTH_LONG)
                            .show();
                }

                @Override
                public void onFailure(Call<List<Produto>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),
                            "Erro ao carregar produtos", Toast.LENGTH_LONG)
                            .show();
                }
            });

            // Inserting row in users table
            //db.addUser(name, email, uid, created_at);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Erro ao atualuzar produtos", Toast.LENGTH_LONG)
                    .show();
        } finally {
            hideDialog();
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
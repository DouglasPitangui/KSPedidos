package com.example.dpitangui.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SolicitarPedido extends AppCompatActivity {


    String[] ProdutosNome = {
            "C",
            "C++",
            "C#",
            "Java",
            "Python",
            "Lua",
            "JavaScript"};

    String[] ProdutosCodigo = {
            "C",
            "C++",
            "C#",
            "Java",
            "Python",
            "Lua",
            "JavaScript"};

    String[] ProdutosQuantidade = {
            "C",
            "C++",
            "C#",
            "Java",
            "Python",
            "Lua",
            "JavaScript"};

    private ArrayList<Map<String, String>> pProduto;
    private SimpleAdapter mAdapter;
    private AutoCompleteTextView pAutoCompleteProduto;
    private ListView lstview;
    private ListViewAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_pedido);

        Intent it = getIntent();
        setTitle(it.getStringExtra("Empresa"));

        //region LISTVIEW
        lstview=(ListView)findViewById(R.id.listv);
        adapter = new ListViewAdapter(this, ProdutosCodigo, ProdutosNome, ProdutosQuantidade) ;
        // Bind data to the ListView
        lstview.setAdapter(adapter);

        Util.setListViewHeightBasedOnChildren(lstview);
        //endregion LISTVIEW

        //region LISTA PRODUTO
        pProduto = new ArrayList<Map<String, String>>();
        PopulatePeopleList();
        pAutoCompleteProduto = (AutoCompleteTextView) findViewById(R.id.autocompleteProduto);
        mAdapter = new SimpleAdapter(this, pProduto, R.layout.layout_autocomplete_produto,
                new String[] { "Nome", "Valor", "Codigo" }, new int[] {
                R.id.ProdutoNome, R.id.ProdutoValor, R.id.ProdutoCodigo });
        pAutoCompleteProduto.setAdapter(mAdapter);

        pAutoCompleteProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> map = (Map<String, String>) parent.getItemAtPosition(position);

                String name  = map.get("Nome");
                String number = map.get("Valor");
                pAutoCompleteProduto.setText(""+name+"<"+number+">");
            }
        });
        //endregion LISTA PRODUTO
    }

    public void PopulatePeopleList() {
        pProduto.clear();
        Map<String, String> mapProduto = new HashMap<String, String>();
        mapProduto.put("Nome", "Batata");
        mapProduto.put("Valor", "3,50");
        mapProduto.put("Codigo", "123456");
        //Then add this map to the list.
        pProduto.add(mapProduto);

        Map<String, String> mapProduto1 = new HashMap<String, String>();
        mapProduto1.clear();
        mapProduto1.put("Nome", "Batata Doce");
        mapProduto1.put("Valor", "7,99");
        mapProduto1.put("Codigo", "332564");
        //Then add this map to the list.
        pProduto.add(mapProduto1);

        Map<String, String> mapProduto2 = new HashMap<String, String>();
        mapProduto2.clear();
        mapProduto2.put("Nome", "Arroz Batata");
        mapProduto2.put("Valor", "9,99");
        mapProduto2.put("Codigo", "3325641");
        //Then add this map to the list.
        pProduto.add(mapProduto2);
        //Then add this map to the list.


        Map<String, String> mapProduto3 = new HashMap<String, String>();
        mapProduto3.clear();
        mapProduto3.put("Nome", "Batata com Bacon");
        mapProduto3.put("Valor", "19,99");
        mapProduto3.put("Codigo", "33251641");
        //Then add this map to the list.
        pProduto.add(mapProduto3);
    }

    public void clickMe(View view){

        int position = lstview.getPositionForView((View) view.getParent());
        Object toRemove = adapter.getItem(position);
        adapter.remove(adapter.getItem(position));

        adapter.notifyDataSetChanged();
        //Button bt=(Button)view;
        //Toast.makeText(this, "Button "+position,Toast.LENGTH_LONG).show();
    }
}

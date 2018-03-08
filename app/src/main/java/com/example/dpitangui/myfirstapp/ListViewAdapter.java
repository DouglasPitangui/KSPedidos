package com.example.dpitangui.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dpitangui on 07/03/2018.
 */

public class ListViewAdapter extends ArrayAdapter<String> {

    Context context;
    private final String[] produto_codigo;
    private final String[] produto_nome;
    private final String[] produto_qtd;


    public ListViewAdapter(Context context,String[] codigo, String[] nome, String[] quantidade)
    {
        super(context, R.layout.list_item, codigo);

        this.context = (Activity) context;
        this.produto_codigo=codigo;
        this.produto_nome= nome;
        this.produto_qtd = quantidade;

    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Aqui retornamos o layout para podermos administrar as Views da tela
        View rowView= inflater.inflate(R.layout.list_item, null, true);

        //---retorne a referencia de todos os objetos do layout
        TextView txtProdutoNome = (TextView) rowView.findViewById(R.id.produto_nome);
        TextView txtProdutoCodigo = (TextView)rowView.findViewById(R.id.produto_codigo);
        TextView txtProdutoQtd = (TextView)rowView.findViewById(R.id.produto_qtd);
        Button btnExcluir = (Button) rowView.findViewById(R.id.btnExcluir);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        //---passe os textos baseados na posição atual do listView
        txtProdutoCodigo.setText(produto_codigo[position]);
        txtProdutoNome.setText(produto_nome[position]);
        txtProdutoQtd.setText(produto_qtd[position]);
        btnExcluir.setText(produto_codigo[position]);
        //imageView.setImageResource(imageIds[position]);

        return rowView;
    }
}

package com.example.dpitangui.myfirstapp;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dpitangui on 01/03/2018.
 */

//@JsonIgnoreProperties({"codibge", "codestado"}) Isso faz: Olha Jackson, existe as propriedades codibge e codestado em nossa resposta, porém na hora de realizar a conversão para um objeto, pode ignorá-las

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"dt_atualiza"})
public class Usuario {

    private String cod_id;
    private String usuario;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String dt_atualiza;


    public String getCod_id() {
        return cod_id;
    }

    public void setCod_id(String cod_id) {
        this.cod_id = cod_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDt_atualiza() {
        return dt_atualiza;
    }

    public void setDt_atualiza(String dt_atualiza) {
        this.dt_atualiza = dt_atualiza;
    }
}

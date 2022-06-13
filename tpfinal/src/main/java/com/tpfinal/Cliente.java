package com.tpfinal;

import java.util.ArrayList;
import java.util.Date;

/**
 * Cliente
 */
public class Cliente {
  private static int ID_COUNTER = 0;
  protected static double DESCONTO = 0;
  protected static double MENSALIDADE = 0;

  private int id;
  private String usuario;
  private String nome;
  private String senha;
  private ArrayList<Compra> compras;

  Cliente(String usuario, String nome, String senha) {
    this.id = ++Cliente.ID_COUNTER;
    this.setUsuario(usuario);
    this.setNome(nome);
    this.setSenha(senha);
  }

  public void addCompra(Compra compra) {

  }

  public void addCompra(ArrayList<Compra> compras) {

  }

  public void verHistorico() {

  }

  public void verHistorico(Date data) {

  }

  public void verHistorico(String categoria) {
    
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

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public ArrayList<Compra> getCompras() {
    return compras;
  }

  public void setCompras(ArrayList<Compra> compras) {
    this.compras = compras;
  }

  @Override
  public String toString() {
    return "Tipo: " + this.getClass().getSimpleName() + " > id: " + this.id + " nome: " + this.nome + " | usuário: " + this.usuario + " | senha: " + this.senha;
  }
}
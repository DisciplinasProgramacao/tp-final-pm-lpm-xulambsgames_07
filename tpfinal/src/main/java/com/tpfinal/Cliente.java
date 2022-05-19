package com.tpfinal;

import java.util.ArrayList;
import java.util.Date;

/**
 * Cliente
 */
public class Cliente {
  protected static double DESCONTO = 0;
  protected static double MENSALIDADE = 0;

  private String usuario;
  private String nome;
  private String senha;
  private ArrayList<Compra> compras;

  Cliente(String usuario, String nome, String senha) {
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

  public void verHistorico(Jogo categoria) {
    
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
}
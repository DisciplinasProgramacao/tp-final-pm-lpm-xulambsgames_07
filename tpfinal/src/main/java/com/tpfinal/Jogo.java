package com.tpfinal;

public class Jogo {
  private static int ID_COUNTER = 0;

  protected double multiplicador = 1D;
  private int id;
  private double precoBase;
  private String nome;

  Jogo(double precoBase, String nome) {
    this.id = ++Jogo.ID_COUNTER;
    this.setNome(nome);
    this.setPrecoBase(precoBase);
  }

  public double getPrecoVenda() {
    return this.getPrecoBase() * this.multiplicador;
  }

  public double getPrecoBase() {
    return precoBase;
  }

  public void setPrecoBase(double precoBase) {
    this.precoBase = precoBase;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return "Tipo: " + this.getClass().getSimpleName() + " > Id: " + this.id + " nome: " + this.nome + " | preco: " + this.getPrecoVenda();
  }
}

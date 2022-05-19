package com.tpfinal;

public class Jogo {
  protected double multiplicador = 1D;
  private double precoBase;
  private String nome;

  Jogo(double precoBase, String nome) {
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
}

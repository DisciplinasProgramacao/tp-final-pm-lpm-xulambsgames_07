package com.tpfinal;

public class Promocao extends Jogo {
  protected double multiplicador = 1;

  Promocao(double precoBase, String nome, double multiplicador) {
    super(precoBase, nome);
    this.setMultiplicador(multiplicador);
  }

  private void setMultiplicador(double multiplicador) {
    if (multiplicador > 0.5 || multiplicador < 0.3) {
      throw new IllegalArgumentException("O valor de um jogo regular deve estar entre 70 e 100% do valor original");
    }

    this.multiplicador = multiplicador;
  }
}

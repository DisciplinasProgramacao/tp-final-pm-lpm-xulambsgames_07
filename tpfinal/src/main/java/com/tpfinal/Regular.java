package com.tpfinal;

public class Regulares extends Jogo {
  protected double multiplicador = 1;

  Regulares(double precoBase, String nome, double multiplicador) {
    super(precoBase, nome);
    this.setMultiplicador(multiplicador);
  }

  private void setMultiplicador(double multiplicador) {
    if (multiplicador > 1 || multiplicador < 0.7) {
      throw new IllegalArgumentException("O valor de um jogo regular deve estar entre 70 e 100% do valor original");
    }

    this.multiplicador = multiplicador;
  }
}

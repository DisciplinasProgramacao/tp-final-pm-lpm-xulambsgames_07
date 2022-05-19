package com.tpfinal;

/**
 * Cliente
 */
public class Fanatico extends Cliente {
  protected static double DESCONTO = 0.3;
  protected static double MENSALIDADE = 25D;

  Fanatico(String usuario, String nome, String senha) {
    super(usuario, nome, senha);
  }
}
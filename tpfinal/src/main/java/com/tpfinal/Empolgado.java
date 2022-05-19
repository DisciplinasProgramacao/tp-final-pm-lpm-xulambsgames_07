package com.tpfinal;

/**
 * Cliente
 */
public class Empolgado extends Cliente {
  protected static double DESCONTO = 0.1;
  protected static double MENSALIDADE = 10D;

  Empolgado(String usuario, String nome, String senha) {
    super(usuario, nome, senha);
  }
}
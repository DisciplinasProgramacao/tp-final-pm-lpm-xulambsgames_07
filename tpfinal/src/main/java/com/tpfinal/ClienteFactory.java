package com.tpfinal;

import java.util.List;
import java.util.Scanner;

public class ClienteFactory {
  private static ClienteFactory instance;
  List<String> tiposCliente;

  private ClienteFactory() {
    this.tiposCliente = List.of("cadastrado", "empolgado", "fanatico");
  }

  public static ClienteFactory getInstance() {
    if (instance == null) {
      instance = new ClienteFactory();
    }

    return instance;
  }

  public Cliente criarPeloTerminal(Scanner scanner) {
    String tipoCliente = ScannerUtils.lerValor(scanner, "Tipo do cliente (cadastrado/empolgado/fanatico): ", this.tiposCliente);
    String nomeCliente = ScannerUtils.lerValor(scanner, "Nome: ");
    String nomeUsuarioCliente = ScannerUtils.lerValor(scanner, "Nome de usuário: ");
    String senhaCliente = ScannerUtils.lerValor(scanner, "Senha: ");

    return criar(tipoCliente, nomeCliente, nomeUsuarioCliente, senhaCliente);
  }

  public Cliente criar(String tipoCliente, String nomeCliente, String nomeUsuarioCliente, String senhaCliente) {
    if (!this.tiposCliente.contains(tipoCliente)) {
      throw new IllegalArgumentException("Tipo de cliente inválido: " + tipoCliente);
    }

    Cliente cliente = null;

    switch (tipoCliente.toLowerCase()) {
      case "cadastrado":
        cliente = new Cadastrado(nomeUsuarioCliente, nomeCliente, senhaCliente);
        break;
      case "empolgado":
        cliente = new Empolgado(nomeUsuarioCliente, nomeCliente, senhaCliente);
        break;
      case "fanatico":
        cliente = new Fanatico(nomeUsuarioCliente, nomeCliente, senhaCliente);
        break;
    }

    return cliente;
  }
}

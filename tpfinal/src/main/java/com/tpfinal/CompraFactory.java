package com.tpfinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompraFactory {
  private static CompraFactory instance;
  private List<Jogo> refJogos;
  private List<Cliente> refClientes;
  private LocalDate tempData;
  private Cliente tempCliente;
  private List<Jogo> tempJogos;

  private CompraFactory(List<Jogo> jogos, List<Cliente> clientes) {
    this.refJogos = jogos;
    this.refClientes = clientes;
  }

  public static CompraFactory getInstance(List<Jogo> jogos, List<Cliente> clientes) {
    if (instance == null) {
      instance = new CompraFactory(jogos, clientes);
    }

    return instance;
  }

  private void printMenuPrincipal() {
    System.out.println("=======================================");
    System.out.println("= 1 - Definir data de venda           =");
    System.out.println("= 2 - Definir cliente                 =");
    System.out.println("= 3 - Adicionar jogo                  =");
    System.out.println("= 4 - Cadastrar compra                =");
    System.out.println("= 0 - Cancelar cadastro               =");
    System.out.println("=======================================");
  }

  private void processarInstrucao(Scanner scanner, String instrucao) {
    switch (instrucao) {
      case "1": {
        int dia = Integer.parseInt(ScannerUtils.lerValor(scanner, "Dia: "));
        int mes = Integer.parseInt(ScannerUtils.lerValor(scanner, "Mês: "));
        int ano = Integer.parseInt(ScannerUtils.lerValor(scanner, "Ano: "));

        this.tempData = LocalDate.of(ano, mes, dia);
        break;
      }
      case "2": {
        String mostrarListaDeClientes = ScannerUtils.lerValor(scanner, "\tListar clientes? (s/n)", List.of("s", "n"));

        if (mostrarListaDeClientes.equals("s")) {
          this.refClientes.stream().forEach(System.out::println);
        }

        int idCliente = Integer.parseInt(ScannerUtils.lerValor(scanner, "\tId do cliente: "));

        if (this.refClientes.get(idCliente - 1) != null) {
          this.tempCliente = this.refClientes.get(idCliente - 1);
        }
        break;
      }
      case "3": {
        String mostrarListaDeJogos = ScannerUtils.lerValor(scanner, "\tListar jogos? (s/n)", List.of("s", "n"));

        if (mostrarListaDeJogos.equals("s")) {
          this.refJogos.stream().forEach(System.out::println);
        }

        int idJogo = Integer.parseInt(ScannerUtils.lerValor(scanner, "\tId do jogo: "));

        if (this.refJogos.get(idJogo - 1) != null) {
          if (this.tempJogos == null) {
            this.tempJogos = new ArrayList<Jogo>();
          }

          this.tempJogos.add(this.refJogos.get(idJogo - 1));
        }
        break;
      }
      case "4": {
        if (this.tempCliente == null) {
          System.out.println("Cliente não definido");
          break;
        }

        if (this.tempJogos == null || this.tempJogos.size() == 0) {
          System.out.println("Sem jogos inclusos na venda");
          break;
        }
      }
    }
  }

  public Compra criarPeloTerminal(Scanner scanner) {
    String instrucao = "";

    do {
      printMenuPrincipal();

      instrucao = ScannerUtils.lerInstrucao(scanner);

      processarInstrucao(scanner, instrucao);
    } while (!instrucao.equals("0") && !instrucao.equals("4"));

    if (instrucao.equals("0")) {
      return null;
    } 

    Compra novaCompra = criar(this.tempData, this.tempCliente, this.tempJogos);
    this.tempCliente.addCompra(novaCompra);
    
    this.tempData = null;
    this.tempCliente = null;
    this.tempJogos = null;

    return novaCompra;
  }

  public Compra criar(LocalDate data, Cliente cliente, List<Jogo> jogos) {
    return new Compra(data, cliente, jogos);
  }
}
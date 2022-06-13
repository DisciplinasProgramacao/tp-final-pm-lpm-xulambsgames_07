package com.tpfinal;

import java.util.List;
import java.util.Scanner;

public class JogoFactory {
  private static JogoFactory instance;
  List<String> tiposJogo;

  private JogoFactory() {
    this.tiposJogo = List.of("lancamento", "premium", "regular", "promocao");
  }

  public JogoFactory getInstance() {
    if (instance == null) {
      instance = new JogoFactory();
    }

    return instance;
  }

  public Jogo criarPeloTerminal(Scanner scanner) {
    String tipoJogo = ScannerUtils.lerValor(scanner, "Tipo do jogo (lancamento/premium/regular/promocao): ", this.tiposJogo);
    String nomeJogo = ScannerUtils.lerValor(scanner, "Nome: ");
    double precoJogo = Double.parseDouble(ScannerUtils.lerValor(scanner, "Preço: "));
    double multiplicador = -1;

    if (tipoJogo == "regular") {
      multiplicador = Double.parseDouble(ScannerUtils.lerValor(scanner, "Multiplicador de preço: "));
    }

    return criar(tipoJogo, nomeJogo, precoJogo, multiplicador);
  }

  public Jogo criar(String tipoJogo, String nomeJogo, double precoJogo, double multiplicador) {
    if (!this.tiposJogo.contains(tipoJogo)) {
      throw new IllegalArgumentException("Tipo de jogo inválido: " + tipoJogo);
    }

    Jogo jogo = null;

    switch (tipoJogo.toLowerCase()) {
      case "lancamento":
        jogo = new Lancamento(precoJogo, nomeJogo);
        break;
      case "premium":
        jogo = new Premium(precoJogo, nomeJogo);
        break;
      case "regular":
        jogo = new Regular(precoJogo, nomeJogo, multiplicador);
        break;
    }

    return jogo;
  }
}

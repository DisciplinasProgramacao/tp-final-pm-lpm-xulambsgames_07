package com.tpfinal;

import java.util.List;
import java.util.Scanner;

public class ScannerUtils {
  public static String lerValor(Scanner scanner, String label) {
    return lerValor(scanner, label, List.of());
  }

  public static String lerValor(Scanner scanner, String label, List<String> restricao) {
    String valor = "";
    Boolean invalido = false;

    do {
      System.out.println(label);
      valor = scanner.nextLine();
      invalido = valor == "" || restricao.contains(valor);

      if (invalido) {
        System.out.println("Valor inválido.\n");
      }
    } while (invalido);

    return valor;
  }
}

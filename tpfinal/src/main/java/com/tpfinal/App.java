package com.tpfinal;

import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    public static void printMenu() {
        System.out.println("===============================================");
        System.out.println("= 1 - Cadastrar cliente                       =");
        System.out.println("= 2 - Cadastrar jogo                          =");
        System.out.println("= 3 - Cadastrar compra                        =");
        System.out.println("= 0 - Encerrar e salvar dados                 =");
        System.out.println("===============================================");
    }

    public static String lerInstrucao(Scanner scanner) {
        return lerInstrucao("Escolha uma opção: ", scanner);
    }

    public static String lerInstrucao(String label, Scanner scanner) {
        System.out.println(label);

        return scanner.nextLine();
    }

    public static void processarInstrucao(String instrucao) {
        switch (instrucao) {
            case "1":
                                
                break;
        
            default:
                System.out.println("Instrução não encontrada.\n");
                break;
        }
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        String instrucao = "";
        Scanner scanner = new Scanner(System.in);
        
        do {
            printMenu(); 
            
            instrucao = lerInstrucao(scanner);

            processarInstrucao(instrucao);
        } while (!instrucao.equals("0"));
    }
}

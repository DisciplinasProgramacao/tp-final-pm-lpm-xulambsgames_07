package com.tpfinal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static ArrayList<Jogo> jogos = new ArrayList<Jogo>();
    private static ArrayList<Compra> compras = new ArrayList<Compra>();
    
    private App() {
    }

    public static void printMenu() {
        System.out.println("===============================================");
        System.out.println("= 1 - Cadastrar cliente                       =");
        System.out.println("= 2 - Cadastrar jogo                          =");
        System.out.println("= 3 - Cadastrar compra                        =");
        System.out.println("= 4 - Listar clientes                         =");
        System.out.println("= 5 - Listar jogos                            =");
        System.out.println("= 6 - Listar compras                          =");
        System.out.println("= 7 - Listar histórico de usuário             =");
        System.out.println("= 0 - Encerrar e salvar dados                 =");
        System.out.println("===============================================");
    }

    public static void processarInstrucao(Scanner scanner, String instrucao) {
        switch (instrucao) {
            case "1":
                App.clientes.add(ClienteFactory.getInstance().criarPeloTerminal(scanner));
                break;
            case "2":
                App.jogos.add(JogoFactory.getInstance().criarPeloTerminal(scanner));
                break;
            case "3":
                App.compras.add(CompraFactory.getInstance(App.jogos, App.clientes).criarPeloTerminal(scanner));
                break;
            case "4":
                App.clientes.stream().forEach(System.out::println);
                break;
            case "5":
                App.jogos.stream().forEach(System.out::println);
                break;
            case "6":
                App.compras.stream().forEach(System.out::println);
                break;
            default:
                System.out.println("Instrução não encontrada.\n");
                break;
        }

        System.out.println("\n\n");
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
            
            instrucao = ScannerUtils.lerInstrucao(scanner);

            processarInstrucao(scanner, instrucao);
        } while (!instrucao.equals("0"));
    }
}

package com.tpfinal;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public final class App {
	private static List<Cliente> clientes = lerArquivoCliente("cliente.dat");
	private static List<Jogo> jogos = lerArquivoJogo("jogo.dat");
	private static List<Compra> compras = lerArquivoCompra("compra.dat");

	private App() {
	}

	public static List<Cliente> lerArquivoCliente(String arquivoCliente) {

		List<Cliente> ListaCliente = new ArrayList<>();

		try (ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(arquivoCliente))) {
			Cliente objeto = (Cliente) leitor.readObject();
			while (objeto != null) {
				ListaCliente.add(objeto);
				objeto = (Cliente) leitor.readObject();
			}

		} catch (EOFException e) {
			// TODO Auto-generated catch block
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}

		return ListaCliente;
	}

	public static List<Jogo> lerArquivoJogo(String arquivoJogo) {

		List<Jogo> ListaJogo = new ArrayList<>();

		try (ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(arquivoJogo))) {
			Jogo objeto = (Jogo) leitor.readObject();
			while (objeto != null) {
				ListaJogo.add(objeto);
				objeto = (Jogo) leitor.readObject();
			}

		} catch (EOFException e) {
			// TODO Auto-generated catch block
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}

		return ListaJogo;
	}

	public static List<Compra> lerArquivoCompra(String arquivoCompra) {

		List<Compra> ListaCompra = new ArrayList<>();

		try (ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(arquivoCompra))) {
			Compra objeto = (Compra) leitor.readObject();
			while (objeto != null) {
				ListaCompra.add(objeto);
				objeto = (Compra) leitor.readObject();
			}

		} catch (EOFException e) {
			// TODO Auto-generated catch block
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}

		return ListaCompra;
	}

	public static void salvarArquivo(String arquivoSalvo, List<? extends Serializable> lista) {

		try (ObjectOutputStream gravador = new ObjectOutputStream(new FileOutputStream(arquivoSalvo))) {
			for (Serializable objeto : lista) {
				gravador.writeObject(objeto);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		case "7":
            Optional<Cliente> cliente;

            do {
                Integer idCliente = Integer.parseInt(ScannerUtils.lerInstrucao("Insira o id do Cliente:", scanner));
                cliente = clientes.stream().filter((c) -> c.getId() == idCliente).findFirst();

                if (cliente.isEmpty()) {
                    System.out.println("Cliente não encontrado.");
                }
            } while (cliente.isEmpty());

            String instrucaoHistorico = "";

            do {
                System.out.println("=======================================");
                System.out.println("= 1 - Filtrar pela data da compra     =");
                System.out.println("= 2 - Filtrar pela categoria          =");
                System.out.println("= 3 - Exibir histórico completo       =");
                System.out.println("= 0 - Voltar                          =");
                System.out.println("=======================================");

                instrucaoHistorico = ScannerUtils.lerInstrucao("Selecione a opção desejada: ", scanner);

                switch (instrucaoHistorico) {
                    case "1":
                        int dia = Integer.parseInt(ScannerUtils.lerValor(scanner, "Dia: "));
                        int mes = Integer.parseInt(ScannerUtils.lerValor(scanner, "Mês: "));
                        int ano = Integer.parseInt(ScannerUtils.lerValor(scanner, "Ano: "));
                
                        LocalDate data = LocalDate.of(ano, mes, dia);
                        System.out.println(cliente.get().historicoData(data));
                        break;
                    case "2":
                        String categoria = ScannerUtils.lerValor(scanner, "Tipo do jogo (lancamento/premium/regular/promocao): ", JogoFactory.getInstance().tiposJogo);
                        System.out.println(cliente.get().historicoCategoria(categoria));
                        break;
                    case "3":
                        System.out.println(cliente.get().historico());
                        break;
                    default:
                        break;
                }
            } while (!instrucaoHistorico.equals("0"));
			break;
        case "0":
            System.out.println("Salvando dados...");
            salvarArquivo("cliente.dat", clientes);
            salvarArquivo("jogo.dat", jogos);
            salvarArquivo("compra.dat", compras);
            break;
		default:
			System.out.println("Instrução não encontrada.\n");
			break;
		}

		System.out.println("\n\n");
	}
    
	public static void main(String[] args) {
		Cliente.setID_COUNTER(clientes.size());
		Jogo.setID_COUNTER(jogos.size());
		String instrucao = "";
		Scanner scanner = new Scanner(System.in);

		do {
			printMenu();

			instrucao = ScannerUtils.lerInstrucao(scanner);

			processarInstrucao(scanner, instrucao);
		} while (!instrucao.equals("0"));
	}
}
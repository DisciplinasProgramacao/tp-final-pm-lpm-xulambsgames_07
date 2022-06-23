package tpfinal;

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
			System.out.println("Insira o id do Cliente:");
			int a = scanner.nextInt();
			System.out.println("Filtro pela Data de jogo:");
			System.out.println(clientes.get(a).historicoData(LocalDate.now()));
			System.out.println("==================================");
			System.out.println("Insira a  categoria: (premium,regular,lancamento,promocao");
			String cat = scanner.next();
			System.out.println(clientes.get(a).historicoCategoria(cat));
			break;
		default:
			System.out.println("Instrução não encontrada.\n");
			break;
		}

		System.out.println("\n\n");
	}

	/**
	 * Says hello to the world.
	 * 
	 * @param args The arguments of the program.
	 */
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

		salvarArquivo("cliente.dat", clientes);
		salvarArquivo("jogo.dat", jogos);
		salvarArquivo("compra.dat", compras);

	}
}
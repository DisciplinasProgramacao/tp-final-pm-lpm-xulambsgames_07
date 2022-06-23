package tpfinal;

/**
 * Cliente
 */
public class Empolgado extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static double DESCONTO = 0.1;
	protected static double MENSALIDADE = 10D;

	Empolgado(String usuario, String nome, String senha) {
		super(usuario, nome, senha);
	}
}
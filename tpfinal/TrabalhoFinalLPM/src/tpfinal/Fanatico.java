package tpfinal;

/**
 * Cliente
 */
public class Fanatico extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static double DESCONTO = 0.3;
	protected static double MENSALIDADE = 25D;

	Fanatico(String usuario, String nome, String senha) {
		super(usuario, nome, senha);
	}
}

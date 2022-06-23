package tpfinal;

public class Regular extends Jogo {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected double multiplicador = 1;

  Regular(double precoBase, String nome, double multiplicador) {
    super(precoBase, nome);
    this.setMultiplicador(multiplicador);
  }

  private void setMultiplicador(double multiplicador) {
    if (multiplicador > 1 || multiplicador < 0.7) {
      throw new IllegalArgumentException("O valor de um jogo regular deve estar entre 70 e 100% do valor original");
    }

    this.multiplicador = multiplicador;
  }
}

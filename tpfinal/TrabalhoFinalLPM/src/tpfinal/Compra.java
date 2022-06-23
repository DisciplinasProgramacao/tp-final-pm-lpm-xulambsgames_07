package tpfinal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Compra
 */
@SuppressWarnings("unused")
public class Compra implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate data;
	private Cliente cliente;
	private List<Jogo> itensCompra;
	private double precoBase;
	private double descontosAplicados = 0D;

	Compra(LocalDate data, Cliente cliente, List<Jogo> itensCompra) {
		this.setData(data);
		this.setCliente(cliente);
		this.setItensCompra(itensCompra);
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Jogo> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<Jogo> itensCompra) {
		this.itensCompra = itensCompra;
		this.recalcularValores();
	}

	public boolean filterCategoria(String categoria) {
		for(Jogo j: itensCompra) {
			if (j.getClass().getSimpleName().equalsIgnoreCase(categoria))
				return true;
		}
		return false;
	}
	
	private void recalcularValores() {
		List<String> tiposJogo = JogoFactory.getInstance().tiposJogo;
		int[] jogosPorTipo = new int[tiposJogo.size()];

		this.precoBase = 0;

		for (Jogo jogo : itensCompra) {
			this.precoBase += jogo.getPrecoVenda();
			String tipoJogo = jogo.getClass().getSimpleName().toLowerCase();

			if (jogosPorTipo[tiposJogo.indexOf(tipoJogo)] > 0) {
				jogosPorTipo[tiposJogo.indexOf(tipoJogo)]++;
			} else {
				jogosPorTipo[tiposJogo.indexOf(tipoJogo)] = 1;
			}
		}

		this.descontosAplicados = this.calcularPorcentagemDesconto(jogosPorTipo);
	}

	private double calcularPorcentagemDesconto(int[] relacaoPorTipoDeJogo) {
		int totalDeJogos = 0;

		for (int quant : relacaoPorTipoDeJogo) {
			totalDeJogos += quant;
		}

		Boolean condicaoBase = relacaoPorTipoDeJogo[0] > 2 || (relacaoPorTipoDeJogo[1] == 2 && totalDeJogos >= 3)
				|| (relacaoPorTipoDeJogo[1] == 3);

		if (condicaoBase || (condicaoBase && relacaoPorTipoDeJogo[2] == 3) || relacaoPorTipoDeJogo[2] == 5) {
			return 0.2;
		} else if (relacaoPorTipoDeJogo[1] == 2 || relacaoPorTipoDeJogo[2] == 4) {
			return 0.1;
		}

		return 0D;
	}

	private double getValorTotal() {
		return this.precoBase * (1 - this.descontosAplicados - this.cliente.DESCONTO);
	}

	@Override
	public String toString() {
		return "Cliente: " + this.cliente.getNome() + " > jogos: "
				+ Arrays.toString(this.itensCompra.stream().map((c) -> c.getNome()).toArray()) + " | valor base: "
				+ this.precoBase + " | valor após descontos: " + this.getValorTotal();
	}
}
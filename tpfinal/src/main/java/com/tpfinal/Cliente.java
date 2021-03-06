package com.tpfinal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Cliente
 */
@SuppressWarnings("unused")
public class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int ID_COUNTER = 0;
	protected static double DESCONTO = 0;
	protected static double MENSALIDADE = 0;

	private int id;
  private String usuario;
	private String nome;
	private String senha;
	private List<Compra> compras;
	
	public static void setID_COUNTER(int id){
		Cliente.ID_COUNTER = id;
	}

	Cliente(String usuario, String nome, String senha) {
		this.id = ++Cliente.ID_COUNTER;
		this.setUsuario(usuario);
		this.setNome(nome);
		this.setSenha(senha);
		this.compras = new ArrayList<>();
	}
	
	public void addCompra(Compra compra) {
		compras.add(compra);
	}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

  public String historico() {
		StringBuilder hist = new StringBuilder();
    hist.append("\n");
		this.compras.stream().forEach(c -> hist.append(c + " \n"));
		return hist.toString();
	}

	public String historicoData(LocalDate data) {
		StringBuilder hist = new StringBuilder();
    hist.append("\n");
		this.compras.stream().filter(c -> c.getData().isEqual(data)).forEach(c -> hist.append(c + " \n"));
		return hist.toString();
	}
	
	public String historicoCategoria(String categoria) {
		StringBuilder hist = new StringBuilder();
    hist.append("\n");
    this.compras.stream().filter(c -> c.filterCategoria(categoria)).forEach(c -> hist.append(c + "\n"));
		return hist.toString();
	}

	@Override
	public String toString() {
		return "Tipo: " + this.getClass().getSimpleName() + " > id: " + this.id + " nome: " + this.nome + " | usu??rio: "
				+ this.usuario + " | senha: " + this.senha;
	}
}
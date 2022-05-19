package com.tpfinal;

import java.util.ArrayList;
import java.util.Date;

/**
 * Compra
 */
public class Compra {
  private Date data;
  private ArrayList<Jogo> itensCompra;
  
  Compra(Date data, ArrayList<Jogo> itensCompra) {
    this.setData(data);
    this.setItensCompra(itensCompra);
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public ArrayList<Jogo> getItensCompra() {
    return itensCompra;
  }

  public void setItensCompra(ArrayList<Jogo> itensCompra) {
    this.itensCompra = itensCompra;
  }
  
}
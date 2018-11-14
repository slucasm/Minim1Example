package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

public class User {

    String name;
    LinkedList<Pedido> historialPedido;

    public User(String name)
    {
        this.name = name;
        this.historialPedido = new LinkedList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LinkedList<Pedido> getHistorialPedido() {
        return historialPedido;
    }
    public void setHistorialPedido(LinkedList<Pedido> historialPedido) {
        this.historialPedido = historialPedido;
    }

    public void addPedido(Pedido p)
    {
        this.historialPedido.add(p);
    }
}

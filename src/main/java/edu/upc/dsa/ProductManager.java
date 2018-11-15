package edu.upc.dsa;

import java.util.HashMap;
import java.util.List;

public interface ProductManager {
    List<Product> findAllProductsOrderedByPrice();
    List<Product> findAllProductsOrderedBySales();
    void realizarPedido(String user, Pedido P);
    Pedido servirPedido();
    List<Pedido> pedidosUnUsuario(String user);
    void addUser(String user);
    void addProduct(Product p);

    int size();
    List<Product> allProducts();
    HashMap<String, User> allUsers();
}

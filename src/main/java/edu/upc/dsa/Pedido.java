package edu.upc.dsa;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private User user;
    List<LP> products;

    public Pedido(List<LP> products)
    {
        this.products = new ArrayList<>();
        this.products = products;
    }

    public List<LP> getProducts() {
        return products;
    }
    public void setProducts(List<LP> products) {
        this.products = products;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

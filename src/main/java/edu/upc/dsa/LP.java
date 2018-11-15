package edu.upc.dsa;

public class LP {

    public int quantity;
    public Product product;


    public LP(Product product,int quantity){
        this.product = product;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Pedido [Name=" + product + ", Quantity=" + quantity + "]";
    }
}

package edu.upc.dsa;

public class Product {

    String name;
    double price;
    int sales = 0;

    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getSales() {
        return sales;
    }
    public void addSales(int quantity) {
        this.sales =this.sales + quantity;
    }
}

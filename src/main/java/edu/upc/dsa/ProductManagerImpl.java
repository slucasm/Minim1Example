package edu.upc.dsa;

import java.util.*;
import org.apache.log4j.Logger;

public class ProductManagerImpl implements ProductManager {

    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());

    private static ProductManager instance;

    private List<Product> productList;
    private LinkedList<Pedido> pedidoList;
    private HashMap<String,User> userMap;

    private ProductManagerImpl()
    {
        this.productList = new ArrayList<>();
        this.pedidoList = new LinkedList<>();
        this.userMap = new HashMap<>();
    }
    public static ProductManager getInstance(){
        if(instance==null) instance = new ProductManagerImpl();
        return instance;
    }

    public List<Product> findAllProductsOrderedByPrice() {
        List<Product> listOfProductsOrderedByPrice = new ArrayList<>();
        listOfProductsOrderedByPrice.addAll(productList);
        Collections.sort(listOfProductsOrderedByPrice, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {

                return (int) (o1.getPrice()-o2.getPrice());
            }
        });
        log.info("List products ordered by price:" +listOfProductsOrderedByPrice);
        return listOfProductsOrderedByPrice;
    }

    public List<Product> findAllProductsOrderedBySales() {
        List<Product> listOfProductsOrderedBySales = new ArrayList<>();
        listOfProductsOrderedBySales.addAll(productList);
        Collections.sort(listOfProductsOrderedBySales, new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
                return (-1)*(o1.getSales() - o2.getSales());
            }

        });
        log.info("List of products ordered by sales: " +listOfProductsOrderedBySales);
        return listOfProductsOrderedBySales;
    }

    public void realizarPedido(String user, Pedido P) {
        User usuario = this.userMap.get(user);
        log.info("User: " + user);
        if (usuario != null) {
            P.setUser(usuario);
            List<LP> listLP = P.getProducts();
            Product product;
            for (LP lp : listLP) {
                product = lp.product;
                product.addSales(lp.quantity);
                lp.quantity = product.getSales();
            }
            usuario.addPedido(P);
            this.pedidoList.add(P);
        }
        else {
            log.warn("The user don't exist");
        }
    }

    public Pedido servirPedido() {
        Pedido p = this.pedidoList.pop();
        log.info("The order is served");
        log.info("The order is from the user: " +p.getUser().name);
        return p;
    }

    public LinkedList<Pedido> pedidosUnUsuario(String user) {
        User usuario = this.userMap.get(user);
        LinkedList<Pedido> listaPedidosUsuario = new LinkedList<>();

        if (usuario != null) {
            log.info("User: " +user);
            listaPedidosUsuario = usuario.getHistorialPedido();
            log.info("Orders historial: "+listaPedidosUsuario);
        }
        else{
            log.warn("The user don't exist");

        }
        return listaPedidosUsuario;
    }

    public void addUser(String user) {

        userMap.put(user, new User(user));
    }

    public void addProduct(Product p) {
        this.productList.add(p);
    }


    public int size() {
        return productList.size();
    }


    public List<Product> allProducts() {
        return productList;
    }

    public HashMap<String, User> allUsers() {
        return userMap;
    }

}

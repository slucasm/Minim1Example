package edu.upc.dsa;

import org.apache.log4j.Logger;
import org.junit.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductManagerImplTest {

    final static Logger log = Logger.getLogger(ProductManagerImpl.class.getName());
    static ProductManager productManager;
    static Product product1,product2,product3,product4;
    static Pedido pedido1,pedido2;
    static List<LP> listaLP1,listaLP2;

    @BeforeClass
    public static void setUp(){

        productManager =  ProductManagerImpl.getInstance();

        listaLP1 = new ArrayList<>();
        listaLP2 = new ArrayList<>();
        productManager.addUser("Sergi");
        productManager.addUser("Laura");

        product1 = new Product("Cocacola",2);
        product2 = new Product("Fanta",3);
        product3 = new Product("Aigua",1);
        product4 = new Product("Aquarius",4);
        productManager.addProduct(product1);
        productManager.addProduct(product2);
        productManager.addProduct(product3);
        productManager.addProduct(product4);

        LP lp1 = new LP(product1,5);
        LP lp2 = new LP(product2,10);
        LP lp3 = new LP(product3,1);
        LP lp4 = new LP(product4,4);
        listaLP1.add(lp1);
        listaLP1.add(lp2);
        listaLP2.add(lp3);
        listaLP2.add(lp4);
        pedido1 = new Pedido(listaLP1);
        pedido2 = new Pedido(listaLP2);

        //productManager.realizarPedido("Marc",pedido1);
        productManager.realizarPedido("Sergi",pedido1);
        productManager.realizarPedido("Laura",pedido2);

    }


    @Test
    public void realizarPedido(){




        assertEquals(listaLP1.get(0).product.name,"Cocacola");
        assertEquals(listaLP1.get(0).quantity,5);
        assertEquals(listaLP1.get(0).product.getSales(),5);
        assertEquals(pedido1.getUser().getName(),"Sergi");
        assertEquals(pedido1.products.get(0).quantity,5,0);

    }



    @Test
    public void findAllProductsOrderedByPrice(){

        List<Product> listProductsOrderedByPrice = this.productManager.findAllProductsOrderedByPrice();

        assertEquals(listProductsOrderedByPrice.get(0).getName(),"Aigua");
        assertEquals(listProductsOrderedByPrice.get(1).getName(),"Cocacola");
        assertEquals(listProductsOrderedByPrice.get(2).getName(),"Fanta");
        assertEquals(listProductsOrderedByPrice.get(3).getName(),"Aquarius");
    }

    @Test
    public void findAllProductsOrderedBySales(){

        List<Product> listProductsOrderedBySales= this.productManager.findAllProductsOrderedBySales();

        assertEquals(listProductsOrderedBySales.get(0).sales,10,0);
        assertEquals(listProductsOrderedBySales.get(0).getName(),"Fanta");
        assertEquals(listProductsOrderedBySales.get(1).getName(),"Cocacola");
        assertEquals(listProductsOrderedBySales.get(1).getSales(),5,0);
    }


    @Test
    public void historialUser(){

        List<Pedido> historial = this.productManager.pedidosUnUsuario("Sergi");
        assertEquals(historial.get(0).products.get(0).product.getName(),"Cocacola");
        assertEquals(historial.get(0).products.get(1).product.getName(),"Fanta");
        //assertEquals(historial.get(1).products.get(0).product.getName(),"Aigua");
        //assertEquals(historial.get(1).products.get(1).product.getName(),"Aquarius");
    }

    @Test
    public void servirPedido(){

        pedido1 = this.productManager.servirPedido();
        pedido2 = this.productManager.servirPedido();

        assertEquals(pedido1.products.get(0).quantity,5,0);
        assertEquals(pedido1.getUser().getName(),"Sergi");
        assertEquals(pedido1.products.get(0).product.getName(),"Cocacola");

    }

    @AfterClass
    public static void tearDown()
    {
        productManager = null;
    }

}
package edu.upc.dsa.services;

import edu.upc.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Api(value = "/orders", description = "Endpoint to Order Service")
@Path("/orders")
public class OrdersService {

    final static Logger log = Logger.getLogger(OrdersService.class.getName());

    private ProductManager pm;

    public OrdersService(){
        this.pm = ProductManagerImpl.getInstance();
        if (pm.size()==0){
            pm.addProduct(new Product("Cocacola",1));
            pm.addProduct(new Product("Fanta",2));
            pm.addProduct(new Product("Aigua",3));
            pm.addProduct(new Product("Aquarius",4));
            pm.addUser("Sergi");
            pm.addUser("Laura") ;
        }
    }

    @GET
    @ApiOperation(value = "get all products", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List of Products")
    })
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {

        List<Product> productos = this.pm.allProducts();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productos) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get all users", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List of Users")
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        HashMap<String,User> users = this.pm.allUsers();

        GenericEntity<HashMap<String,User>> entity = new GenericEntity<HashMap<String,User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get all orders from user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Pedido.class, responseContainer="List of orders"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersFromUser(@PathParam("user") String user) {

        List<Pedido> pedidos;
        HashMap<String,User> users  = pm.allUsers();
        User usuario = users.get(user);
        if (usuario!= null) {
            pedidos = this.pm.pedidosUnUsuario(user);
            GenericEntity<List<Pedido>> entity = new GenericEntity<List<Pedido>>(pedidos){};
            return Response.status(201).entity(entity).build();
        }
        else{
            return Response.status(404).build();
        }
    }

    @GET
    @ApiOperation(value = "get all products sorted by price", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List of products")
    })
    @Path("/sortedbyprice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsSortedByPrice() {

        List<Product> productos = this.pm.findAllProductsOrderedByPrice();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productos){};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all products sorted by sales", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List of products")
    })
    @Path("/sortedbyosales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsSortedBySales() {

        List<Product> productos = this.pm.findAllProductsOrderedBySales();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productos){};
        return Response.status(201).entity(entity).build();
    }
    @POST
    @ApiOperation(value = "create a new Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Product.class)
    })

    @Path("/addproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProduct(Product producto) {
        this.pm.addProduct(producto);
        return Response.status(201).entity(producto).build();
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class)
    })

    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(String user) {
        this.pm.addUser(user);
        return Response.status(201).entity(user).build();
    }

    @POST
    @ApiOperation(value = "place an Order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{user}placeanorder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeAnOrder(@PathParam("user") String user,Pedido p) {
        HashMap<String,User> users  = pm.allUsers();
        User usuario = users.get(user);

        if (usuario!= null) {

            //p.setUser(usuario);
            this.pm.realizarPedido(user,p);
            return Response.status(201).build();
        }
        else{
            return Response.status(404).build();
        }
    }

    @DELETE
    @ApiOperation(value = "serve an order", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Pedido.class, responseContainer = "Pedido")
    })
    @Path("/serveanorder")
    public Response serveAnOrder() {
        Pedido pedido = this.pm.servirPedido();
        return Response.status(201).entity(pedido).build();
    }




}

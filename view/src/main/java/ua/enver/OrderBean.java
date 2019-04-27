package ua.enver;

import ua.enver.domain.Order;
import ua.enver.domain.Product;
import ua.enver.ejb.OrdersManagerBean;
import ua.enver.ejb.ProductsManagerBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author Enver on 25.04.2019 23:43.
 * @project shop
 */

@Named
@SessionScoped
public class OrderBean implements Serializable {
    private Order order;
    private String name;
    private double price;
    private int quantity;

    @EJB
    private OrdersManagerBean ordersManagerBean;

    @EJB
    private ProductsManagerBean productsManagerBean;

    public void createOrder(){
        if (order == null){
            order = ordersManagerBean.createOrder();
        }
    }

    public void createProduct(){
        productsManagerBean.createProduct(name, price, quantity);
    }

    public List<Product> getProducts(){
        return productsManagerBean.getProducts();
    }

    public void addProduct(Product product){
        if (order == null){
            return;
        }
        ordersManagerBean.addToOrder(product.getId(), order.getId(), product.getPrice(), 1);
    }

    public List<Product> getProductsInOrder(){
        if (order == null){
            return Collections.emptyList();
        }

        return ordersManagerBean.getProductsInOrder(order.getId());
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

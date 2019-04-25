package ua.enver;

import ua.enver.domain.Order;
import ua.enver.domain.Thing;
import ua.enver.ejb.OrdersManagerBean;
import ua.enver.ejb.ThingsManagerBean;

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
    private ThingsManagerBean thingsManagerBean;

    public void createOrder(){
        if (order == null){
            order = ordersManagerBean.createOrder();
        }
    }

    public void createThing(){
        thingsManagerBean.createThing(name, price, quantity);
    }

    public List<Thing> getThings(){
        return thingsManagerBean.getThings();
    }

    public void addThing(Thing thing){
        if (order == null){
            return;
        }
        ordersManagerBean.addToOrder(thing.getId(), order.getId(), thing.getPrice(), 1);
    }

    public List<Thing> getThingsInOrder(){
        if (order == null){
            return Collections.emptyList();
        }

        return ordersManagerBean.getThingInOrder(order.getId());
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

package ua.enver.ejb;

import ua.enver.domain.Order;
import ua.enver.domain.Thing;
import ua.enver.domain.ThingInOrder;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Enver on 25.04.2019 22:29.
 * @project shop
 */

@Stateless
@LocalBean
public class OrdersManagerBean {

    @PersistenceContext(unitName = "shopPU")
    private EntityManager entityManager;

    public Order createOrder() {
        Order order = new Order();
        entityManager.persist(order);
        return order;
    }

    public boolean addToOrder(long thingId, long orderId, double price, int quantity) {
        Thing thing = entityManager.find(Thing.class, thingId);
        if (thing == null) {
            return false;
        }

        Order order = entityManager.find(Order.class, orderId);
        if (order == null) {
            return false;
        }

        ThingInOrder thingInOrder = new ThingInOrder();
        thingInOrder.setOrder(order);
        thingInOrder.setThing(thing);
        thingInOrder.setPrice(price);
        thingInOrder.setQuantity(quantity);
        entityManager.persist(thingInOrder);

        return true;
    }

    public List<Thing> getThingInOrder(long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        if (order == null) {
            return Collections.emptyList();
        }

        List<ThingInOrder> thingInOrders = order.getThingInOrders();
        List<Thing> result = new ArrayList<>();
        for (ThingInOrder thingInOrder : thingInOrders) {
            result.add(thingInOrder.getThing());
        }

        return result;
    }
}

package ua.enver.ejb;

import ua.enver.domain.Thing;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Enver on 25.04.2019 23:12.
 * @project shop
 */

@Stateless
@LocalBean
public class ThingsManagerBean {

    @PersistenceContext(unitName = "shopPU")
    private EntityManager entityManager;

    public Thing createThing(String name, double price, int quantity){
        Thing thing = new Thing();
        thing.setName(name);
        thing.setPrice(price);
        thing.setQuantity(quantity);
        entityManager.persist(thing);

        return thing;
    }

    public List<Thing> getThings() {
        TypedQuery<Thing> query = entityManager.createQuery("select c from Thing c", Thing.class);
        return query.getResultList();
    }
}

package ua.enver.ejb;

import ua.enver.domain.Product;

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
public class ProductsManagerBean {

    @PersistenceContext(unitName = "shopPU")
    private EntityManager entityManager;

    public Product createProduct(String name, double price, int quantity){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        entityManager.persist(product);

        return product;
    }

    public List<Product> getProducts() {
        TypedQuery<Product> query = entityManager.createQuery("select c from Product c", Product.class);
        return query.getResultList();
    }
}

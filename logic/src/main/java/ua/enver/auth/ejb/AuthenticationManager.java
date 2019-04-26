package ua.enver.auth.ejb;

import org.apache.commons.lang3.StringUtils;
import ua.enver.auth.domain.Admin;
import ua.enver.auth.domain.Credentials;
import ua.enver.auth.domain.ShopUser;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Enver on 26.04.2019 23:58.
 * @project shop
 */

@Stateless
@LocalBean
public class AuthenticationManager {
    @PersistenceContext(unitName = "shopPU")
    private EntityManager entityManager;

    public boolean loginAsUser(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return false;
        }

        Credentials credentials = entityManager.find(Credentials.class, email);
        if (credentials == null) {
            return false;
        }

        if (!password.equals(credentials.getPassword())){
            return false;
        }

        ShopUser shopUser = credentials.getShopUser();
        if (shopUser == null){
            return false;
        }

        return true;
    }

    public boolean loginAsAdmin(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return false;
        }

        Credentials credentials = entityManager.find(Credentials.class, email);
        if (credentials == null) {
            return false;
        }

        if (!password.equals(credentials.getPassword())){
            return false;
        }

        Admin admin = credentials.getAdmin();
        if (admin == null){
            return false;
        }

        return true;
    }
}

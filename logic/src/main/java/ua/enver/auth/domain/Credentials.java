package ua.enver.auth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Enver on 26.04.2019 23:44.
 * @project shop
 */

@Entity
public class Credentials {
    @Id
    private String email;
    private String password;

    @OneToOne(mappedBy = "credentials")
    private Admin admin;
    @OneToOne(mappedBy = "credentials")
    private ShopUser shopUser;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ShopUser getShopUser() {
        return shopUser;
    }

    public void setShopUser(ShopUser shopUser) {
        this.shopUser = shopUser;
    }
}

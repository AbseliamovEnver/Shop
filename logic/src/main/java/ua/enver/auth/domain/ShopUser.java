package ua.enver.auth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Enver on 26.04.2019 23:43.
 * @project shop
 */

@Entity
public class ShopUser {
    @Id
    private long id;
    private String name;

    @OneToOne
    private Credentials credentials;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}

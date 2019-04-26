package ua.enver.auth;

import org.apache.commons.lang3.StringUtils;
import ua.enver.auth.ejb.AuthenticationManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Enver on 27.04.2019 0:31.
 * @project shop
 */

@Named
@SessionScoped
public class AuthBean implements Serializable {
    private boolean loggedIn;

    private String login;
    private String password;

    private String requestedPage;

    @EJB
    private AuthenticationManager authenticationManager;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void doLogin(){
        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)){
            loggedIn = false;
            return;
        }

        loggedIn = authenticationManager.loginAsUser(login, password);

        if (loggedIn){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(requestedPage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestedPage() {
        return requestedPage;
    }

    public void setRequestedPage(String requestedPage) {
        this.requestedPage = requestedPage;
    }
}

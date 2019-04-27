package ua.enver.auth;

import ua.enver.auth.domain.ShopUser;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Enver on 27.04.2019 0:23.
 * @project shop
 */

@WebFilter(urlPatterns = {UserLoginFilter.USER_FILTER_URI + "*",
        UserLoginFilter.ADMIN_FILTER_URI + "*"})
public class UserLoginFilter implements Filter {
    public static final String USER_FILTER_URI = "/user/";
    public static final String ADMIN_FILTER_URI = "/admin/";

    @Inject
    private AuthBean authBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        ShopUser.Role role = authBean.getRole();
        if (role != null){
            String uri = request.getRequestURI();
            String beginOfAdminUri = request.getContextPath() + ADMIN_FILTER_URI;
            if (uri.startsWith(beginOfAdminUri) && role != ShopUser.Role.ADMIN){
                response.sendRedirect(request.getContextPath() + "/errors.xhtml");
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        authBean.setRequestedPage(request.getRequestURI());
        response.sendRedirect(request.getContextPath() + "/login.xhtml");
    }

    @Override
    public void destroy() {

    }
}

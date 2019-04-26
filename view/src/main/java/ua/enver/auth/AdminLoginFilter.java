package ua.enver.auth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Enver on 27.04.2019 0:26.
 * @project shop
 */

@WebFilter(urlPatterns = "/admin/*")
public class AdminLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}

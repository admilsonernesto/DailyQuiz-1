package br.com.sidlar.dailyquiz.interceptor;

import br.com.sidlar.dailyquiz.domain.Membro;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ADMILSON on 01/09/14.
 */
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object controller) throws Exception {
        String uri = request.getRequestURI();

        if(uri.endsWith("Login") ||uri.endsWith("EfetuaLogin") || uri.contains("resources")){
            return true;
        }

        Membro membroAutenticado = (Membro) request.getSession().getAttribute("membroAutenticado");
        if(membroAutenticado == null){
            response.sendRedirect("/Login");
            return false;
        }
        return true;
    }
}


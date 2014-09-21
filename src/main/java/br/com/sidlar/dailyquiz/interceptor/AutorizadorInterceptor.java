package br.com.sidlar.dailyquiz.interceptor;

import br.com.sidlar.dailyquiz.infrastructure.autenticacao.AutenticacaoMembro;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Autoriza o acesso do membro a outras URIs caso esteja autenticado.
 * <p>Caso o membro não esteja autenticado ele será redirecionado para a página de Login.
 */
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object controller) throws Exception {
        String uri = request.getRequestURI();

        if(uri.endsWith("Login") || uri.endsWith("CadastroMembro") || uri.contains("resources")){
            return true;
        }

        if(membroNaoEstaAutenticado(request)){
            response.sendRedirect("/Login");
            return false;
        }

        return true;
    }

    private boolean membroNaoEstaAutenticado(HttpServletRequest request) {
        return !(request.getSession().getAttribute("membroAutenticado") instanceof AutenticacaoMembro);
    }
}


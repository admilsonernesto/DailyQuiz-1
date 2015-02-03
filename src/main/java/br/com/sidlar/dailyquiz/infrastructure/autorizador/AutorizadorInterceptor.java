package br.com.sidlar.dailyquiz.infrastructure.autorizador;

import br.com.sidlar.dailyquiz.infrastructure.autenticacao.AutenticacaoMembro;
import br.com.sidlar.dailyquiz.infrastructure.autenticacao.AutenticacaoUtils;
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

//        if(uri.endsWith("Login") || uri.endsWith("CadastroMembro") || uri.contains("resources")){
//            return true;
//        }
//
//        if(!AutenticacaoUtils.membroEstaAutenticadoNaSession(request.getSession())){
//            response.sendRedirect("/Login");
//            return false;
//        }
        return true;
    }
}


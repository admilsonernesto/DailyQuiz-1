package br.com.sidlar.dailyquiz.presentation.login;

import br.com.sidlar.dailyquiz.infrastructure.autenticacao.Autenticador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Efetua o login do membro
 */
@RequestMapping("/Login")
@Controller
public class LoginController {
    @Autowired
    private Autenticador autenticador;

    @RequestMapping(method = RequestMethod.GET)
    public String loginForm() {
        return "/Login/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String efetuaLogin(@RequestParam String email,@RequestParam String senha, HttpServletRequest request) {

        if(!autenticador.autentica(email, senha)){
            request.setAttribute("mensagemErro", "Email ou senha inválida");
            return "/Login/login";
        }

        return "redirect:/";
    }
}

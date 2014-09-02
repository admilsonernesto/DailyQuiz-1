package br.com.sidlar.dailyquiz.presentation;

import br.com.sidlar.dailyquiz.domain.Membro;
import br.com.sidlar.dailyquiz.domain.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ADMILSON on 01/09/14.
 */
@Controller
public class LoginController {

    @Autowired
    private MembroRepository membroRepository;

    @RequestMapping(value ="/Login", method = RequestMethod.GET)
    public String loginForm() {
        return "/Login/login";
    }

    @RequestMapping(value = "/EfetuaLogin", method = RequestMethod.POST)
    public String efetuaLogin(@RequestParam String username,@RequestParam String senha, HttpSession session, HttpServletRequest request) {
        Membro membro = membroRepository.buscaMembroPorUsername(username);
        Autenticador autenticador = new Autenticador(membro, senha, session);

        if(!autenticador.autentica()){
            request.setAttribute("mensagemErro", "Username ou senha inválido");
            return "/Login/login";
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

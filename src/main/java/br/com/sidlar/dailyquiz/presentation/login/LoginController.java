package br.com.sidlar.dailyquiz.presentation.login;

import br.com.sidlar.dailyquiz.infrastructure.autenticacao.Autenticador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String efetuaLogin(@RequestParam String email,@RequestParam String senha, RedirectAttributes redirectAttrs) {
        try {
            autenticador.autentica(email, senha);
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/Login";
        }
        return "redirect:/";
    }
}

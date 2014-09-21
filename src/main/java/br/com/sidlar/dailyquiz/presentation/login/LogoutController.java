package br.com.sidlar.dailyquiz.presentation.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Efetua o logout do membro invalidando a sessão.
 */
@RequestMapping("/Logout")
@Controller
public class LogoutController {

    @RequestMapping(method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

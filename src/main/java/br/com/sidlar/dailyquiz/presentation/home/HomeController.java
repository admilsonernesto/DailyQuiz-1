package br.com.sidlar.dailyquiz.presentation.home;

import br.com.sidlar.dailyquiz.domain.dashboard.DashboardService;
import br.com.sidlar.dailyquiz.domain.membro.Membro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.security.Principal;

import static br.com.sidlar.dailyquiz.infrastructure.autenticacao.AutenticacaoUtils.obtemAutenticacaoMembroDaSession;

@RequestMapping("/")
@Controller
public class HomeController {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(method = RequestMethod.GET)
    public String goHome(ModelMap modelMap, HttpServletRequest request, @AuthenticationPrincipal Membro membroAutenticado) {
//        Membro membro = obtemAutenticacaoMembroDaSession(session).getMembro();
        modelMap.addAttribute("dashboard", dashboardService.criaDashboard(membroAutenticado));
        return "/Home/home";
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public String goHome2(ModelMap modelMap) {
        return "/Home/home";
    }
}

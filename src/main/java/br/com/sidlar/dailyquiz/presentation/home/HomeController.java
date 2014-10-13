package br.com.sidlar.dailyquiz.presentation.home;

import br.com.sidlar.dailyquiz.domain.Membro;
import br.com.sidlar.dailyquiz.infrastructure.autenticacao.AutenticacaoMembro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@RequestMapping("/")
@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String goHome(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("mensagemParabenizacao", getMensagemParabenizacao(session));
        return "Home/index";
    }

    private String getMensagemParabenizacao(HttpSession session) {
        Membro membro = ((AutenticacaoMembro)session.getAttribute("membroAutenticado")).getMembro();
        if (membro.fazAniversarioHoje()){
            return String.format("%s, parabéns pelos seus %s anos de vida.",membro.getNome(),membro.getIdade());
        }

        if(membro.getQuantidadeDiasParaProximoAniversario().getDays() > 0 && membro.getQuantidadeDiasParaProximoAniversario().getDays() <=5){
            return String.format("%s, falta(m) apenas %s dia(s) para o seu aniversário.",membro.getNome(),membro.getQuantidadeDiasParaProximoAniversario().getDays());
        }
        return "";
    }
}

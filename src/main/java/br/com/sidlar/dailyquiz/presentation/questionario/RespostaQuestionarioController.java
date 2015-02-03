package br.com.sidlar.dailyquiz.presentation.questionario;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import br.com.sidlar.dailyquiz.domain.resposta.CartaoResposta;
import br.com.sidlar.dailyquiz.domain.resposta.RespostaQuestionario;
import br.com.sidlar.dailyquiz.domain.resposta.RespostaService;
import br.com.sidlar.dailyquiz.infrastructure.autenticacao.AutenticacaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Admilson
 */
@RequestMapping("/RespostaQuestionario")
@Controller
public class RespostaQuestionarioController {

    @Autowired
    private RespostaService respostaService;

    @RequestMapping(method = RequestMethod.GET)
    public String preparaResposta(@RequestParam(value = "idQuestionario", required = false) Long idQuestionario, ModelMap modelMap) {
        CartaoResposta cartaoResposta = null;
        try {
            cartaoResposta = respostaService.obtemCartaoResposta(idQuestionario);
        } catch (Exception e) {
            modelMap.addAttribute("mensagemErro",e.getMessage());
        }
        modelMap.addAttribute("cartaoResposta", cartaoResposta);
        return "/RespostaQuestionario/cartaoResposta";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String salvaResposta(@ModelAttribute("cartaoResposta") CartaoResposta cartaoResposta, ModelMap modelMap, HttpSession session) {
        RespostaQuestionario respostaQuestionario;
        try {
            respostaQuestionario = respostaService.salvaRespostaQuestionario(cartaoResposta, getMembro(session));
        } catch (Exception e) {
            modelMap.addAttribute("mensagemErro",e.getMessage());
            return "redirect:/RespostaQuestionario?idQuestionario=" + cartaoResposta.getIdQuestionario();
        }
        return "redirect:/VisualizaResposta?idRespostaQuestionario=" + respostaQuestionario.getId();
    }

    private Membro getMembro(HttpSession session) {
        return AutenticacaoUtils.obtemAutenticacaoMembroDaSession(session).getMembro();
    }
}

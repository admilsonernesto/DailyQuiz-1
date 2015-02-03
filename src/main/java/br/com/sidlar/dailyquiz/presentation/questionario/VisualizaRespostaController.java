package br.com.sidlar.dailyquiz.presentation.questionario;

import br.com.sidlar.dailyquiz.domain.resposta.RespostaQuestionario;
import br.com.sidlar.dailyquiz.domain.resposta.RespostaQuestionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Admilson
 */
@RequestMapping("/VisualizaResposta")
@Controller
public class VisualizaRespostaController {

    @Autowired
    private RespostaQuestionarioRepository respostaQuestionarioRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String visualizaResposta(@RequestParam(value = "idRespostaQuestionario", required = false) Long idRespostaQuestionario, ModelMap modelMap) {
        RespostaQuestionario respostaQuestionario = respostaQuestionarioRepository.buscaPorId(idRespostaQuestionario);
        modelMap.addAttribute("respostaQuestionario", respostaQuestionario);
        return "/RespostaQuestionario/visualizaResposta";
    }

}

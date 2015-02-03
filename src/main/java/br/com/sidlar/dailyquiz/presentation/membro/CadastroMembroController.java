package br.com.sidlar.dailyquiz.presentation.membro;

import br.com.sidlar.dailyquiz.application.CadastroMembroApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 */
@RequestMapping("/CadastroMembro")
@Controller
public class CadastroMembroController {

    @Autowired
    private CadastroMembroApplication cadastroMembroApplication;

    @RequestMapping(method = RequestMethod.GET)
    public String cadastroForm(ModelMap modelMap) {
        modelMap.addAttribute("form", new FormularioMembro());
        return "/Membro/cadastro";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String criaMembro(ModelMap modelMap, @ModelAttribute("form") FormularioMembro form, BindingResult errors) {

        ValidadorFormulario validadorFormulario = new ValidadorFormulario(form, errors);
        if (!validadorFormulario.isPreenchidoCorretamente()){
            modelMap.addAttribute("form", form);
            return "/Membro/cadastro";
        }

        try {
            cadastroMembroApplication.criaMembroDaEspecificacao(form.toEspecificacaoMembro());
        } catch (Exception e) {
            modelMap.addAttribute("mensagemErro", e.getMessage());
        }
        modelMap.addAttribute("form", form);
        return "redirect:/";
    }
}

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
    public String criaMembro(RedirectAttributes redirectAttrs, @ModelAttribute("form") FormularioMembro form, BindingResult errors) {

        ValidadorFormulario validadorFormulario = new ValidadorFormulario(form, errors);
        if (validadorFormulario.isPreenchidoCorretamente()){
            redirectAttrs.addFlashAttribute("form", form);
            return "redirect:/Membro/cadastro";
        }

        try {
            cadastroMembroApplication.criaMembroDaEspecificacao(form.getEspecificacaoMembro());
            redirectAttrs.addFlashAttribute("mensagemSucesso", "Membro cadastrado com sucesso.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("mensagemErro", e.getMessage());
        }
        redirectAttrs.addFlashAttribute("form", form);
        return "redirect:/Membro/cadastro";
    }
}

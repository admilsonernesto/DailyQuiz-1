package br.com.sidlar.dailyquiz.presentation.membro;

import br.com.sidlar.dailyquiz.application.MembroApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@RequestMapping("/CadastroMembro")
@Controller
public class CadastroMembroController {

    @Autowired
    private MembroApplication membroApplication;

    @RequestMapping(method = RequestMethod.GET)
    public String cadastroForm(ModelMap modelMap) {
        return preparaModel(modelMap, new FormularioMembro());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String criaMembro(ModelMap modelMap, @ModelAttribute("form") FormularioMembro form, BindingResult errors) {

        throw new RuntimeException("pelou");


//        ValidadorFormulario validadorFormulario = new ValidadorFormulario(form, errors);
//        if (!validadorFormulario.estaPreenchidoCorretamente()){
//            return preparaModel(modelMap, form);
//        }
//
//        try {
//            membroApplication.salva(form.toEspecificacaoMembro());
//            modelMap.addAttribute("mensagemSucesso", "Membro cadastrado com sucesso.");
//        } catch (Exception e) {
//            return preparaModel(modelMap, form);
//        }
//        return "/Membro/cadastro";
    }

    private String preparaModel(ModelMap modelMap, FormularioMembro form) {
        modelMap.addAttribute("form", form);
        return "/Membro/cadastro";
    }
}
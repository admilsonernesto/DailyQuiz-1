package br.com.sidlar.dailyquiz.presentation.membro;

import br.com.sidlar.dailyquiz.domain.FalhaCriacaoMembroException;
import br.com.sidlar.dailyquiz.infrastructure.utilitarios.ValidacaoEmailUtils;
import org.joda.time.LocalDate;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

/**
 * Valida formulário de cadastro de um novo membro e reporta os erros em BindingResult.
 * @author Admilson
 */
class ValidadorFormulario {

    private FormularioMembro form;
    private BindingResult errors;

    public ValidadorFormulario(FormularioMembro form, BindingResult errors) {
        this.form = form;
        this.errors = errors;
    }

    public boolean isPreenchidoCorretamente() {
        validaNome();
        validaEmail();
        validaDataNascimento();
        validaSenha();
        return !errors.hasErrors();
    }

    public void validaSenha(){
        if(StringUtils.isEmpty(form.getSenha())){
            errors.rejectValue("senha","errors","Senha não informada!");
        }

        if(form.getSenha().length() <= 5){
            errors.rejectValue("senha","errors","Senha deve conter no mínimo 6 caracteres!");
        }

        if(form.getSenha().length() > 10){
            errors.rejectValue("senha","errors","Senha deve conter no máximo 10 caracteres!");
        }

        if(StringUtils.isEmpty(form.getConfirmacaoSenha())){
            errors.rejectValue("senha","errors","Confirmação da senha não informada!");
        }

        if(!form.getSenha().equals(form.getConfirmacaoSenha())){
            errors.rejectValue("senha","errors","Confirmação da senha não confere!");
        }
    }

    public void validaEmail() {
        if(StringUtils.isEmpty(form.getEmail())){
            errors.rejectValue("email", "errors","Email não informado!");
        }

        if(!ValidacaoEmailUtils.isValido(form.getEmail())){
            errors.rejectValue("email", "errors","Email no formato inválido!");
        }
    }

    public void validaNome() {
        if(StringUtils.isEmpty(form.getNome())){
            errors.rejectValue("nome","errors","Nome não informado!");
        }
    }

    public void validaDataNascimento() {
        if(StringUtils.isEmpty(form.getDataNascimento())){
            errors.rejectValue("dataNascimento","errors","Data de nascimento não informada!");
        }

        if(form.getDataNascimento().isAfter(LocalDate.now())){
            errors.rejectValue("dataNascimento","errors","Data de nascimento deve ser menor que hoje!");
        }
    }
}

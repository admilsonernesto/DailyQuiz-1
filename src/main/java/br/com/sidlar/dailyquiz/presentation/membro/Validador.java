package br.com.sidlar.dailyquiz.presentation.membro;

import br.com.sidlar.dailyquiz.infrastructure.autenticacao.ValidacaoEmailUtils;
import org.joda.time.LocalDate;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

/**
 * Valida formulário de cadastro de um novo membro
 * @author Admilson
 */
class Validador {

    private FormularioMembro form;
    private BindingResult errors;

    public Validador(FormularioMembro form, BindingResult errors) {
        this.form = form;
        this.errors = errors;
    }

    public boolean isValido() {

        validaNome();

        validaEmail();

        validaDataNascimento();

        validaSenha();

        return !errors.hasErrors();
    }

    private void validaSenha() {
        if(StringUtils.isEmpty(form.getSenha())){
            errors.rejectValue("senha","errors","Senha não informada!");
            return;
        }

        if(form.getSenha().length() <= 3){
            errors.rejectValue("senha","errors","Senha deve conter no mínimo 4 caracteres!");
            return;
        }

        if(StringUtils.isEmpty(form.getConfirmacaoSenha())){
            errors.rejectValue("confirmacaoSenha","errors","Confirmação da senha não informada!");
            return;
        }

        if(!form.getSenha().equals(form.getConfirmacaoSenha())){
            errors.rejectValue("confirmacaoSenha","errors","Confirmação da senha não confere!");
        }
    }

    private void validaEmail() {
        if(StringUtils.isEmpty(form.getEmail())){
            errors.rejectValue("email","errors","Email não informado!");
            return;
        }

        if(!ValidacaoEmailUtils.isValido(form.getEmail())){
            errors.rejectValue("email","errors","Email inválido!");
        }
    }

    private void validaNome() {
        if(StringUtils.isEmpty(form.getNome())){
            errors.rejectValue("nome","errors","Nome não informado!");
        }
    }

    private void validaDataNascimento() {
        if(StringUtils.isEmpty(form.getDataNascimento())){
            errors.rejectValue("dataNascimento","errors","Data de nascimento não informada!");
            return;
        }

        if(form.getDataNascimento().isAfter(LocalDate.now())){
            errors.rejectValue("dataNascimento","errors","Data de nascimento deve ser menor que hoje!");
        }
    }

}

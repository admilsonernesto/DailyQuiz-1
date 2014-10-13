package br.com.sidlar.dailyquiz.presentation.membro;

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

    private void validaSenha() {
        try {
            form.validaSenha();
        } catch (Exception e) {
            errors.rejectValue("senha","errors",e.getMessage());
        }
    }

    private void validaEmail() {
        try {
            form.validaEmail();
        } catch (Exception e) {
            errors.rejectValue("email", "errors", e.getMessage());
        }
    }

    private void validaNome() {
        try {
            form.validaNome();
        } catch (Exception e) {
            errors.rejectValue("nome","errors",e.getMessage());
        }
    }

    private void validaDataNascimento() {
        try {
            form.validaDataNascimento();
        } catch (Exception e) {
            errors.rejectValue("dataNascimento","errors",e.getMessage());
        }
    }

}

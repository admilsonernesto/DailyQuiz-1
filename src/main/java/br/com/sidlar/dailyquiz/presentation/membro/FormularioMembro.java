package br.com.sidlar.dailyquiz.presentation.membro;

import br.com.sidlar.dailyquiz.domain.EspecificacaoMembro;
import org.joda.time.LocalDate;
import org.springframework.util.StringUtils;

/**
 * @author Admilson
 */
class FormularioMembro {
    private EspecificacaoMembro especificacaoMembro;
    private String confirmacaoSenha;

    public EspecificacaoMembro getEspecificacaoMembro() {
        return especificacaoMembro;
    }

    public void setEspecificacaoMembro(EspecificacaoMembro especificacaoMembro) {
        this.especificacaoMembro = especificacaoMembro;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public void validaEmail(){
        getEspecificacaoMembro().validaEmail();
    }

    public void validaSenha(){
        getEspecificacaoMembro().validaSenha();

        if(StringUtils.isEmpty(getConfirmacaoSenha())){
            throw new IllegalArgumentException("Confirmação da senha não informada!");
        }

        if(!getEspecificacaoMembro().getSenha().equals(getConfirmacaoSenha())){
            throw new IllegalArgumentException("Confirmação da senha não confere!");
        }
    }
    public void validaNome(){
        getEspecificacaoMembro().validaNome();
    }

    public void validaDataNascimento(){
        getEspecificacaoMembro().validaDataNascimento();
    }
}

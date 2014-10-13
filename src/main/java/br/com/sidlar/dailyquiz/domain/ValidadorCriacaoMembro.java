package br.com.sidlar.dailyquiz.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Valida a especificação para a criação de um novo membro
 * @author Admilson
 */
@Component
public class ValidadorCriacaoMembro {

    @Autowired
    private MembroRepository membroRepository;

    public void valida(EspecificacaoMembro especMembro) {
        especMembro.validaNome();

        especMembro.validaSenha();

        especMembro.validaDataNascimento();

        especMembro.validaEmail();

        validaSeExisteMembroComEmailInformado(especMembro);
    }

    private void validaSeExisteMembroComEmailInformado(EspecificacaoMembro especMembro) {
        if (existeEmailCadastrado(especMembro.getEmail())){
            throw new RuntimeException("Já existe um membro com o email informado!");
        }
    }

    private boolean existeEmailCadastrado(String email) {
        try {
            membroRepository.buscaMembroPorEmail(email);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

}

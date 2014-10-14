package br.com.sidlar.dailyquiz.domain;

import br.com.sidlar.dailyquiz.infrastructure.utilitarios.ValidacaoEmailUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Valida a especificação para a criação de um novo membro
 * @author Admilson
 */
@Component
public class ValidadorCriacaoMembro {

    @Autowired
    private MembroRepository membroRepository;

    public void valida(EspecificacaoMembro especMembro) {
        validaNome(especMembro);

        validaSenha(especMembro);

        validaDataNascimento(especMembro);

        validaEmail(especMembro);

        validaSeExisteMembroComEmailInformado(especMembro);
    }

    public void validaNome(EspecificacaoMembro especMembro) {
        if(StringUtils.isEmpty(especMembro.getNome())){
            throw new FalhaCriacaoMembroException("Nome não informado!");
        }
    }

    public void validaSenha(EspecificacaoMembro especMembro){
        if(StringUtils.isEmpty(especMembro.getSenha())){
            throw new FalhaCriacaoMembroException("Senha não informada!");
        }

        if(especMembro.getSenha().length() <= 5){
            throw new FalhaCriacaoMembroException("Senha deve conter no mínimo 6 caracteres!");
        }

        if(especMembro.getSenha().length() > 10){
            throw new FalhaCriacaoMembroException("Senha deve conter no máximo 10 caracteres!");
        }
    }

    public void validaEmail(EspecificacaoMembro especMembro) {
        if(StringUtils.isEmpty(especMembro.getEmail())){
            throw new FalhaCriacaoMembroException("Email não informado!");
        }

        if(!ValidacaoEmailUtils.isValido(especMembro.getEmail())){
            throw new FalhaCriacaoMembroException("Email no formato inválido!");
        }
    }

    public void validaDataNascimento(EspecificacaoMembro especMembro) {
        if(StringUtils.isEmpty(especMembro.getDataNascimento())){
            throw new FalhaCriacaoMembroException("Data de nascimento não informada!");
        }

        if(especMembro.getDataNascimento().isAfter(LocalDate.now())){
            throw new FalhaCriacaoMembroException("Data de nascimento deve ser menor que hoje!");
        }
    }

    private void validaSeExisteMembroComEmailInformado(EspecificacaoMembro especMembro) {
        if (existeEmailCadastrado(especMembro.getEmail())){
            throw new FalhaCriacaoMembroException("Já existe um membro com o email informado!");
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

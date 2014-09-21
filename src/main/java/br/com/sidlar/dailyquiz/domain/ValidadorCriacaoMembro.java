package br.com.sidlar.dailyquiz.domain;

import br.com.sidlar.dailyquiz.infrastructure.autenticacao.ValidacaoEmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityExistsException;

/**
 * Valida a especificação para a criação de um novo membro
 * @author Admilson
 */
@Component
public class ValidadorCriacaoMembro {

    @Autowired
    private MembroRepository membroRepository;

    public void valida(EspecificacaoMembro especMembro) {
        if (StringUtils.isEmpty(especMembro.getNome())){
            throw new IllegalArgumentException("Nome não informado!");
        }

        if (StringUtils.isEmpty(especMembro.getSenha())){
            throw new IllegalArgumentException("Senha não informada!");
        }

        if (StringUtils.isEmpty(especMembro.getDataNascimento())){
            throw new IllegalArgumentException("Data nascimento não informada!");
        }

        if (StringUtils.isEmpty(especMembro.getEmail())){
            throw new IllegalArgumentException("Email não informado!");
        }

        if(!ValidacaoEmailUtils.isValido(especMembro.getEmail())){
            throw new IllegalArgumentException("Email inválido!");
        }

        if (existeEmailCadastrado(especMembro.getEmail())){
            throw new EntityExistsException("Já existe um membro com o email informado!");
        }
    }

    private boolean existeEmailCadastrado(String email) {
        return membroRepository.buscaMembroPorEmail(email) != null;
    }

}

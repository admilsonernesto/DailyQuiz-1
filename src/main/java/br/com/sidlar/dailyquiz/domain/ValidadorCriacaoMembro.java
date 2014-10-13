package br.com.sidlar.dailyquiz.domain;

import br.com.sidlar.dailyquiz.infrastructure.utilitarios.ValidacaoEmailUtils;
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

        if (verificaSeExisteEmailCadastrado(especMembro.getEmail())){
            throw new RuntimeException("Já existe um membro com o email informado!");
        }
    }

    private boolean verificaSeExisteEmailCadastrado(String email) {
        try {
            membroRepository.buscaMembroPorEmail(email);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

}

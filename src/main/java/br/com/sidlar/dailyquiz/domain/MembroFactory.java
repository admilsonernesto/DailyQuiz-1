package br.com.sidlar.dailyquiz.domain;

import br.com.sidlar.dailyquiz.infrastructure.digest.GeradorDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p><strong>Fabrica um membro com os dados do formulário</strong>
 * <ul>Passos da criação de um Membro:
 * <li>Valida as informações do formulário;</li>
 * <li>Salva o membro utilizando o hash da senha conforme especificação</li>
 * </ul>
 *
 * @author Admilson
 */
@Component
public class MembroFactory {

    @Autowired
    private ValidadorCriacaoMembro validadorCriacaoMembro;

    @Autowired
    private GeradorDigest geradorDigest;

    @Autowired
    private MembroRepository membroRepository;

    public void cria(EspecificacaoMembro especificacaoMembro) {
        validadorCriacaoMembro.valida(especificacaoMembro);
        criaMembro(especificacaoMembro);
    }

    private void criaMembro(EspecificacaoMembro espec) {
        Membro membro = new Membro(
                espec.getNome(),
                espec.getEmail(),
                getHash(espec.getSenha()),
                espec.getDataNascimento()
        );
        membroRepository.salva(membro);
    }

    /**
     * Obtem o hash da senha no formato hexadecimal utilizando o algorítimo MD5.
     *
     * @return hash da senha
     */
    public String getHash(String senha) {
        return geradorDigest.geraHashSenha(senha);
    }

}


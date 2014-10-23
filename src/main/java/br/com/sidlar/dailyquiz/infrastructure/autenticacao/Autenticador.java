package br.com.sidlar.dailyquiz.infrastructure.autenticacao;

import br.com.sidlar.dailyquiz.domain.Membro;
import br.com.sidlar.dailyquiz.domain.MembroNaoEncontradoException;
import br.com.sidlar.dailyquiz.domain.MembroRepository;
import br.com.sidlar.dailyquiz.domain.SenhaInvalidaException;
import br.com.sidlar.dailyquiz.infrastructure.digest.GeradorDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * <p><strong>Serviço de Autenticação de um Membro.</strong>
 * <p> Passos para autenticação:
 * <ul>
 *  <li>Verifica se existe um membro com o email informado;
 *  <li>Valida se o hash da senha informada confere com a senha do membro;
 *  <li>Armazena o membro na sessão {@link javax.servlet.http.HttpSession}.
 * </ul>
 */
@Component
public class Autenticador {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private GeradorDigest geradorDigest;

    @Autowired
    private HttpSession session;

    /**
     *Autentica um membro validando email, senha e armazenando na sessão.
     */
    public void autentica(String email, String senha) throws MembroNaoEncontradoException, SenhaInvalidaException{
        Membro membro = buscaMembroComEmail(email);
        validaSenha(membro, senha);
        armazenaMembroNaSessao(membro);
    }

    private Membro buscaMembroComEmail(String email) {
        return membroRepository.buscaMembroPorEmail(email);
    }


    private void armazenaMembroNaSessao(Membro membro) {
        AutenticacaoUtils.armazenaAutenticacaoMembroNaSession(new AutenticacaoMembro(membro), session);
    }

    /**
     * Obtem o hash da senha no formato hexadecimal utilizando o algorítimo MD5.
     * @param senha senha a ser convertida
     * @return digest da senha
     */
    private String getDigestDaSenha(String senha){
        return geradorDigest.geraHashSenha(senha);
    }

    /**
     * Valida através do hash da senha.
     */
    private void validaSenha(Membro membro, String senha) {
        if(!membro.getSenha().equals(getDigestDaSenha(senha))){
            throw new SenhaInvalidaException("A senha do membro não confere com a senha informada.");
        }
    }

    void setMembroRepository(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    void setGeradorDigest(GeradorDigest geradorDigest) {
        this.geradorDigest = geradorDigest;
    }

    void setSession(HttpSession session) {
        this.session = session;
    }
}

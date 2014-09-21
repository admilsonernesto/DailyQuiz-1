package br.com.sidlar.dailyquiz.infrastructure.autenticacao;

import br.com.sidlar.dailyquiz.domain.Membro;
import br.com.sidlar.dailyquiz.domain.MembroRepository;
import br.com.sidlar.dailyquiz.infrastructure.digest.GeradorDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * <p><strong>Serviço de Autenticação de um Membro.</strong>
 * <p> Passos para autenticação:
 * <ul>
 *  <li>Valida se existe um membro com o email informado;
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
     * <p>Autentica um membro validando email, senha e armazenando na sessão.
     * @return retorna se o usuário foi autenticado
     * @param email email do membro para autenticação
     * @param senha senha do membro para autenticação
     */
    public boolean autentica(String email, String senha) {
        Membro membro = membroRepository.buscaMembroPorEmail(email);

        if(naoFoiEncontradoMembroComEmailInformado(membro)) return false;
        if(senhaNaoConfere(membro, senha))return false;
        armazenaMembroNaSessao(membro);

        return true;
    }

    private boolean naoFoiEncontradoMembroComEmailInformado(Membro membro) {
        return membro == null;
    }


    private void armazenaMembroNaSessao(Membro membro) {
        session.setAttribute("membroAutenticado", new AutenticacaoMembro(membro));
    }

    /**
     * Obtem o hash da senha no formato hexadecimal utilizando o algorítimo MD5.
     * @param senha senha a ser convertida
     * @return digest da senha
     */
    public String getDigestDaSenha(String senha){
        return geradorDigest.geraHashSenha(senha);
    }


    /**
     * A validação da senha é feita através do hash da senha
     * @return retorna a comparação da senha do membro com a senha informada
     * @param membro membro para verificar a senha
     * @param senha senha informada
     */
    private boolean senhaNaoConfere(Membro membro, String senha) {
        return !membro.getSenha().equals(getDigestDaSenha(senha));
    }

}

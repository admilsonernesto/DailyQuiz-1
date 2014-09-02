package br.com.sidlar.dailyquiz.presentation;

import br.com.sidlar.dailyquiz.domain.Membro;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpSession;

/**
 * Caso a verificação do login seja válida então autentica o membro na sessão
 * Na validação da senha utilizamos o hash no formato hexadecimal com o algorítimo MD5
 */
public class AutenticaMembro {
    private Membro membro;
    private String senha;
    private HttpSession session;

    public AutenticaMembro(Membro membro, String senha, HttpSession session) {
        this.membro = membro;
        this.senha = senha;
        this.session = session;
    }

    public boolean executa() {
        if (!validaLogin()){
            return false;
        }

        autenticaMembro();
        return true;
    }

    private boolean validaLogin() {
        return membro!=null && validaSenha(membro);
    }

    private void autenticaMembro() {
        session.setAttribute("membroAutenticado", membro);
    }

    /**
     * Obtem o hash da senha no formato hexadecimal utilizando o algorítimo MD5.
     * @param senha senha a ser criptografada
     * @return hash da senha com MD5
     */
    public String getSenhaCriptografada(String senha){
        return DigestUtils.md5Hex(senha);
    }


    /**
     * A validação da senha é feita através do hash da senha
     * @param membro senha do membro a ser validado
     * @return retorna a comparação da senha do membro com a senha informada
     */
    private boolean validaSenha(Membro membro) {
        return membro.getSenha().equalsIgnoreCase(getSenhaCriptografada(senha));
    }

}

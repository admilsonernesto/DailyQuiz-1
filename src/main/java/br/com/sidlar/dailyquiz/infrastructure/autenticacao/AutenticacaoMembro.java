package br.com.sidlar.dailyquiz.infrastructure.autenticacao;

import br.com.sidlar.dailyquiz.domain.Membro;
import org.joda.time.DateTime;

/**
 * Armazena dados relacionados com a autenticação do membro.
 */
public class AutenticacaoMembro {
    private Membro membro;
    private DateTime instanteUltimoLogin;

    public AutenticacaoMembro(Membro membro) {
        this.membro = membro;
        this.instanteUltimoLogin = DateTime.now();
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public DateTime getInstanteUltimoLogin() {
        return instanteUltimoLogin;
    }

    public void setInstanteUltimoLogin(DateTime instanteUltimoLogin) {
        this.instanteUltimoLogin = instanteUltimoLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutenticacaoMembro that = (AutenticacaoMembro) o;

        if (!membro.equals(that.membro)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return membro.hashCode();
    }
}

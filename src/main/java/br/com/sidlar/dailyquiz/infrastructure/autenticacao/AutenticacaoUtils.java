package br.com.sidlar.dailyquiz.infrastructure.autenticacao;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpSession;

/**
 * Utilitário de autenticação do membro.
 * @author Admilson
 */
public class AutenticacaoUtils {
    /**
     * Obtem autenticação do membro armazenado na sessão.
     * @return a instância de {@code AutenticacaoMembro} que está na HTTP session
     * @throws java.lang.IllegalStateException quando não há membro autenticado na HTTP session.
     */
    public static AutenticacaoMembro obtemAutenticacaoMembroDaSession(@Nonnull HttpSession session) {
        return (AutenticacaoMembro) session.getAttribute("membroAutenticado");
    }

    /**
     * @return se um membro está autenticado ou não na sessão.
     */
    public static boolean membroEstaAutenticadoNaSession(@Nonnull HttpSession session) {
        return session.getAttribute("membroAutenticado") instanceof AutenticacaoMembro;
    }

    /**
     * Armazena a autenticação do membro na sessão.
     */
    public static void armazenaAutenticacaoMembroNaSession(AutenticacaoMembro AutenticacaoMembro, @Nonnull HttpSession session) {
        session.setAttribute("membroAutenticado", AutenticacaoMembro);
    }
}

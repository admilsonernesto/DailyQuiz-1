package br.com.sidlar.dailyquiz.infrastructure.digest;

import com.sun.istack.internal.NotNull;

import javax.annotation.Nonnull;

/**
 * Strategy para geração de hash da senha.
 *
 * <p>As funções de hash são implementadas nas classes Concrete Strategy.
 *
 * @author Admilson
 */
public interface GeradorDigest {

    /**
     * Gera o hash da senha em hexadecimal.
     *
     * @return string hexadecimal
     */
    String geraHashSenha(@Nonnull String senha);

}

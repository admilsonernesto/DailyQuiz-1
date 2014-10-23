package br.com.sidlar.dailyquiz.infrastructure.digest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * Implementação que utiliza a função de hash 'MD5'.
 * @author Admilson
 */
@Component
public class GeradorDigestMd5 implements GeradorDigest {

    @Override
    public String geraHashSenha(@Nonnull String senha) {
        return DigestUtils.md5Hex(senha);
    }
}

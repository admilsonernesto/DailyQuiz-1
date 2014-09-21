package br.com.sidlar.dailyquiz.infrastructure.digest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * Implementação que utiliza a função de hash 'MD5'.
 * @author Admilson
 */
@Component
public class GeradorDigestImpl implements GeradorDigest {
    @Override
    public String geraHashSenha(String senha) {
        return DigestUtils.md5Hex(senha);
    }
}

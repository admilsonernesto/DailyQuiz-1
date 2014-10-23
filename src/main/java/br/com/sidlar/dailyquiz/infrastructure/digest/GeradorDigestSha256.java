package br.com.sidlar.dailyquiz.infrastructure.digest;

import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Nonnull;

/**
 * @author Admilson
 */
public class GeradorDigestSha256 implements GeradorDigest {

    @Override
    public String geraHashSenha(@Nonnull String senha){
        return DigestUtils.sha256Hex(senha);
    }
}

package br.com.sidlar.dailyquiz.infrastructure.digest;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TesteGeradorDigest {

    @Test
    public void geraHashSenhaMd5(){
        //Fixture
        GeradorDigest geradorDigest = new GeradorDigestMd5();

        //SUT
        String hashSenha = geradorDigest.geraHashSenha("123456");

        //Verify
        assertThat(hashSenha, is(DigestUtils.md5Hex("123456")));
    }

    @Test
    public void geraHashSenhaSha256(){
        //Fixture
        GeradorDigest geradorDigest = new GeradorDigestSha256();

        //SUT
        String hashSenha = geradorDigest.geraHashSenha("123456");

        //Verify
        assertThat(hashSenha, is(DigestUtils.sha256Hex("123456")));
    }

    @Test(expected = NullPointerException.class)//Verify
    public void geraHashSenhaComSenhaNula_deveLancarException(){
        //Fixture
        GeradorDigest geradorDigest = new GeradorDigestMd5();

        //SUT
        geradorDigest.geraHashSenha(null);
        fail("Gerar Digest de uma senha nula deveria falhar.");
    }
}
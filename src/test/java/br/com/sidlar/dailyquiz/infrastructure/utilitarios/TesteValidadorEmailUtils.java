package br.com.sidlar.dailyquiz.infrastructure.utilitarios;


import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class TesteValidadorEmailUtils {

    @Test
    public void testeFormatoEmailCorreto(){
        assertThat(ValidadorEmailUtils.isFormatoCorreto("tio..bill@microsoft.com"), is(false));
        assertThat(ValidadorEmailUtils.isFormatoCorreto("tio.bill@microsoft..com"), is(false));
        assertThat(ValidadorEmailUtils.isFormatoCorreto("tio_bill@localserver"), is(true));
        assertThat(ValidadorEmailUtils.isFormatoCorreto("admilson@sidlar.com.br"), is(true));
        assertThat(ValidadorEmailUtils.isFormatoCorreto("admilson@globomail.com"), is(true));
    }
}
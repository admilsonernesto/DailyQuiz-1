package br.com.sidlar.dailyquiz.domain

import org.joda.time.DateTimeUtils
import org.joda.time.LocalDate
import org.joda.time.Years
import org.joda.time.format.DateTimeFormat
import org.junit.Test

import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat

/**
 * @author Admilson
 */
class Groovy_TesteIdadeMembro {

    @Test
    void "membro faz aniversário hoje"(){
        // setup fixture
        defineHojeEm("01/10/2014");
        def membro = new Membro(dataNascimento: newLocaldate("01/10/2000"))

        // exercise SUT
        Years idade = membro.getIdade();

        // verify outcome
        assertThat(idade.getYears(), is(14));
    }

    @Test
    void "membro ainda não fez aniversário no ano"(){
        // setup fixture
        defineHojeEm("01/10/2014");
        def membro = new Membro(dataNascimento: newLocaldate("02/10/2000"))

        // exercise SUT
        Years idade = membro.getIdade();

        // verify outcome
        assertThat(idade.getYears(), is(13));
    }

    @Test
    void "membro já fez aniversário no ano"(){
        // setup fixture
        defineHojeEm("01/10/2014");
        def membro = new Membro(dataNascimento: newLocaldate("30/09/2000"))

        // exercise SUT
        Years idade = membro.getIdade();

        // verify outcome
        assertThat(idade.getYears(), is(14));
    }

    private static LocalDate newLocaldate(String diaMesAno) {
        return LocalDate.parse(diaMesAno, DateTimeFormat.forPattern("dd/MM/yyyy"));
    }

    private static void defineHojeEm(String diaMesAno) {
        DateTimeUtils.setCurrentMillisFixed(newLocaldate(diaMesAno).toDateTimeAtStartOfDay().getMillis());
    }
}

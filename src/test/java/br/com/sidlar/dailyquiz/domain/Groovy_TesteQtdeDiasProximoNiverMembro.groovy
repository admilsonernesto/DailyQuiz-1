package br.com.sidlar.dailyquiz.domain

import br.com.sidlar.dailyquiz.domain.membro.Membro
import org.joda.time.DateTimeUtils
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.junit.Test
import static org.junit.Assert.*;

import static org.hamcrest.Matchers.is

/**
 * @author Admilson
 */
class Groovy_TesteQtdeDiasProximoNiverMembro {
    @Test
    void "membro já fez aniversário no ano"() {

        // setup fixture
        defineHojeEm "31/12/2000"
        def membro = new Membro(dataNascimento: newLocaldate("05/01/1990"))

        // exercise SUT
        def diasRestantes = membro.getQuantidadeDiasParaProximoAniversario()

        // verify outcome
        assertThat diasRestantes.getDays(), is(5)
    }

    @Test
    void "membro ainda não fez aniversário no ano"() {

        // setup fixture
        defineHojeEm "01/12/2000"
        def membro = new Membro(dataNascimento: newLocaldate("10/12/1990"))

        // exercise SUT
        def diasRestantes = membro.getQuantidadeDiasParaProximoAniversario()

        // verify outcome
        assertThat diasRestantes.getDays(), is(9)
    }

    @Test
    void "membro faz aniversário hoje"() {

        // setup fixture
        defineHojeEm "01/12/2000"
        def membro = new Membro(dataNascimento: newLocaldate("01/12/1990"))

        // exercise SUT
        def diasRestantes = membro.getQuantidadeDiasParaProximoAniversario();

        // verify outcome
        assertThat diasRestantes.getDays(), is(365)
    }

    private static LocalDate newLocaldate(String diaMesAno) {
        return LocalDate.parse(diaMesAno, DateTimeFormat.forPattern("dd/MM/yyyy"))
    }

    private static defineHojeEm(String diaMesAno) {
        DateTimeUtils.setCurrentMillisFixed(newLocaldate(diaMesAno).toDateTimeAtStartOfDay().getMillis())
    }
}

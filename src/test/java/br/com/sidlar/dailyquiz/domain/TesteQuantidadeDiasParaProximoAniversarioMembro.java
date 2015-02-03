package br.com.sidlar.dailyquiz.domain;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import org.joda.time.DateTimeUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TesteQuantidadeDiasParaProximoAniversarioMembro {

    private Membro membro;

    @Before
    public void setUp() {
        membro = new Membro();
    }

    @Test
    public void membroJaFezAniversarioNoAno(){
        // setup fixture
        defineHojeEm("31/12/2000");
        membro.setDataNascimento(newLocaldate("05/01/1990"));

        // exercise SUT
        Days diasRestantes = membro.getQuantidadeDiasParaProximoAniversario();

        // verify outcome
        assertThat(diasRestantes.getDays(), is(5));
    }

    @Test
    public void membroAindaNaoFezAniversarioNoAno() {

        // setup fixture
        defineHojeEm("01/12/2000");
        membro.setDataNascimento(newLocaldate("10/12/1990"));

        // exercise SUT
        Days diasRestantes = membro.getQuantidadeDiasParaProximoAniversario();

        // verify outcome
        assertThat(diasRestantes.getDays(), is(9));
    }

    @Test
    public void membroFazAniversarioHoje() {

        // setup fixture
        defineHojeEm("01/12/2000");
        membro.setDataNascimento(newLocaldate("01/12/1990"));

        // exercise SUT
        Days diasRestantes = membro.getQuantidadeDiasParaProximoAniversario();

        // verify outcome
        assertThat(diasRestantes.getDays(), is(365));
    }

    private static LocalDate newLocaldate(String diaMesAno) {
        return LocalDate.parse(diaMesAno, DateTimeFormat.forPattern("dd/MM/yyyy"));
    }

    private static void defineHojeEm(String diaMesAno) {
        DateTimeUtils.setCurrentMillisFixed(newLocaldate(diaMesAno).toDateTimeAtStartOfDay().getMillis());
    }

}

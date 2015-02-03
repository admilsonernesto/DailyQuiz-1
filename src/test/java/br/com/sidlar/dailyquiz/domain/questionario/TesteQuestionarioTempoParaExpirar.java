package br.com.sidlar.dailyquiz.domain.questionario;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TesteQuestionarioTempoParaExpirar {

    @Test
    public void testGetTempoParaExpirar() throws Exception {
        Questionario questionario = new Questionario();
        questionario.setDataDisponibilidadeFinal(new DateTime(2014,11,30,0,0));
        defineHojeEm("19/11/2014");

        String tempoParaExpirar = questionario.getTempoParaExpirar();
        assertThat(tempoParaExpirar, is("11 dias"));
    }

    private static LocalDate newLocaldate(String diaMesAno) {
        return LocalDate.parse(diaMesAno, DateTimeFormat.forPattern("dd/MM/yyyy"));
    }

    private static void defineHojeEm(String diaMesAno) {
        DateTimeUtils.setCurrentMillisFixed(newLocaldate(diaMesAno).toDateTimeAtStartOfDay().getMillis());
    }
}
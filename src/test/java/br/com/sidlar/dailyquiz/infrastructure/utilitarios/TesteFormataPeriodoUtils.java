package br.com.sidlar.dailyquiz.infrastructure.utilitarios;

import org.joda.time.DurationFieldType;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TesteFormataPeriodoUtils {

    @Test
    public void testeFormatacaoPeriodo() {
        assertThat(FormataPeriodoUtils.formata(Period.days(10).withHours(4).withMinutes(8)), is("10 dias"));
        assertThat(FormataPeriodoUtils.formata(Period.days(0).withHours(4).withMinutes(8)), is("4 horas e 8 minutos"));
        assertThat(FormataPeriodoUtils.formata(Period.days(0).withHours(0).withMinutes(8)), is("8 minutos"));
        assertThat(FormataPeriodoUtils.formata(Period.days(0).withHours(0).withMinutes(0)), is("alguns segundos"));
    }
}
package br.com.sidlar.dailyquiz.infrastructure.utilitarios;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.util.StringUtils;

/**
 * Formata os campos de um {@link org.joda.time.Period} utilizando uma configuração específica para nosso projeto.
 * @author Admilson
 */
public class FormataPeriodoUtils {

    /**
     * Formata os campos de um período da seguinte forma:
     * <ul>
     *     <li>Período com 10 dias, 4 horas e 20 minutos : "10 dias"</li>
     *     <li>Período com  0 dias, 4 horas e  8 minutos : "4 horas e 8 minutos"</li>
     *     <li>Período com  0 dias, 0 horas e  8 minutos : "8 minutos"</li>
     *     <li>Período com  0 dias, 0 horas e  0 minutos : "alguns segundos"</li>
     * </ul>
     * @return um período formatado
     */
    public static String formata(Period period) {
        PeriodFormatter formatador;
        if (period.getDays() > 0) {
            formatador = new PeriodFormatterBuilder()
                        .appendDays().appendSuffix(" dia", " dias")
                        .toFormatter();
        }else{
            formatador = new PeriodFormatterBuilder()
                        .appendHours().appendSuffix(" hora", " horas")
                        .appendSeparator(" e ")
                        .appendMinutes().appendSuffix(" minuto", " minutos")
                        .toFormatter();
        }

        String periodoFormatado = formatador.print(period);
        if (StringUtils.isEmpty(periodoFormatado) || (period.equals(Period.days(0).withHours(0).withMinutes(0)))){
            return "alguns segundos";
        }
        return periodoFormatado;
    }

}

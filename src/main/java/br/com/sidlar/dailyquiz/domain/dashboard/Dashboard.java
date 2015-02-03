package br.com.sidlar.dailyquiz.domain.dashboard;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import br.com.sidlar.dailyquiz.domain.ranking.ClassificacaoRanking;

import java.util.List;

/**
 * @author Admilson
 */
public class Dashboard {

    private Membro membro;
    private List<QuestionarioDisponivel> questionariosDisponiveis;
    private List<QuestionarioRespondido> questionariosRespondidos;
    private List<ClassificacaoRanking> classificacoesRanking;

    public Dashboard() {
    }

    public Dashboard(Membro membro, List<QuestionarioDisponivel> questionariosDisponiveis, List<QuestionarioRespondido> questionariosRespondidos, List<ClassificacaoRanking> classificacoesRanking) {
        this.membro = membro;
        this.questionariosDisponiveis = questionariosDisponiveis;
        this.questionariosRespondidos = questionariosRespondidos;
        this.classificacoesRanking = classificacoesRanking;
    }

    public Membro getMembro() {
        return membro;
    }

    public List<QuestionarioDisponivel> getQuestionariosDisponiveis() {
        return questionariosDisponiveis;
    }

    public List<QuestionarioRespondido> getQuestionariosRespondidos() {
        return questionariosRespondidos;
    }

    public List<ClassificacaoRanking> getClassificacoesRanking() {
        return classificacoesRanking;
    }


    public String getMensagemParabenizacao() {
        String msgParabenizacao = "";
        if (membro.fazAniversarioHoje()) {
            msgParabenizacao = String.format("%s, parabéns pelos seus %s anos de vida.", membro.getNome(), membro.getIdade());
        }
        else if(membro.getQuantidadeDiasParaProximoAniversario().getDays() > 0 && membro.getQuantidadeDiasParaProximoAniversario().getDays() <=5){
            msgParabenizacao = String.format("%s, falta(m) apenas %s dia(s) para o seu aniversário.",membro.getNome(),membro.getQuantidadeDiasParaProximoAniversario().getDays());
        }
        return msgParabenizacao;
    }
}


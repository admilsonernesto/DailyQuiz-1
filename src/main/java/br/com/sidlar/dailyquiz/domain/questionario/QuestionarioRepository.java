package br.com.sidlar.dailyquiz.domain.questionario;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Admilson
 */
@Repository
public class QuestionarioRepository {
    @PersistenceContext
    private EntityManager em;

    public Questionario buscaPorId(Long idQuestionario) throws RuntimeException {
        TypedQuery<Questionario> query = em.createQuery(
                "SELECT q " +
                        "FROM   Questionario q " +
                        "INNER JOIN FETCH q.questoes t " +
                        "INNER JOIN FETCH t.alternativaCorreta " +
                        "WHERE  q.id =:idQuestionario "
                , Questionario.class
        ).setParameter("idQuestionario", idQuestionario);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException("Questionário não encontrado.(id:" + idQuestionario + ")");
        }
    }

    public List<Questionario> buscaDisponiveis(Membro membro) {
        TypedQuery<Questionario> query = em.createQuery(
                "SELECT q " +
                        "FROM   Questionario q " +
                        "WHERE  NOT EXISTS  (  " +
                        "                   SELECT  r " +
                        "                   FROM    RespostaQuestionario r " +
                        "                   WHERE   r.questionario.id= q.id and r.membro =:membro " +
                        "                   ) "  +
                        " AND ( q.dataDisponibilidadeFinal is null OR  q.dataDisponibilidadeFinal >= :hoje) "
                , Questionario.class
        ).setParameter("membro", membro)
         .setParameter("hoje", DateTime.now());

        return query.getResultList();
    }
}

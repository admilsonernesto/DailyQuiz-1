package br.com.sidlar.dailyquiz.domain.questionario;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Admilson
 */
@Repository
public class QuestaoRepository {

    @PersistenceContext
    EntityManager em;

    public Questao buscaPorId(Long idQuestao) throws RuntimeException {
        TypedQuery<Questao> query = em.createQuery(
                "SELECT q " +
                        "FROM   Questao q " +
                        "WHERE  q.id =:idQuestao "
                , Questao.class
        ).setParameter("idQuestao", idQuestao);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException("Questão não encontrado.(id:" + idQuestao + ")");
        }
    }
}

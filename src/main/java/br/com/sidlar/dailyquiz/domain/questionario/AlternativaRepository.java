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
public class AlternativaRepository {

    @PersistenceContext
    EntityManager em;

    public Alternativa buscaPorId(Long idAlternativa) throws RuntimeException {
        TypedQuery<Alternativa> query = em.createQuery(
                "SELECT q " +
                        "FROM   Alternativa q " +
                        "WHERE  q.id =:idAlternativa "
                , Alternativa.class
        ).setParameter("idAlternativa", idAlternativa);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException("Alternativa não encontrada.(id:" + idAlternativa + ")");
        }
    }
}

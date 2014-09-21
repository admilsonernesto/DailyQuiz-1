package br.com.sidlar.dailyquiz.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by ADMILSON on 01/09/14.
 */
@Repository
public class MembroRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Caso não encontre nenhum membro com o email informado retornará nulo.
     * @param email email do membro
     * @return membro encontrado
     */
    public Membro buscaMembroPorEmail(String email) {
        TypedQuery<Membro> query = em.createQuery(
                "SELECT m " +
                "FROM Membro m " +
                "WHERE m.email= :email "
                , Membro.class
        ).setParameter("email", email);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void salva(Membro membro) {
        em.persist(membro);
    }
}

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
     * Caso não encontre nenhum membro com o username informado retornará nulo.
     * @param username username do membro
     * @return membro encontrado
     */
    public Membro buscaMembroPorUsername(String username) {
        TypedQuery<Membro> query = em.createQuery(
                "SELECT m " +
                "FROM Membro m " +
                "WHERE m.username= :username "
                , Membro.class
        ).setParameter("username", username);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

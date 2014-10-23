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
     * Caso não encontre nenhum membro com o email informado lançará a exceção do tipo {@link java.lang.RuntimeException}.
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
            throw new MembroNaoEncontradoException("Membro não encontrado com o email " + email + ".");
        }
    }

    public void salva(Membro membro) {
        em.persist(membro);
    }
}

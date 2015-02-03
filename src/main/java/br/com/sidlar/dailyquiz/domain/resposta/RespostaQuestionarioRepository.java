package br.com.sidlar.dailyquiz.domain.resposta;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
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
public class RespostaQuestionarioRepository {
    @PersistenceContext
    private EntityManager em;

    public List<RespostaQuestionario> buscaUltimasCincoRespostas(Membro membro) {
        TypedQuery<RespostaQuestionario> query = em.createQuery(
                "SELECT q " +
                "FROM   RespostaQuestionario q " +
                "WHERE  q.membro =:membro " +
                "ORDER BY  q.data desc "
                , RespostaQuestionario.class
        ).setParameter("membro", membro)
         .setMaxResults(5);

        return query.getResultList();
    }

    public void salva(RespostaQuestionario respostaQuestionario) {
        em.persist(respostaQuestionario);
    }

    public RespostaQuestionario buscaPorId(Long idRespostaQuestionario) throws RuntimeException {
        TypedQuery<RespostaQuestionario> query = em.createQuery(
                "SELECT q " +
                "FROM   RespostaQuestionario q " +
                "WHERE  q.id =:idRespostaQuestionario "
                , RespostaQuestionario.class
        ).setParameter("idRespostaQuestionario", idRespostaQuestionario);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException("Resposta do questionário não encontrada.(id:" + idRespostaQuestionario + ")");
        }
    }

    public List<RespostaQuestionario> buscaRespostasDoQuestionario(Long idQuestionario) {
        TypedQuery<RespostaQuestionario> query = em.createQuery(
                "SELECT q " +
                "FROM   RespostaQuestionario q " +
                "WHERE  q.questionario.id =:idQuestionario "
                , RespostaQuestionario.class
        ).setParameter("idQuestionario", idQuestionario);

        return query.getResultList();
    }

    public List<RespostaQuestionario> buscaTodasRespostas() {
        TypedQuery<RespostaQuestionario> query = em.createQuery(
                "SELECT q " +
                        "FROM   RespostaQuestionario q "
                , RespostaQuestionario.class
        );

        return query.getResultList();
    }
}

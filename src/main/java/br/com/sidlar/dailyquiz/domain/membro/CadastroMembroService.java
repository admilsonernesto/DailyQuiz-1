package br.com.sidlar.dailyquiz.domain.membro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Admilson
 */
@Component
public class CadastroMembroService {

    @Autowired
    private MembroFactory membroFactory;

    @Autowired
    private MembroRepository membroRepository;

    public void criaMembro(EspecificacaoMembro especificacaoMembro) {
        Membro membro = membroFactory.cria(especificacaoMembro);
        membroRepository.salva(membro);
    }


}

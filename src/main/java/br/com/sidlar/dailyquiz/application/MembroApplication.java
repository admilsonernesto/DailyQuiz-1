package br.com.sidlar.dailyquiz.application;

import br.com.sidlar.dailyquiz.domain.EspecificacaoMembro;
import br.com.sidlar.dailyquiz.domain.Membro;
import br.com.sidlar.dailyquiz.domain.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Admilson
 */
@Component
public class MembroApplication {

    @Autowired
    private MembroService membroService;

    @Transactional(readOnly = false)
    public void salva(EspecificacaoMembro especificacaoMembro) {
        membroService.salva(especificacaoMembro);
    }
}

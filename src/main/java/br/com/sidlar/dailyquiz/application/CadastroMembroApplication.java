package br.com.sidlar.dailyquiz.application;

import br.com.sidlar.dailyquiz.domain.membro.EspecificacaoMembro;
import br.com.sidlar.dailyquiz.domain.membro.CadastroMembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Admilson
 */
@Component
public class CadastroMembroApplication {

    @Autowired
    private CadastroMembroService cadastroMembroService;

    @Transactional(readOnly = false)
    public void criaMembroDaEspecificacao(EspecificacaoMembro especificacaoMembro) {
        cadastroMembroService.criaMembro(especificacaoMembro);
    }
}

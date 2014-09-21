package br.com.sidlar.dailyquiz.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;

/**
 * @author Admilson
 */
@Component
public class MembroService {

    @Autowired
    private MembroFactory membroFactory;

    public void salva(EspecificacaoMembro especificacaoMembro) {
        membroFactory.cria(especificacaoMembro);

    }


}

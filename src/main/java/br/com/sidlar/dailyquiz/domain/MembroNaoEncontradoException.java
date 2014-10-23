package br.com.sidlar.dailyquiz.domain;

/**
 * @author Admilson
 */
public class MembroNaoEncontradoException extends RuntimeException {

    public MembroNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public MembroNaoEncontradoException(Throwable cause) {
        super(cause);
    }

    public MembroNaoEncontradoException(String message) {
        super(message);
    }
}

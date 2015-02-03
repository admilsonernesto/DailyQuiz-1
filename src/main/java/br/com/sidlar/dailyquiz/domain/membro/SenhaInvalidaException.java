package br.com.sidlar.dailyquiz.domain.membro;

/**
 * @author Admilson
 */
public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SenhaInvalidaException(Throwable cause) {
        super(cause);
    }

    public SenhaInvalidaException(String message) {
        super(message);
    }
}

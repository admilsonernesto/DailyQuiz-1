package br.com.sidlar.dailyquiz.domain.membro;

/**
 * @author Admilson
 */
public class FalhaCriacaoMembroException extends RuntimeException {

    public FalhaCriacaoMembroException(String message, Throwable cause) {
        super(message, cause);
    }

    public FalhaCriacaoMembroException(Throwable cause) {
        super(cause);
    }

    public FalhaCriacaoMembroException(String message) {
        super(message);
    }
}

package br.com.sidlar.dailyquiz.domain;

import br.com.sidlar.dailyquiz.infrastructure.utilitarios.ValidacaoEmailUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

/**
 * Especificação para a criação de novos membros
 * @author Admilson
 */
public class EspecificacaoMembro {
    private final String nome;
    private final String email;
    private final String senha;
    private final LocalDate dataNascimento;

    public EspecificacaoMembro(String nome, String email, String senha, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

}

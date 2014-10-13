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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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

    public void validaSenha(){
        if(StringUtils.isEmpty(getSenha())){
            throw new IllegalArgumentException("Senha não informada!");
        }

        if(getSenha().length() <= 5){
            throw new IllegalArgumentException("Senha deve conter no mínimo 6 caracteres!");
        }

        if(getSenha().length() > 10){
            throw new IllegalArgumentException("Senha deve conter no máximo 10 caracteres!");
        }
    }

    public void validaEmail() {
        if(StringUtils.isEmpty(getEmail())){
            throw new IllegalArgumentException("Email não informado!");
        }

        if(!ValidacaoEmailUtils.isValido(getEmail())){
            throw new IllegalArgumentException("Email no formato inválido!");
        }
    }

    public void validaNome() {
        if(StringUtils.isEmpty(getNome())){
            throw new IllegalArgumentException("Nome não informado!");
        }
    }

    public void validaDataNascimento() {
        if(StringUtils.isEmpty(getDataNascimento())){
            throw new IllegalArgumentException("Data de nascimento não informada!");
        }

        if(getDataNascimento().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data de nascimento deve ser menor que hoje!");
        }
    }
}

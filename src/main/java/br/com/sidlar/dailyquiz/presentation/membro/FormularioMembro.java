package br.com.sidlar.dailyquiz.presentation.membro;

import br.com.sidlar.dailyquiz.domain.EspecificacaoMembro;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Admilson
 */
class FormularioMembro {
    private String nome;
    private String email;
    private String senha;
    private String confirmacaoSenha;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EspecificacaoMembro toEspecificacaoMembro() {
        return new EspecificacaoMembro(getNome(), getEmail(), getSenha(), getDataNascimento());
    }

}

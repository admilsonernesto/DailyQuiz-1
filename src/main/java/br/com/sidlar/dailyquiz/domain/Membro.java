package br.com.sidlar.dailyquiz.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.Years;

import javax.persistence.*;
import java.time.MonthDay;


/**
 * Created by ADMILSON on 01/09/14.
 */
@Table(name="membro")
@Entity
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dataNascimento;

    public Membro() {
    }

    public Membro(String nome, String email, String senha, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade(){
        return Years.yearsBetween(dataNascimento, LocalDate.now()).getYears();
    }

    /**
     * Verifica se o mes e ano do nascimento é igual ao mes ano de hoje
     * @return se é o aniversário do membro
     */
    public boolean isAniversariante() {
        LocalDate hoje = LocalDate.now();
        MonthDay diaMesAniversario = MonthDay.of(getDataNascimento().getMonthOfYear(), getDataNascimento().getDayOfMonth());
        MonthDay diaMesAtual = MonthDay.of(hoje.getMonthOfYear(), hoje.getDayOfMonth());
        return diaMesAniversario.equals(diaMesAtual);
    }

    /**
     * Calcula a diferença em dias entre a data de nascimento até hoje
     * @return Quantidade de dias para o próximo aniversário
     */
    public int getQuantidadeDiasParaProximoAniversario(){
        Period periodo = Period.fieldDifference(getDataNascimento(), LocalDate.now());
        return periodo.getDays();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Membro membro = (Membro) o;

        if (email != null ? !email.equalsIgnoreCase(membro.email) : membro.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Membro{" +
                "email='" + email + '\'' +
                '}';
    }

}

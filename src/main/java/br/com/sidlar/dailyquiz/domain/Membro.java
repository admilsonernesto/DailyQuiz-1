package br.com.sidlar.dailyquiz.domain;

import org.hibernate.annotations.Type;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.MonthDay;
import org.joda.time.Years;

import javax.persistence.*;



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

    Membro(String nome, String email, String senha, LocalDate dataNascimento) {
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

    public Years getIdade(){
        return Years.yearsBetween(dataNascimento, LocalDate.now());
    }

    /**
     * @return se é o aniversário do membro
     */
    public boolean fazAniversarioHoje() {
        MonthDay aniversario = new MonthDay(getDataNascimento().getMonthOfYear(), getDataNascimento().getDayOfMonth());
        MonthDay hoje = MonthDay.now();
        return aniversario.equals(hoje);
    }

    /**
     * Calcula a diferença em dias de hoje até o próximo aniversário do membro
     * @return Quantidade de dias para o próximo aniversário
     */
    public Days getQuantidadeDiasParaProximoAniversario(){
        LocalDate hoje = LocalDate.now();
        LocalDate proximoAniversario = getDataNascimento().plusYears(getIdade().getYears() + 1);
        return Days.daysBetween(hoje, proximoAniversario);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Membro membro = (Membro) o;

        if (!email.equalsIgnoreCase(membro.email)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "Membro{" +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\''+
                '}';
    }
}

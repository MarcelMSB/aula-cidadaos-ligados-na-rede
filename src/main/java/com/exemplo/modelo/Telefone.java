package com.exemplo.modelo;

import com.exemplo.util.LocalDateDeserializer;
import com.exemplo.util.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema = "public", name = "telefones")
public class Telefone {

    @Id
    @SequenceGenerator(name = "telefones_id_seq", sequenceName = "telefones_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telefones_id_seq")
    @Column(name = "id")
    private int id;
    @NotNull(message = "Erro burro! Informa o DDD")
    @Max(value = 999, message = "DDD invalido")
    @Column(name = "ddd")
    private Integer ddd;
    @Column(name = "numero")
    @Size(min = 9, max = 10, message = "Teste de checagem")
    private String numero;
    @Column(name = "nome")
    private String nome;
    @JoinColumn(name = "i_operadora")
    @ManyToOne(fetch = FetchType.EAGER)
    private Operadora operadora;
    @Transient
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataCadastro = LocalDate.now();

    public Operadora getOperadora() {
        return operadora;
    }

    public void setOperadora(Operadora operadora) {
        this.operadora = operadora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}

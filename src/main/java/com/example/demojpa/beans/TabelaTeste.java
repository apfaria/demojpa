package com.example.demojpa.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabela_teste")
public class TabelaTeste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tabela_teste_id_seq")
    @SequenceGenerator(name = "tabela_teste_id_seq", sequenceName = "tabela_teste_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nome", nullable = false, length = 1)
    private String nome;

    public TabelaTeste() {
        super();
    }

    public TabelaTeste(int id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

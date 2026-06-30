package br.com.poupyg.fintechNovo.model;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "T_RECEITA")

public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_receita")
    @SequenceGenerator(
            name = "seq_receita",
            sequenceName = "seq_receita",
            allocationSize = 1
    )
    @Column(name = "ID_RECEITA")
    private Long id;
    @Column(name = "DS_RECEITA")
    private String descricao;
    @Column(name = "VL_RECEITA")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name= "id_usuario", nullable = false)
    private Usuario usuario;


    public Receita() {
    }


    public Receita(Long id, String descricao, BigDecimal valor, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
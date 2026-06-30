package br.com.poupyg.fintechNovo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "T_INVESTIMENTO")

public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INVESTIMENTO")
    @SequenceGenerator(
            name = "SEQ_INVESTIMENTO",
            sequenceName = "SEQ_INVESTIMENTO",
            allocationSize = 1
    )
    @Column(name = "ID_INVESTIMENTO")
    private Long id;
    @Column(name = "DS_INVESTIMENTO")
    private String descricao;
    @Column(name = "VL_INVESTIMENTO")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name= "id_usuario", nullable = false)
    private Usuario usuario;



    public Investimento() {
    }



    public Investimento(Long id, String descricao, BigDecimal valor, Usuario usuario) {
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



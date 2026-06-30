package br.com.poupyg.fintechNovo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "T_DESPESA")

public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_despesa")
    @SequenceGenerator(
            name = "seq_despesa",
            sequenceName = "seq_despesa",
            allocationSize = 1
    )
    @Column(name = "ID_DESPESA")
    private Long id;
    @Column(name = "DS_DESPESA")
    private String descricao;
    @Column(name = "VL_DESPESA")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name= "id_usuario", nullable = false)
    private Usuario usuario;

    public Despesa() {

    }

    public Despesa(Long id, String descricao, BigDecimal valor, Usuario usuario) {
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




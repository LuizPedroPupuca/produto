package br.com.zup.edu.produto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String codigo;

    @Column(nullable = false)
    private BigDecimal preco;

    public Produto(String nome, String codigo, BigDecimal preco) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
    }

    @Deprecated
    public Produto() {
    }

    public Long getId() {
        return id;
    }
}

package br.com.zup.edu.produto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 1, max = 6)
    private String codigo;

    @NotNull
    private BigDecimal preco;

    public ProdutoRequest(String nome, String codigo, BigDecimal preco) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
    }

    public ProdutoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Produto toModel() {
        return new Produto(nome, codigo, preco);
    }
}

package com.iff.edu.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Produto implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    @NotBlank(message = "O nome é obrigatório!")
    private String nome;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Funcionario obrigatório.")
    private TipoProdutoEnum tipo;
    @Column(nullable = false)
    private double preco;
    
    private int quantidadeEstoque;
    @JsonIgnore 
    @OneToMany(mappedBy = "produto")
    private List<Item> itens = new ArrayList<>(); 
    
    public Long getId() {
            return id;
    }
    public void setId(Long id) {
            this.id = id;
    }
    public String getNome() {
            return nome;
    }
    public void setNome(String nome) {
            this.nome = nome;
    }
    public TipoProdutoEnum getTipo() {
            return tipo;
    }
    public void setTipo(TipoProdutoEnum tipo) {
            this.tipo = tipo;
    }
    public double getPreco() {
            return preco;
    }
    public void setPreco(double preco) {
            this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
      
}

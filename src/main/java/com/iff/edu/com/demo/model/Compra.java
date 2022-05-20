package com.iff.edu.com.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @FutureOrPresent(message = "Data da compra precisa ser presente ou futura!.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar dataDaCompra;
    @Column(nullable = false)
    private double total;
    @Column(nullable = false)
    private int desconto;
    @JsonIgnore 
    @OneToMany(mappedBy = "compra")
    private List<Item> itens = new ArrayList<>(); 
    @ManyToOne
    @JoinColumn(nullable = true)
    @NotNull(message = "Cliente obrigatório.")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(nullable = true)
    @NotNull(message = "Funcionario obrigatório.")
    private Funcionario funcionario;
    @Embedded
    @NotNull(message = "A pretação é obrigatória!")
    private Prestacao prestacao;
    

    
    public Long getId() {
	return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDataDaCompra() {
		return dataDaCompra;
	}
	public void setDataDaCompra(Calendar dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getDesconto() {
		return desconto;
	}
	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}
	public List<Item> getItem() {
		return itens;
	}
	public void setItem(List<Item> items) {
		this.itens = items;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Prestacao getPrestacao() {
		return prestacao;
	}
	public void setPrestacao(Prestacao prestacao) {
		this.prestacao = prestacao;
	}

     
        

    
    
}

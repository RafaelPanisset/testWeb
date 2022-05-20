package com.iff.edu.com.demo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Prestacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    private int juroAoMes;
    @Column(nullable = false)
    @Size(min = 1, message = "O dia de vencimento é obrigatório.")
    private int diaDeVencimento;
    @Column(nullable = false)
    private double valorAPagar;
    @Column(nullable = false)
    @Min(value = 1, message = "A quantidade é obrigatória.")
    private int quantidade;
 
 
    public int getJuroAoMes() {
            return juroAoMes;
    }
    public void setJuroAoMes(int juroAoMes) {
            this.juroAoMes = juroAoMes;
    }
    public int getDiaDeVencimento() {
            return diaDeVencimento;
    }
    public void setDiaDeVencimento(int diaDeVencimento) {
            this.diaDeVencimento = diaDeVencimento;
    }
    public double getValorAPagar() {
            return valorAPagar;
    }
    public void setValorAPagar(double valorAPagar) {
            this.valorAPagar = valorAPagar;
    }
    public int getQuantidade() {
            return quantidade;
    }
    public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
    }
   
}

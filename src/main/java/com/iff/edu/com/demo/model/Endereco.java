package com.iff.edu.com.demo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Embeddable

public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column()
    @Digits(integer = 10, fraction = 0, message = "Número deve ser inteiro e ter até 10 digitos.")
    private int numero;
    @Column(length = 50, nullable = false)
    @Length(max = 150, message = "Valor digitado muito grande!")
    private String rua;
    @Column(length = 50, nullable = false)
    @Length(max = 150, message = "Valor digitado muito grande!")
    private String bairro;
    @Column(length = 50, nullable = false)
    @Length(max = 150, message = "Valor digitado muito grande!")
    @NotBlank(message = "A cidade é obrigatória!")
    private String cidade;
    @Column(length = 9)
    @Length(max = 150, message = "Valor digitado muito grande!")
    @NotBlank(message = "O CEP é obrigatório!")
    private String cep;
    @Column(length = 50)
    @Length(max = 100, message = "Valor digitado muito grande!")
    private String complemento;
   
    public int getNumero() {
            return numero;
    }
    public void setNumero(int numero) {
            this.numero = numero;
    }
    public String getBairro() {
            return bairro;
    }
    public void setBairro(String bairro) {
            this.bairro = bairro;
    }
    public String getRua() {
            return rua;
    }
    public void setRua(String rua) {
            this.rua = rua;
    }
    public String getCidade() {
            return cidade;
    }
    public void setCidade(String cidade) {
            this.cidade = cidade;
    }
    public String getCep() {
            return cep;
    }
    public void setCep(String cep) {
            this.cep = cep;
    }
    public String getComplemento() {
            return complemento;
    }
    public void setComplemento(String complemento) {
            this.complemento = complemento;
    }
    @Override
    public int hashCode() {
            return Objects.hash(bairro, cep, cidade, complemento, numero);
    }
    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            Endereco other = (Endereco) obj;
            return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
                            && Objects.equals(cidade, other.cidade) && Objects.equals(complemento, other.complemento)
                            && Objects.equals(numero, other.numero);
    }
}

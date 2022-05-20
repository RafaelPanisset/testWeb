package com.iff.edu.com.demo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(length = 50)
    @NotBlank(message = "O número é obrigatório.")
    private String numero;

    public String getNumero() {
            return numero;
    }

    public void setNumero(String numero) {
            this.numero = numero;
    }

    @Override
    public int hashCode() {
            return Objects.hash(numero);
    }

    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            Telefone other = (Telefone) obj;
            return Objects.equals(numero, other.numero);
    }
	
}

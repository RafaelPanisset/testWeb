package com.iff.edu.com.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, length = 150)
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @Column(nullable = false, length = 150, unique = true)
    @NotBlank(message = "O email é obrigatório")
    private String email;
    @Column(nullable = false, length = 14, unique = true)
    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido.")
    private String cpf;
    @Embedded
    private Endereco endereco;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Telefone> telefones = new ArrayList<>();
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
    public String getEmail() {
            return email;
    }
    public void setEmail(String email) {
            this.email = email;
    }
    public String getCpf() {
            return cpf;
    }
    public void setCpf(String cpf) {
            this.cpf = cpf;
    }
    public Endereco getEndereco() {
            return endereco;
    }
    public void setEndereco(Endereco endereco) {
            this.endereco = endereco;
    }
    public List<Telefone> getTelefones() {
            return telefones;
    }
    public void setTelefones(List<Telefone> telefones) {
            this.telefones = telefones;
    }
    
    
    @Override
    public int hashCode() {
            return Objects.hash(id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
                return true;
        if (obj == null)
                return false;
        if (getClass() != obj.getClass())
                return false;
        Pessoa other = (Pessoa) obj;
        return id == other.id;
    }
}

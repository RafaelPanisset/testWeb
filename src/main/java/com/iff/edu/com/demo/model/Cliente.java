package com.iff.edu.com.demo.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
//
@Entity
public class Cliente extends Pessoa {
    @Column(length = 200)
    private String documentos;
    @JsonIgnore 
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras = new ArrayList<>();

    public String getDocumento() {
            return documentos;
    }
    public void setDocumento(String documentos) {
            this.documentos = documentos;
    }
    public List<Compra> getCompras() {
            return compras;
    }
    public void setCompras(List<Compra> compras) {
            this.compras = compras;
    }

  
}

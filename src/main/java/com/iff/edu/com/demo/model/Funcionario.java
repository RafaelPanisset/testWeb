package com.iff.edu.com.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;


@Entity
public class Funcionario extends Pessoa {
    @Column(nullable = false, length = 50)
    @Length(max = 150, message = "Valor digitado muito grande!")
    @NotBlank(message = "O login é obrigatório brigatória!")
    private String login;
    @Column(nullable = false)
    @NotBlank(message = "A senha ´é obrigatória!")
    @Length(max = 150, message = "Valor digitado muito grande!")
    private String senha;
    @JsonIgnore 
    @OneToMany(mappedBy = "funcionario")
    private List<Compra> compras = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @OrderColumn
    @Size(min = 1, message = "O funcionário precisa ter pelo menos uma permissão!")
    private List<Permissao> permissoes  = new ArrayList<>();
 
    public String getLogin() {
            return login;
    }
    public void setLogin(String login) {
            this.login = login;
    }
    public String getSenha() {
            return senha;
    }
    public void setSenha(String senha) {
            this.senha = senha;
    }
    public List<Compra> getCompras() {
            return compras;
    }
    public void setCompras(List<Compra> compras) {
            this.compras = compras;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
}

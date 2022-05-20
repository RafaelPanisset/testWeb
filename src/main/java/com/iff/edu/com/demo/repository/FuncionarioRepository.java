package com.iff.edu.com.demo.repository;

import com.iff.edu.com.demo.model.Funcionario;
import com.iff.edu.com.demo.model.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    @Query("SELECT pessoa FROM Pessoa pessoa WHERE pessoa.cpf = :cpf OR pessoa.email = :email")
    public List<Pessoa> findByCpfOuEmail(@Param("cpf") String cpf, @Param("email") String email);
    public Funcionario findByEmail(String email);
}
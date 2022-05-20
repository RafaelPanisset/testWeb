
package com.iff.edu.com.demo.repository;

import com.iff.edu.com.demo.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}


package com.iff.edu.com.demo.repository;

import com.iff.edu.com.demo.model.Produto;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    @Query("SELECT produto FROM Produto produto WHERE produto.id = :id")
    public List<Produto> buscarProduto(@Param("id") int id);

}
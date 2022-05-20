package com.iff.edu.com.demo.repository;

import com.iff.edu.com.demo.model.Compra;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprasRepository extends JpaRepository<Compra, Long>{
  @Query("SELECT compra FROM Compra compra WHERE compra.id = :id")
    public List<Compra> buscarCompra(@Param("id") int id);

}

package com.iff.edu.com.demo.repository;

import com.iff.edu.com.demo.model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    @Query("SELECT item FROM Item item WHERE item.id = :id")
    public List<Item> buscarItem(@Param("id") int id);
}

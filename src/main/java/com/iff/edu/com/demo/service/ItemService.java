
package com.iff.edu.com.demo.service;

import com.iff.edu.com.demo.exception.NotFoundException;
import com.iff.edu.com.demo.model.Item;
import com.iff.edu.com.demo.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository repo;

    public List<Item> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Item> findAll() {
        return repo.findAll();
    }

    public Item findById(Long id) {
        Optional<Item> result = repo.findById(id);
        if (result.isEmpty()) {
          
        }
        return result.get();
    }

      public Item save(Item p) {
        try {
            return repo.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o item.");
        }
    }

    public Item update(Item p) {
        Item obj = findById(p.getId());
        try {
            p.setCompra(obj.getCompra());
            p.setProduto(obj.getProduto());
            p.setQuantidadeComprada(obj.getQuantidadeComprada());
           
            return repo.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar o item.");
        }
    }

    public void delete(Long id){
        Item obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new NotFoundException("Falha ao deletar o item.");
        }
    }   
}

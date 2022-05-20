
package com.iff.edu.com.demo.service;

import com.iff.edu.com.demo.exception.NotFoundException;
import com.iff.edu.com.demo.model.Cliente;
import com.iff.edu.com.demo.model.Produto;
import com.iff.edu.com.demo.model.TipoProdutoEnum;
import com.iff.edu.com.demo.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    public List<Produto> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Produto> findAll() {
        return repo.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> result = repo.findById(id);
        if (result.isEmpty()) {
          
        }
        return result.get();
    }

      public Produto save(Produto p) {
         System.out.println(p);
        try {
            return repo.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o produto.");
        }
    }

    public Produto update(Produto p) {
        Produto obj = findById(p.getId());
        try {
            p.setId(obj.getId());
            p.setNome(obj.getNome());
            p.setPreco(obj.getPreco());
            p.setQuantidadeEstoque(obj.getQuantidadeEstoque());
            p.setTipo(obj.getTipo());
           
           return p;
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar o produto.");
        }
    }

    public void delete(Long id){
        Produto obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new NotFoundException("Falha ao deletar o produto.");
        }
    }   
}

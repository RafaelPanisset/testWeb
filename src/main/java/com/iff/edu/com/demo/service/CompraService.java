package com.iff.edu.com.demo.service;


import com.iff.edu.com.demo.exception.NotFoundException;
import com.iff.edu.com.demo.model.Compra;
import com.iff.edu.com.demo.repository.ComprasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    @Autowired
    private ComprasRepository repo;

    public List<Compra> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Compra> findAll() {
        return repo.findAll();
    }

    public Compra findById(Long id) {
        Optional<Compra> result = repo.findById(id);
        if (result.isEmpty()) {
        }
        return result.get();
    }

    public Compra save(Compra s) {
        try {
            return repo.save(s);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o compra.");
        }
    }
    public void delete(Long id){
        Compra obj = findById(id);
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new NotFoundException("Falha ao deletar a compra.");
        }
    }

   
    public Compra update(Compra c) {
        Compra obj = findById(c.getId());
        try {
            c.setCliente(obj.getCliente());
            c.setDataDaCompra(obj.getDataDaCompra());
            c.setDesconto(obj.getDesconto());
            c.setFuncionario(obj.getFuncionario());
            c.setItem(obj.getItem());
            c.setPrestacao(obj.getPrestacao());
            c.setTotal(obj.getTotal());
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar a compra.");
        }
    }
}
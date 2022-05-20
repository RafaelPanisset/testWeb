package com.iff.edu.com.demo.service;

///
import com.iff.edu.com.demo.model.Cliente;
import com.iff.edu.com.demo.model.Pessoa;
import com.iff.edu.com.demo.repository.ClienteRepository;
import com.iff.edu.com.demo.exception.NotFoundException;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> result = repo.findById(id);
        if (result.isEmpty()) {
          
        }
        return result.get();
    }

    public Cliente save(Cliente c) {
     
       /* if (file != null) {
            if (!file.isEmpty()) {
                salvarArquivo(file, c.getCpf() + ".pdf");
                c.setDocumento(c.getCpf() + ".pdf");
            } else {
                c.setDocumento(null);
            }
        }*/
        verificaCpfEmailCadastrado(c.getCpf(), c.getEmail());
        try {
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Problema ao salvar o cliente");
        }
    }

    public Cliente update(Cliente c) {
        Cliente obj = findById(c.getId());
        try {
            c.setCpf(obj.getCpf());
            c.setEmail(obj.getEmail());
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Problema ao salvar o cliente");
        }
    }

    public void delete(Long id) {
        Cliente obj = findById(id);
        verificaExclusaoClienteComCompras(obj);
        try {
            repo.delete(obj);
            /*if (obj.getDocumento() != null) {
                Path caminho = Paths.get("uploads", obj.getDocumento());
                Files.deleteIfExists(caminho);
            }*/
        } catch (Exception e) {
            throw new NotFoundException("Problema ao deletar!");
        }
    }

    private void salvarArquivo(MultipartFile file, String novoNome) {
        if (file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)) {
            Path dest = Paths.get("uploads", novoNome);
            try {
                file.transferTo(dest);
            } catch (IOException ex) {
                throw new RuntimeException("Problema ao salvar arquivo");
            }
        } else {
            throw new RuntimeException("Arquivo deve ser do tipo PDF.");
        }
    }

    private void verificaCpfEmailCadastrado(String cpf, String email) {
        List<Pessoa> result = repo.findByCpfOuEmail(cpf, email);
        if (!result.isEmpty()) {
            throw new NotFoundException("CPF ou EMAIL já cadastrados.");
        }
    }

    private void verificaExclusaoClienteComCompras(Cliente c) {
        if (!c.getCompras().isEmpty()) {
            throw new NotFoundException("Cliente possui compras. Não pode ser excluído.");
        }
    }

}
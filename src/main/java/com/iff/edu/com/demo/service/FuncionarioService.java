package com.iff.edu.com.demo.service;

import com.iff.edu.com.demo.model.Funcionario;
import com.iff.edu.com.demo.model.Pessoa;
import com.iff.edu.com.demo.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.iff.edu.com.demo.exception.NotFoundException;
import com.iff.edu.com.demo.model.Permissao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repo;

    public List<Funcionario> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Funcionario> findAll() {
        return repo.findAll();
    }
    
    public Funcionario findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Funcionario findById(Long id) {
        Optional<Funcionario> result = repo.findById(id);
        if (result.isEmpty()) {
        }
        return result.get();
    }

    public Funcionario save(Funcionario f) {

        verificaCpfEmailCadastrado(f.getCpf(), f.getEmail());
        removePermissoesNulas(f);
        try {
            f.setSenha(new BCryptPasswordEncoder().encode(f.getSenha()));
            return repo.save(f);
        } catch (Exception e) {
            throw new NotFoundException("Problema ao salvar ao salvar o Funcionario.");
        }
    }

    public Funcionario update(Funcionario f, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        
        Funcionario obj = findById(f.getId());
      
        removePermissoesNulas(f);
       
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        try {
            f.setCpf(obj.getCpf());
            f.setEmail(obj.getEmail());
            f.setSenha(obj.getSenha());
            return repo.save(f);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar o Funcionario.");
        }
    }


    public void delete(Long id) {
        Funcionario obj = findById(id);
        verificaExclusaoClienteComCompra(obj);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new NotFoundException("Problema ao excluir o Funcionario");
        }

    }

    private void verificaCpfEmailCadastrado(String cpf, String email) {
        List<Pessoa> result = repo.findByCpfOuEmail(cpf, email);
        if (!result.isEmpty()) {
            throw new NotFoundException("CPF ou EMAIL já cadastrados.");
        }
    }

    private void alterarSenha(Funcionario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        if (!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()) {
            if (!crypt.matches(senhaAtual, obj.getSenha())) {
                throw new RuntimeException("Senha atual está incorreta.");
            }
            if (!novaSenha.equals(confirmarNovaSenha)) {
                throw new NotFoundException("Nova Senha e Confirmar Nova Senha não conferem.");
            }
            obj.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        }
    }

    private void verificaExclusaoClienteComCompra(Funcionario f) {
        if (!f.getCompras().isEmpty()) {
            throw new NotFoundException("Funcionario possui compras. Não pode ser excluído.");
        }
    }
    
    public void removePermissoesNulas(Funcionario f){
        f.getPermissoes().removeIf( (Permissao p) -> {
            return p.getId() == null;
        });
        if(f.getPermissoes().isEmpty()){
            throw new RuntimeException("O funcionário deve conter no mínimo 1 permissão.");
        }
    }
}
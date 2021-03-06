
package com.iff.edu.com.demo.controller.view;


import com.iff.edu.com.demo.model.Cliente;
import com.iff.edu.com.demo.model.Funcionario;
import com.iff.edu.com.demo.repository.PermissaoRepository;
import com.iff.edu.com.demo.service.ClienteService;
import com.iff.edu.com.demo.service.FuncionarioService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioViewController {

    @Autowired
    private FuncionarioService service;
    @Autowired
    private PermissaoRepository permissaoRepo;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("funcionarios", service.findAll());
        return "funcionarios";
    }
    
    @GetMapping(path = "/funcionario")
    public String cadastro(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("permissoes", permissaoRepo.findAll());
        return "formFuncionario";
    }
    
    
    @PostMapping(path = "/funcionario")
    public String save(@Valid @ModelAttribute Funcionario funcionario,
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model) {  
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFuncionario";
        }

        if (!funcionario.getSenha().equals(confirmarSenha)) {
            model.addAttribute("msgErros", new ObjectError("funcionario", "Campos Senha e Confirmar Senha devem ser iguais."));
            return "formFuncionario";
        }

        funcionario.setId(null);
        try {
            service.save(funcionario);
            model.addAttribute("msgSucesso", "Funcion??rio cadastrado com sucesso.");
            model.addAttribute("funcionario", new Funcionario());
            return "formFuncionario";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }
    @GetMapping(path = "/funcionario/{id}")
    public String atualizacao(@PathVariable("id") Long id, Model model) {
        model.addAttribute("funcionario", service.findById(id));
        model.addAttribute("permissoes", permissaoRepo.findAll());
        return "formFuncionario";
    }
    
       @PostMapping(path = "/funcionario/{id}")
    public String update(@Valid @ModelAttribute Funcionario funcionario,
            BindingResult result,
            @PathVariable("id") Long id,
            Model model) {
        
        model.addAttribute("permissoes", permissaoRepo.findAll());
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha")){
                list.add(fe);
            }
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formFuncionario";
        }

        funcionario.setId(id);
        try {
            service.update(funcionario, "", "", "");
            model.addAttribute("msgSucesso", "Funcion??rio atualizado com sucesso.");
            model.addAttribute("funcionario", funcionario);
            return "formFuncionario";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }

    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/funcionarios";
    }    
    
    
       //os dados
    @GetMapping(path = "/meusdados")
    public String getMeusDados(@AuthenticationPrincipal User user, Model model){
        Funcionario funcionario = service.findByEmail(user.getUsername());
        model.addAttribute("funcionario", funcionario);
        return "formMeusDados";
    }
    
    @PostMapping(path = "/meusdados")
    public String updateMeusDados(
            @Valid @ModelAttribute Funcionario funcionario,
            BindingResult result,
            @AuthenticationPrincipal User user,
            @RequestParam("senhaAtual") String senhaAtual,
            @RequestParam("novaSenha") String novaSenha,
            @RequestParam("confirmarNovaSenha") String confirmarNovaSenha,
            Model model) {
        
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha") && !fe.getField().equals("permissoes") ){
                list.add(fe);
            }
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formMeusDados";
        }

        Funcionario funcionarioBD = service.findByEmail(user.getUsername());
        if(!funcionarioBD.getId().equals(funcionario.getId())){
            throw new RuntimeException("Acesso negado.");
        }
        try {
            funcionario.setPermissoes(funcionarioBD.getPermissoes());
            service.update(funcionario, senhaAtual, novaSenha, confirmarNovaSenha);
            model.addAttribute("msgSucesso", "Funcion??rio atualizado com sucesso.");
            model.addAttribute("funcionario", funcionario);
            return "formMeusDados";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formMeusDados";
        }
    }
}
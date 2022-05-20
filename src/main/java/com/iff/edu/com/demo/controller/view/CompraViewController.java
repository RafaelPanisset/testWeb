
package com.iff.edu.com.demo.controller.view;

import com.iff.edu.com.demo.model.Compra;
import com.iff.edu.com.demo.model.Item;
import com.iff.edu.com.demo.service.ClienteService;
import com.iff.edu.com.demo.service.CompraService;
import com.iff.edu.com.demo.service.FuncionarioService;
import com.iff.edu.com.demo.service.ProdutoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;




@Controller
@RequestMapping(path = "/compras")
public class CompraViewController {

    @Autowired
    private CompraService service;
    @Autowired
    private ProdutoService serviceProduto;
    @Autowired
    private ClienteService serviceCliente;
    @Autowired
    private FuncionarioService serviceFuncionario;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("compras", service.findAll());
        return "compras";
    }
    @GetMapping(path = "/compra")
    public String cadastro(Model model) {
        model.addAttribute("compra", new Compra());
        model.addAttribute("produtos", serviceProduto.findAll());
        model.addAttribute("item", new Item());
        model.addAttribute("clientes", serviceCliente.findAll());
        model.addAttribute("funcionarios", serviceFuncionario.findAll());
        return "formCompras";
    }
    
    @PostMapping(path = "/compra")
    public String salvar(@Valid @ModelAttribute Compra compra, BindingResult result, Model model) {
            return "formCompras";
   
    }
}
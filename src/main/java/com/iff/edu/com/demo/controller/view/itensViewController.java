
package com.iff.edu.com.demo.controller.view;
  
import com.iff.edu.com.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/compras/itens/{id}")
public class itensViewController {

    @Autowired
    private ItemService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("itens", service.findAll());
        return "itens";
    }
}

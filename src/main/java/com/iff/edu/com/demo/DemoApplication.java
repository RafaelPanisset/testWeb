package com.iff.edu.com.demo;

import com.iff.edu.com.demo.model.Cliente;
import com.iff.edu.com.demo.model.Compra;
import com.iff.edu.com.demo.model.TipoProdutoEnum;
import com.iff.edu.com.demo.model.Endereco;
import com.iff.edu.com.demo.model.Funcionario;
import com.iff.edu.com.demo.model.Item;
import com.iff.edu.com.demo.model.Permissao;
import com.iff.edu.com.demo.model.Telefone;
import com.iff.edu.com.demo.model.Prestacao;
import com.iff.edu.com.demo.model.Produto;
import com.iff.edu.com.demo.repository.ClienteRepository;
import com.iff.edu.com.demo.repository.ComprasRepository;
import com.iff.edu.com.demo.repository.FuncionarioRepository;
import com.iff.edu.com.demo.repository.ItemRepository;
import com.iff.edu.com.demo.repository.PermissaoRepository;
import com.iff.edu.com.demo.repository.ProdutoRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
    
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private FuncionarioRepository funcionarioRepo;
    @Autowired
    private ComprasRepository compraRepo;
    @Autowired
    private ProdutoRepository produtoRepo;
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private PermissaoRepository permissaoRepo;
   
    public static void main(String[] args) {
            SpringApplication.run(DemoApplication.class, args);
    }
    
    public void run(String... args) throws Exception {
        
        //Permissao
        Permissao pp1 = new Permissao();
        pp1.setNome("ADMIN");
        Permissao pp2 = new Permissao();
        pp2.setNome("FUNC");
        permissaoRepo.saveAll(List.of(pp1, pp2));
      
        System.out.println("here");
        //Cliente
        Cliente c1 = new Cliente();
        c1.setNome("João Carlos da Silva");
        c1.setCpf("532.353.230-07");
        c1.setEmail("carlosjoao@gmail.com");
        
        Telefone t1 = new Telefone();
        t1.setNumero("(85) 3968-1540");
        Telefone t2 = new Telefone();
        t2.setNumero("(85) 99671-6448");
        
        c1.setTelefones(List.of(t1,t2));
        
        Endereco end = new Endereco();
        end.setRua("Rua Professora Marlene Ribeiro");
        end.setNumero(753);
        end.setBairro("Centro");
        end.setCidade("Beberibe");
        end.setCep("62840-973");
        
        c1.setEndereco(end);
        clienteRepo.save(c1);
        System.out.println("here");

        //Funcionario
        Funcionario f1 = new Funcionario();
        f1.setNome("Ana da Silva");
        f1.setPermissoes(List.of(pp1));
        f1.setEmail("silva@gmail.com");
        f1.setCpf("256.201.752-86");
        
        f1.setEndereco(end);
        Telefone t3 = new Telefone();
        t1.setNumero("(79) 2658-1408");
        Telefone t4 = new Telefone();
        t2.setNumero("(79) 98860-6346");
        
        f1.setTelefones(List.of(t3,t4));
        
        f1.setLogin("anaSilva");
        f1.setSenha(new BCryptPasswordEncoder().encode("12345"));
        funcionarioRepo.save(f1);
        
        //Produto
        Produto p1 = new Produto();
        p1.setNome("Gelatina");
        p1.setTipo(TipoProdutoEnum.LATICIONIOS);
        p1.setPreco(2.99);
        p1.setQuantidadeEstoque(200);
        
        Produto p2 = new Produto();
        p2.setNome("Galinha congelada");
        p2.setTipo(TipoProdutoEnum.CARNE);
        p2.setPreco(3.99);
        p2.setQuantidadeEstoque(500);
        produtoRepo.save(p1);
        produtoRepo.save(p2);
        
       
        //Prestacao
        Prestacao pt1 = new Prestacao();
        pt1.setDiaDeVencimento(18);
        pt1.setJuroAoMes(0);
        pt1.setQuantidade(1);
        pt1.setValorAPagar(202.99);
        // Compra
        Compra cp1 = new Compra();
        cp1.setCliente(c1);
        cp1.setFuncionario(f1);
        cp1.setPrestacao(pt1);
        Calendar dataCompra = Calendar.getInstance();
        dataCompra.set(2022, 10, 10);
        cp1.setDataDaCompra(dataCompra);  
        cp1.setTotal(202.99);  
        cp1.setDesconto(0);
        
       
        compraRepo.save(cp1);
       // cp1.setQuartos(List.of(q1, q2));
       
      
        Item itm1 = new Item();
        itm1.setProduto(p1);
        itm1.setQuantidadeComprada(5);
        itm1.setCompra(cp1);
        itemRepo.save(itm1);
       // itemRepo.save(itm1);
     //   itm1.setQuantidadeComprada(cp1);
        Item itm2 = new Item();
        itm2.setProduto(p2);
        itm2.setQuantidadeComprada(8);
        itm2.setCompra(cp1);
        itemRepo.save(itm2);

     
    }

}

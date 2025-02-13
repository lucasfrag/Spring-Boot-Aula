package com.senac.biblioteca.controller;

import com.senac.biblioteca.model.ListaLivro;
import com.senac.biblioteca.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LivroController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("livro", new Livro());
        return "cadastro";
    }
    
    @PostMapping("/cadastro")
    public String processarFormulario(@ModelAttribute Livro livro, Model model) {
        livro.setDisponivel(true);
        ListaLivro.adicionar(livro);
        model.addAttribute("livro", livro);
        
        return "cadastro-sucesso";
    }
    
    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("livros", ListaLivro.listar());
        return "lista";
    }
}

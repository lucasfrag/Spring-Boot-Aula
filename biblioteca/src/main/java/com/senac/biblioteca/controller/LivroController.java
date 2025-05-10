package com.senac.biblioteca.controller;

import com.senac.biblioteca.model.Livro;
import com.senac.biblioteca.service.ComentarioService;
import com.senac.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroService livroService;
   
    @Autowired
    private ComentarioService comentarioService;
    
    @GetMapping("/cadastro")
    public String exibirFormulario(@CookieValue(name = "pref-estilo", defaultValue="claro") String tema, Model model) {
        model.addAttribute("css", tema);
        model.addAttribute("livro", new Livro());
        return "livro-cadastro";
    }
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Livro livro) {
        livroService.salvar(livro);
        return "redirect:/livro/lista";
    }
    
    @GetMapping("/lista")
    public String lista(@CookieValue(name = "pref-estilo", defaultValue="claro") String tema, Model model) {
        model.addAttribute("css", tema);
        model.addAttribute("livros", livroService.listarTodos());
        return "livro-listagem";
    }
    
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable int id, @CookieValue(name = "pref-estilo", defaultValue="claro") String tema, Model model) {
        model.addAttribute("css", tema);
        model.addAttribute("livro", livroService.buscarPorId(id));
        return "livro-cadastro";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) {
        livroService.excluir(id);
        return "redirect:/livro/lista";
    }
    
    @GetMapping("/detalhes/{id}")
    public String exibirDetalhes(@PathVariable int id, @CookieValue(name = "pref-estilo", defaultValue="claro") String tema, Model model) {
        Livro livro = livroService.buscarPorId(id);
        model.addAttribute("css", tema);
        model.addAttribute("livro", livro);
        model.addAttribute("comentarios", comentarioService.listarPorLivro(id));
        return "detalhes";
    }
}

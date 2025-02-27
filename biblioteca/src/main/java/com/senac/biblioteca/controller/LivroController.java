package com.senac.biblioteca.controller;

// Importações necessárias para manipulação dos modelos e serviços
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

// Define esta classe como um controlador Spring MVC
@Controller
// Define a URL base para as requisições deste controlador
@RequestMapping("/livros")
public class LivroController {
    
    // Injeta automaticamente a dependência do serviço de livros
    @Autowired
    private LivroService livroService;
    
    // Injeta automaticamente a dependência do serviço de comentários
    @Autowired
    private ComentarioService comentarioService;
    
    
    // Exibe o formulário para cadastrar um novo livro
    @GetMapping("/cadastro")
    public String exibirFormulario(@CookieValue(name = "pref-estilo", defaultValue="claro") String tema, Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("css", tema);
        return "cadastro";
    }
    
    // Processa o formulário de cadastro de livro e o salva no sistema
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Livro livro, Model model) {
        livroService.salvar(livro);
        model.addAttribute("livro", livro);
        
        return "cadastro-sucesso";
    }
    
    // Lista todos os livros cadastrados e os exibe na página "lista"
    @GetMapping("/lista")
    public String lista(@CookieValue(name = "pref-estilo", defaultValue="claro") String tema,Model model) {
        model.addAttribute("livros", livroService.listarTodos());
        model.addAttribute("css", tema);
        return "lista";
    }
    
    // Exibe os detalhes de um livro específico, incluindo seus comentários
    @GetMapping("/detalhes/{id}")
    public String exibirDetalhes(@CookieValue(name = "pref-estilo", defaultValue="claro") String tema,@PathVariable int id, Model model) {        
        Livro livro = livroService.buscarPorId(id);
        
        // Adiciona o livro e seus comentários ao modelo para exibição na página
        model.addAttribute("livro", livro);
        model.addAttribute("comentarios", comentarioService.listarPorLivro(id));
        model.addAttribute("css", tema);
        return "detalhes";
    }
    
    // Carrega os dados de um livro para alteração
    @GetMapping("/alterar/{id}")
    public String alterarLivro(@CookieValue(name = "pref-estilo", defaultValue="claro") String tema,@PathVariable int id, Model model) {
        model.addAttribute("livro", livroService.buscarPorId(id));
        model.addAttribute("css", tema);
        return "cadastro";
    }
    
    // Exclui um livro do sistema e redireciona para a lista
    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable int id) {
        livroService.excluir(id);
        return "redirect:/livros/lista";
    }
}
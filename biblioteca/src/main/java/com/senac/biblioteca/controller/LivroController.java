package com.senac.biblioteca.controller;

// Importações necessárias para manipulação dos modelos e serviços
import com.senac.biblioteca.model.ListaLivro;
import com.senac.biblioteca.model.Livro;
import com.senac.biblioteca.service.ComentarioService;
import com.senac.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    // Mapeia a rota raiz para retornar a página inicial
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    // Exibe o formulário para cadastrar um novo livro
    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("livro", new Livro());
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
    public String lista(Model model) {
        model.addAttribute("livros", livroService.listarTodos());
        return "lista";
    }
    
    // Exibe os detalhes de um livro específico, incluindo seus comentários
    @GetMapping("/detalhes/{id}")
    public String exibirDetalhes(@PathVariable int id, Model model) {        
        Livro livro = livroService.buscarPorId(id);
        
        // Adiciona o livro e seus comentários ao modelo para exibição na página
        model.addAttribute("livro", livro);
        model.addAttribute("comentarios", comentarioService.listarPorLivro(id));
        
        return "detalhes";
    }
    
    // Carrega os dados de um livro para alteração
    @GetMapping("/alterar/{id}")
    public String alterarLivro(@PathVariable int id, Model model) {
        model.addAttribute("livro", livroService.buscarPorId(id));
        return "cadastro";
    }
    
    // Exclui um livro do sistema e redireciona para a lista
    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable int id) {
        livroService.excluir(id);
        return "redirect:/livros/lista";
    }
}
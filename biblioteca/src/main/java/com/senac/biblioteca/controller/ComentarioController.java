package com.senac.biblioteca.controller;

// Importações necessárias para manipulação de requisições e serviços
import com.senac.biblioteca.model.Comentario;
import com.senac.biblioteca.model.Livro;
import com.senac.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Indica que esta classe é um controlador Spring MVC
@Controller
// Define o caminho base para os endpoints deste controlador
@RequestMapping("/comentarios")
public class ComentarioController {
    
    // Injeta automaticamente a dependência do serviço de livros
    @Autowired
    private LivroService livroService;
    
    // Endpoint para adicionar um comentário a um livro
    @PostMapping("/adicionar/{livroId}")
    public String adicionarComentario(@PathVariable int livroId, @ModelAttribute Comentario novoComentario) {
        // Busca o livro pelo ID fornecido
        Livro livro = livroService.buscarPorId(livroId);
        
        // Se o livro existir, adiciona o comentário e salva as alterações
        if (livro != null) {
            livro.adicionarComentario(novoComentario); // Adiciona o novo comentário ao livro
            livroService.salvar(livro); // Salva as alterações no livro
        }
        // Redireciona para a página de detalhes do livro após adicionar o comentário
        return "redirect:/livros/detalhes/" + livroId;
    }
}

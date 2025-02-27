package com.senac.biblioteca.controller;

// Importações necessárias para manipulação de requisições e serviços
import com.senac.biblioteca.model.Comentario;
import com.senac.biblioteca.service.ComentarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Indica que esta classe é um controlador REST, permitindo manipulação de requisições HTTP
@RestController
// Habilita o CORS para permitir requisições de qualquer origem
@CrossOrigin(origins = "*")
// Define o caminho base para os endpoints deste controlador
@RequestMapping("/api/comentarios")
public class ComentarioAPIController {
    
    // Injeta automaticamente a dependência do serviço de comentários
    @Autowired
    private ComentarioService comentarioService;
    
    // Endpoint para listar comentários de um livro específico
    @GetMapping("/livro/{livroId}")
    public List<Comentario> listarComentarios(@PathVariable int livroId) {
        // Chama o serviço para obter os comentários associados ao livro informado
        return comentarioService.listarPorLivro(livroId);
    }
    
    // Endpoint para criar um novo comentário
    @PostMapping
    public Comentario criarComentario(@RequestBody Comentario comentario) {
        // Chama o serviço para salvar o novo comentário e o retorna
        return comentarioService.salvar(comentario);
    }
    
    // Endpoint para atualizar um comentário existente
    @PutMapping("/{id}")
    public Comentario atualizarComentario(@PathVariable int id, @RequestBody Comentario comentarioAtualizado) {
        // Busca o comentário pelo ID e atualiza seu texto caso seja encontrado
        Comentario comentario = comentarioService.buscarPorId(id);
        
        if (comentario == null) {
            throw new RuntimeException("Comentário não encontrado");
        }
        
        comentario.setTexto(comentarioAtualizado.getTexto());
        
        return comentarioService.salvar(comentario);
    }
    
    // Endpoint para deletar um comentário pelo ID
    @DeleteMapping("/{id}")
    public void deletarComentario(@PathVariable int id) {
        // Chama o serviço para excluir o comentário
        comentarioService.excluir(id);
    }
}
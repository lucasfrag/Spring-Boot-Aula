package com.senac.biblioteca.controller;

// Importações necessárias para manipulação de requisições e serviços
import com.senac.biblioteca.model.Livro;
import com.senac.biblioteca.service.LivroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/livros")
public class LivroAPIController {
    
    // Injeta automaticamente a dependência do serviço de livros
    @Autowired
    private LivroService livroService;
    
    // Endpoint para listar todos os livros
    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarTodos();
    }
    
    // Endpoint para buscar um livro específico pelo ID
    @GetMapping("/{id}")
    public Livro buscarLivro(@PathVariable int id) {
        return livroService.buscarPorId(id);
    }
    
    // Endpoint para criar um novo livro
    @PostMapping
    public Livro criarLivro(@RequestBody Livro livro) {
        return livroService.salvar(livro);
    }
    
    // Endpoint para atualizar um livro existente
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable int id, @RequestBody Livro livroAtualizado) {
        Livro livro = livroService.buscarPorId(id);
        
        // Atualiza os atributos do livro encontrado com os novos valores
        livro.setNome(livroAtualizado.getNome());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setDisponivel(livroAtualizado.isDisponivel());
        
        // Salva o livro atualizado e retorna a resposta
        Livro livroSalvo = livroService.salvar(livro);
        return ResponseEntity.ok(livroSalvo);
    }
    
    // Endpoint para deletar um livro pelo ID
    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable int id) {
        livroService.excluir(id);
    }  
}

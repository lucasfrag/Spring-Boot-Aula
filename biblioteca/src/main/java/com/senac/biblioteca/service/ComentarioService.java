package com.senac.biblioteca.service;

// Importações das classes necessárias
import com.senac.biblioteca.model.Comentario;
import com.senac.biblioteca.repository.ComentarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Define que esta classe é um serviço gerenciado pelo Spring
@Service
public class ComentarioService {
    
    // Injeta automaticamente a dependência do repositório
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    // Método para listar os comentários associados a um livro específico
    public List<Comentario> listarPorLivro(int livroId) {
        return comentarioRepository.findByLivroId(livroId);
    }
    
    // Método para salvar um novo comentário no banco de dados
    public Comentario salvar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }
    
    // Método para excluir um comentário pelo seu ID
    public void excluir(int id) {
        comentarioRepository.deleteById(id);
    }
    
    // Método para buscar um livro pelo ID, retornando null caso não seja encontrado
    public Comentario buscarPorId(int id) {
        return comentarioRepository.findById(id).orElse(null);
    }      
}

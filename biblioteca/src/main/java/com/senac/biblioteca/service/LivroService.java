package com.senac.biblioteca.service;

// Importações das classes necessárias
import com.senac.biblioteca.model.Livro;
import com.senac.biblioteca.repository.LivroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Anotação para indicar que esta classe é um serviço gerenciado pelo Spring
@Service
public class LivroService {
    
    // Injeta automaticamente o repositório de livros
    @Autowired
    private LivroRepository livroRepository;
    
    // Método para listar todos os livros cadastrados no banco
    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }
    
    // Método para buscar um livro pelo ID, retornando null caso não seja encontrado
    public Livro buscarPorId(int id) {
        return livroRepository.findById(id).orElse(null);
    }
    
    // Método para salvar um livro no banco de dados (criação ou atualização)
    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }
    
    // Método para excluir um livro do banco de dados pelo ID
    public void excluir(int id) {
        livroRepository.deleteById(id);
    }
}

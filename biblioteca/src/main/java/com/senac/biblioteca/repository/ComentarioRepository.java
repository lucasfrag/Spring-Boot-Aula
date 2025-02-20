package com.senac.biblioteca.repository;

// Importação da entidade Comentario e outras classes necessárias
import com.senac.biblioteca.model.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Indica que esta interface é um componente do Spring que gerencia operações de banco de dados
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    
    // Método personalizado para buscar todos os comentários associados a um livro específico pelo seu ID
    List<Comentario> findByLivroId(int livroId);
}

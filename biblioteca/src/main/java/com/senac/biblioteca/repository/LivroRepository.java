package com.senac.biblioteca.repository;

// Importação da entidade Livro e classes do Spring Data JPA
import com.senac.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Indica que esta interface é um repositório gerenciado pelo Spring
@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    // JpaRepository já fornece métodos básicos como salvar, excluir e buscar por ID,
    // então não há necessidade de definir métodos personalizados aqui, a menos que sejam necessários no futuro.
}

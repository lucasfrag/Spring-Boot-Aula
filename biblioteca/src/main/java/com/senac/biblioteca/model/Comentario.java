package com.senac.biblioteca.model;

// Importações necessárias para mapeamento da entidade e manipulação de JSON
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

// A anotação @Data da biblioteca Lombok gera automaticamente getters, setters e outros métodos úteis
@Data
// Indica que esta classe é uma entidade JPA
@Entity
// Especifica o nome da tabela no banco de dados
@Table(name="Comentario")
public class Comentario {
    
    // Define o identificador único da entidade
    @Id
    // Especifica que o ID será gerado automaticamente pelo banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // Campo que armazenará o texto do comentário
    private String texto;
    
    // Define um relacionamento muitos-para-um com a entidade Livro
    @ManyToOne
    @JoinColumn(name = "livro_id") // Especifica a chave estrangeira que relaciona um comentário a um livro
    @JsonIgnore // Evita loops infinitos ao serializar objetos JSON que possuem relações bidirecionais
    private Livro livro;
    
    // Construtor padrão necessário para JPA
    public Comentario() {}
    
    // Construtor que permite criar um comentário com texto especificado
    public Comentario(String texto) {
        this.texto = texto;
    }
}

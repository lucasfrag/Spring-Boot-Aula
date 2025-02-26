package com.senac.biblioteca.model;

// Importações necessárias para a persistência da entidade
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

// A anotação @Data do Lombok gera automaticamente os getters, setters, equals, hashCode e toString
@Data
// Indica que esta classe é uma entidade JPA que será mapeada para o banco de dados
@Entity
// Especifica o nome da tabela correspondente no banco de dados
@Table(name="Livro")
// Define a classe como um componente gerenciado pelo Spring
@Component
public class Livro {
    
    // Define o identificador único da entidade
    @Id
    // Especifica que o ID será gerado automaticamente pelo banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // Campos básicos da entidade Livro
    private String nome;
    private String autor;
    private boolean disponivel;
    
    // Relacionamento um-para-muitos: um livro pode ter vários comentários
    @OneToMany(mappedBy="livro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Indica que esta é a parte não gerenciada do relacionamento e evita loops nas requisições
    private List<Comentario> comentarios = new ArrayList<>();
    
    // Construtor padrão necessário para JPA
    public Livro() {}

    // Construtor que inicializa a entidade com valores específicos
    public Livro(int id, String nome, String autor, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.disponivel = disponivel;
    }
    
    // Retorna a lista de comentários do livro
    public List<Comentario> getComentarios() {
        return comentarios;
    }
    
    // Método para adicionar um comentário ao livro, garantindo o vínculo correto
    public void adicionarComentario(Comentario comentario) {
        comentario.setLivro(this);
        this.comentarios.add(comentario);
    }
}

package com.senac.biblioteca.model;

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

@Data
@Entity
@Table(name="Livro")
@Component
public class Livro {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  
    private String titulo;
    private String autor;
    private int ano;
    private String capa;
    
    @OneToMany(mappedBy="livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();
    
    public void adicionarComentario(Comentario comentario) {
        comentario.setLivro(this);
        this.comentarios.add(comentario);
    }
}

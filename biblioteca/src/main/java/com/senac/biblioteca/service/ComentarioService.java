package com.senac.biblioteca.service;

import com.senac.biblioteca.model.Comentario;
import com.senac.biblioteca.repository.ComentarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    public List<Comentario> listarPorLivro(int livroId) {
        return comentarioRepository.findByLivroId(livroId);
    }
    
    public Comentario salvar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }
    
    public void excluir(int id) {
        comentarioRepository.deleteById(id);
    }
    
    public Comentario buscarPorId(int id) {
        return comentarioRepository.findById(id).orElse(null);
    }
}

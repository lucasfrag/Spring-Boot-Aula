package com.senac.biblioteca.service;

import com.senac.biblioteca.model.Livro;
import com.senac.biblioteca.repository.LivroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;
    
    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }
    
    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }
    
    public Livro buscarPorId(int id) {
        return livroRepository.findById(id).orElse(null);
    }
    
    public void excluir(int id) {
        livroRepository.deleteById(id);
    }
}

package com.senac.biblioteca.controller;

import com.senac.biblioteca.model.Livro;
import com.senac.biblioteca.service.LivroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin(origins= "*")
public class LivroAPIController {
    @Autowired
    private LivroService livroService;
    
    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public Livro buscarLivro(@PathVariable int id) {
        return livroService.buscarPorId(id);
    }
    
    @PostMapping
    public Livro criarLivro(@RequestBody Livro livro) {
        return livroService.salvar(livro);
    }
    
    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable int id, @RequestBody Livro livroAtualizado) {
        return livroService.salvar(livroAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable int id) {
        livroService.excluir(id);
    }
}

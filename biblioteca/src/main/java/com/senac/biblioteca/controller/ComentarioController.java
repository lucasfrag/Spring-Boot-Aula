package com.senac.biblioteca.controller;

import com.senac.biblioteca.model.Comentario;
import com.senac.biblioteca.model.Livro;
import com.senac.biblioteca.service.ComentarioService;
import com.senac.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comentario")
public class ComentarioController {
    @Autowired
    private LivroService livroService;
    
    @Autowired
    private ComentarioService comentarioService;
    
    @PostMapping("/gravar/{livroId}")
    public String adicionarComentario(@PathVariable int livroId, @ModelAttribute Comentario novoComentario) {
        Livro livro = livroService.buscarPorId(livroId);
        if (livro != null) {
            livro.adicionarComentario(novoComentario);
            livroService.salvar(livro);
        }
        return "redirect:/livro/detalhes/" + livroId;
    }
    
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) {
        comentarioService.excluir(id);
        return "redirect:/livro/lista";
    }    
}

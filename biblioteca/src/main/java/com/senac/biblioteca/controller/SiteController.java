package com.senac.biblioteca.controller;

import com.senac.biblioteca.model.Preferencia;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteController {
    @GetMapping("/")
    public String inicio(@CookieValue(name = "pref-estilo", defaultValue="claro") String tema, Model model) {
        model.addAttribute("css", tema);
        return "index";
    }    
    
    @RequestMapping("/preferencias")
    public String preferencias(@CookieValue(name = "pref-estilo", defaultValue="claro") String tema, Model model) {
        model.addAttribute("css", tema);
        return "preferencias";
    }
    
    @PostMapping("/preferencias")
    public ModelAndView gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response) {
        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo());
        
        cookiePrefEstilo.setDomain("localhost");
        cookiePrefEstilo.setHttpOnly(true);
        cookiePrefEstilo.setMaxAge(300);
        response.addCookie(cookiePrefEstilo);
        
        return new ModelAndView("redirect:/");
    }
    
}

package com.unincor.projetoChamados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unincor.projetoChamados.model.domain.Chamado;
import com.unincor.projetoChamados.model.domain.Usuario;
import com.unincor.projetoChamados.model.repository.ChamadoRepository;
import com.unincor.projetoChamados.model.repository.UsuarioRepository;
import com.unincor.projetoChamados.model.service.ChamadoService;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/chamados-site")
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/novo")
    public String novo(Model model) {
        Chamado chamado = new Chamado();
        chamado.setUsuario(new Usuario()); 
        model.addAttribute("chamado", chamado);
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("titulo", "Novo Chamado");
        return "chamados-site/form";
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("chamados", chamadoRepository.findAll());
        return "chamados-site/lista";
    }

    

    @PostMapping
    public String salvar(@Valid @ModelAttribute("chamado") Chamado chamado, 
                         BindingResult br, 
                         RedirectAttributes ra, 
                         Model model) {

        if(br.hasErrors()) {
            model.addAttribute("titulo", (chamado.getId() == null) ? "Novo Chamado" : "Editar Chamado");
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "chamados-site/form";
        }

        chamadoService.salvar(chamado);
        ra.addFlashAttribute("ok", "Chamado salvo com sucesso!");
        return "redirect:/dashboard";
    }

    
}

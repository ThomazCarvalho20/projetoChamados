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
import com.unincor.projetoChamados.model.repository.ChamadoRepository;
import com.unincor.projetoChamados.model.service.ChamadoService;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/chamados-site")
public class ChamadoController {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ChamadoService chamadoService;


    @GetMapping("/novo")
    public String novo(Model model) {
    Chamado chamado = new Chamado();
    model.addAttribute("chamado", chamado);
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

    if (br.hasErrors()) {
        model.addAttribute("titulo", (chamado.getId() == null) ? "Novo Chamado" : "Editar Chamado");
        return "chamados-site/form";
    }

    chamadoService.salvar(chamado);
    ra.addFlashAttribute("ok", "Chamado salvo com sucesso!");
    return "redirect:/dashboard";
}


    // Tela de continuação do chamado
@GetMapping("/{id}/continuar")
public String continuar(@PathVariable Long id, Model model) {
    Chamado chamado = chamadoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Chamado inválido: " + id));

    model.addAttribute("chamado", chamado);
    model.addAttribute("titulo", "Continuar Chamado");
    return "chamados-site/continuar"; // arquivo HTML novo
}

// Salvar continuação
@PostMapping("/{id}/continuar")
public String salvarContinuacao(@PathVariable Long id, 
                                @ModelAttribute("chamado") Chamado chamadoAtualizado,
                                RedirectAttributes ra) {

    Chamado chamado = chamadoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Chamado inválido: " + id));

    // Exemplo simples: adiciona ao final da descrição
    String novaDescricao = chamado.getDescricao() + 
        "\n\n[Continuação]: " + chamadoAtualizado.getDescricao();

    chamado.setDescricao(novaDescricao);
    chamadoRepository.save(chamado);

    ra.addFlashAttribute("ok", "Continuação adicionada com sucesso!");
    return "redirect:/chamados-site";
}


    
}

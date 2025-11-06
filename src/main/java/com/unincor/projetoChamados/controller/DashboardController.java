package com.unincor.projetoChamados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.unincor.projetoChamados.model.domain.StatusChamado;
import com.unincor.projetoChamados.model.service.ChamadoService;

@Controller
public class DashboardController {

    private final ChamadoService chamadoService;

    public DashboardController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        var chamados = chamadoService.listarChamados();

        long abertos = chamados.stream().filter(c -> c.getStatus() == StatusChamado.ABERTO).count();
        long andamento = chamados.stream().filter(c -> c.getStatus() == StatusChamado.EM_ANDAMENTO).count();
        long concluidos = chamados.stream().filter(c -> c.getStatus() == StatusChamado.CONCLUIDO).count();
        long cancelados = chamados.stream().filter(c -> c.getStatus() == StatusChamado.CANCELADO).count();

        model.addAttribute("abertos", abertos);
        model.addAttribute("andamento", andamento);
        model.addAttribute("concluidos", concluidos);
        model.addAttribute("cancelados", cancelados);
        model.addAttribute("chamados", chamados);

        return "dashboard";
    }
}
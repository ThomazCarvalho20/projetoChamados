package com.unincor.projetoChamados.model.service;

import com.unincor.projetoChamados.model.domain.Chamado;
import com.unincor.projetoChamados.model.repository.ChamadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public List<Chamado> listarChamados() {
        return chamadoRepository.findAll();
    }

    public Chamado salvar(Chamado chamado) {
        return chamadoRepository.save(chamado);
    }

    public Chamado buscarPorId(Long id) {
        return chamadoRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        chamadoRepository.deleteById(id);
    }
}

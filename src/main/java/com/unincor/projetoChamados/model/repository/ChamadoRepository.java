package com.unincor.projetoChamados.model.repository;

import com.unincor.projetoChamados.model.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
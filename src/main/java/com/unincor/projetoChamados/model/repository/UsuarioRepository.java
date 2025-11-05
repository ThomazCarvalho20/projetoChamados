package com.unincor.projetoChamados.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unincor.projetoChamados.model.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
Optional<Usuario> findByEmail(String email);
}

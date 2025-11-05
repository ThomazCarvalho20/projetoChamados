package com.unincor.projetoChamados.model.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id") 
@Entity
@Table(name = "usuarios")

public  class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100)
    private String nome;

    @Email(message = "O e-mail informado não é válido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Chamado> chamados = new ArrayList<>();
}
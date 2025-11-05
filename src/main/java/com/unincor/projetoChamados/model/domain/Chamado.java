package com.unincor.projetoChamados.model.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "chamados")
public class Chamado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    @Size(min = 5, max = 200)
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    @Lob
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusChamado status = StatusChamado.ABERTO;

    private LocalDateTime dataAbertura = LocalDateTime.now();
    private LocalDateTime dataFechamento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
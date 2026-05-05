package br.edu.utfpr.pb.pw44s.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tb_endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String rua;

    @NotNull
    private String numero;

    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String estado;

    @NotNull
    private String cep;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User usuario;
}

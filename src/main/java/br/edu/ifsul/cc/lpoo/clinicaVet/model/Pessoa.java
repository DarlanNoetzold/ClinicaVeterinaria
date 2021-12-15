package br.edu.ifsul.cc.lpoo.clinicaVet.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tb_jogador")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public class Pessoa {
    @Id
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String numero_celular;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_nascimento;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String complemento;


}

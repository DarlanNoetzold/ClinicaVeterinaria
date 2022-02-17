package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_especie")
public class Especie implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_especie", sequenceName = "seq_especie_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_especie", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    public Especie() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

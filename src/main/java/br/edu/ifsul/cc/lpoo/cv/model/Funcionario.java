package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "tb_funcionario")
@DiscriminatorValue("F")
public class Funcionario extends Pessoa implements Serializable {

    @Column(nullable = false)
    private String numero_ctps;

    @Column(nullable = false)
    private String numero_pis;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_cadastro_Funcionario;

    public Funcionario() {
    }

    public String getNumero_ctps() {
        return numero_ctps;
    }

    public void setNumero_ctps(String numero_ctps) {
        this.numero_ctps = numero_ctps;
    }

    public String getNumero_pis() {
        return numero_pis;
    }

    public void setNumero_pis(String numero_pis) {
        this.numero_pis = numero_pis;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Calendar getData_cadastro_Funcionario() {
        return data_cadastro_Funcionario;
    }

    public void setData_cadastro_Funcionario(Calendar data_cadastro_Funcionario) {
        this.data_cadastro_Funcionario = data_cadastro_Funcionario;
    }

    @Override
    public String toString() {
        return super.getCpf();
    }
}

package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "tb_venda")
public class Venda {
    @Id
    @SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String observacao;

    @Column(nullable = false)
    private Float valor;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Pagamento pagamento;

    @ManyToMany
    @JoinTable(name = "tb_venda_consulta", joinColumns = {@JoinColumn(name = "venda_id")},
            inverseJoinColumns = {@JoinColumn(name = "consulta_id")})
    private List<Consulta> consultas;

    @ManyToMany
    @JoinTable(name = "tb_venda_produto", joinColumns = {@JoinColumn(name = "venda_id")},
            inverseJoinColumns = {@JoinColumn(name = "produto_id")})
    private List<Produto> produtos;

    public Venda() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}

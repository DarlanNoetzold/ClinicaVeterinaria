package br.edu.ifsul.cc.lpoo.cv.gui.consulta.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Cargo;
import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;

public class JPanelAJConsultaFormulario extends JPanel implements ActionListener{
    
    
    private JPanelAConsulta pnlAConsulta;
    private Controle controle;
    
    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;
    
    private JPanel pnlDadosCadastrais;
    private JPanel pnlCentroDadosCadastrais;
    
    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblCpf;
    private JTextField txfCpf;
    private JLabel lblRg;
    private JTextField txfRg;
    private JLabel lblCep;
    private JTextField txfCep;
    private JLabel lblComplemento;
    private JTextField txfComplemento;
    private JLabel lblData_nascimento;
    private JTextField txfData_nascimento;
    private JLabel lblEmail;
    private JTextField txfEmail;
    private JLabel lblEndereco;
    private JTextField txfEndereco;
    private JLabel lblNome;
    private JTextField txfNome;
    private JLabel lblNumero_celular;
    private JTextField txfNumero_celular;
    private JLabel lblCargo;
    private JComboBox txfCargo;
    private JLabel lblNumero_pis;
    private JTextField txfNumero_pis;
    private JLabel lblNumero_ctps;
    private JTextField txfNumero_ctps;
    private JLabel lblSenha;
    private JPasswordField txfSenha;



    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;
    
    private JPanel pnlDadosConsultas;
    private JPanel pnlDadosReceitas;

    
    
    public JPanelAJConsultaFormulario(JPanelAConsulta pnlAConsulta, Controle controle){
        
        this.pnlAConsulta = pnlAConsulta;
        this.controle = controle;
        
        initComponents();
        
    }
    
    private void initComponents(){

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        tbpAbas = new JTabbedPane();
        this.add(tbpAbas, BorderLayout.CENTER);
        pnlDadosConsultas = new JPanel();

        gridBagLayoutDadosCadastrais = new GridBagLayout();
        pnlDadosConsultas.setLayout(gridBagLayoutDadosCadastrais);

        lblCpf = new JLabel("CPF:");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblCpf, posicionador);//o add adiciona o rotulo no painel

        txfCpf = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfCpf, posicionador);//o add adiciona o rotulo no painel

        lblCargo = new JLabel("Cargo:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblCargo, posicionador);//o add adiciona o rotulo no painel

        txfCargo = new JComboBox();
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        txfCargo.addItem(Cargo.ADESTRADOR);
        txfCargo.addItem(Cargo.ATENDENTE);
        txfCargo.addItem(Cargo.AUXILIAR_VETERINARIO);
        pnlDadosConsultas.add(txfCargo, posicionador);//o add adiciona o rotulo no painel

        lblNumero_ctps = new JLabel("CTPS:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblNumero_ctps, posicionador);//o add adiciona o rotulo no painel

        txfNumero_ctps = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfNumero_ctps, posicionador);//o add adiciona o rotulo no painel

        lblNumero_pis = new JLabel("PIS:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblNumero_pis, posicionador);//o add adiciona o rotulo no painel

        txfNumero_pis = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfNumero_pis, posicionador);//o add adiciona o rotulo no painel

        lblSenha = new JLabel("Senha:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblSenha, posicionador);//o add adiciona o rotulo no painel

        txfSenha = new JPasswordField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfSenha, posicionador);//o add adiciona o rotulo no painel

        lblRg = new JLabel("RG:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 2;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblRg, posicionador);//o add adiciona o rotulo no painel

        txfRg = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 3;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfRg, posicionador);//o add adiciona o rotulo no painel

        lblData_nascimento = new JLabel("Data de Nacimento:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 2;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblData_nascimento, posicionador);//o add adiciona o rotulo no painel

        txfData_nascimento = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 3;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfData_nascimento, posicionador);//o add adiciona o rotulo no painel

        lblEmail = new JLabel("Email:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 2;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblEmail, posicionador);//o add adiciona o rotulo no painel

        txfEmail = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 3;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfEmail, posicionador);//o add adiciona o rotulo no painel

        lblNome = new JLabel("Nome:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 2;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblNome, posicionador);//o add adiciona o rotulo no painel

        txfNome = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 3;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfNome, posicionador);//o add adiciona o rotulo no painel

        lblNumero_celular = new JLabel("Numero Celular:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 2;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblNumero_celular, posicionador);//o add adiciona o rotulo no painel

        txfNumero_celular = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 3;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfNumero_celular, posicionador);//o add adiciona o rotulo no painel

        lblCep = new JLabel("Cep:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblCep, posicionador);//o add adiciona o rotulo no painel

        txfCep = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfCep, posicionador);//o add adiciona o rotulo no painel

        lblComplemento = new JLabel("Complemento:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblComplemento, posicionador);//o add adiciona o rotulo no painel

        txfComplemento = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfComplemento, posicionador);//o add adiciona o rotulo no painel

        lblEndereco = new JLabel("Endereco:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblEndereco, posicionador);//o add adiciona o rotulo no painel

        txfEndereco = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosConsultas.add(txfEndereco, posicionador);//o add adiciona o rotulo no painel

        tbpAbas.addTab("Consultas", pnlDadosConsultas);

        pnlDadosCadastrais = new JPanel();
        tbpAbas.addTab("Dados Cadastrais", pnlDadosCadastrais);

        pnlDadosReceitas = new JPanel();
        tbpAbas.addTab("Receitas", pnlDadosReceitas);


        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);    //acessibilidade
        btnGravar.setToolTipText("btnGravarConsulta"); //acessibilidade
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_consulta");

        pnlSul.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    //acessibilidade
        btnCancelar.setToolTipText("btnCancelarConsulta"); //acessibilidade
        btnCancelar.setActionCommand("botao_cancelar_formulario_consulta");

        pnlSul.add(btnCancelar);

        this.add(pnlSul, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){

            Consulta consulta = new Consulta();


            //controle.cadastrar(consulta);

            pnlAConsulta.showTela("tela_consulta_listagem");
            
            
        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){
            
        }
    }
}

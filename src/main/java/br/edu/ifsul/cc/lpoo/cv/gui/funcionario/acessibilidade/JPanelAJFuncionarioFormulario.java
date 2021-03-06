package br.edu.ifsul.cc.lpoo.cv.gui.funcionario.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Cargo;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

public class JPanelAJFuncionarioFormulario extends JPanel implements ActionListener {


    private JPanelAFuncionario pnlAFuncionario;
    private Controle controle;

    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;
    private Funcionario funcionarioM;
    private SimpleDateFormat format;

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
    private Calendar dataCadastroFunc;

    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;


    public JPanelAJFuncionarioFormulario(JPanelAFuncionario pnlAFuncionario, Controle controle) {

        this.pnlAFuncionario = pnlAFuncionario;
        this.controle = controle;

        initComponents();

    }

    public Funcionario getFuncionariobyFormulario() throws ParseException {

        //validacao do formulario
        if (txfCpf.getText().trim().length() > 4 &&
                new String(txfSenha.getPassword()).trim().length() > 3) {

            Funcionario funcionario = new Funcionario();

            funcionario.setCargo((Cargo) txfCargo.getSelectedItem());
            funcionario.setNumero_pis(txfNumero_pis.getText());
            funcionario.setNumero_ctps(txfNumero_ctps.getText());
            funcionario.setCpf(txfCpf.getText());
            funcionario.setSenha(String.valueOf(txfSenha.getPassword()));
            funcionario.setRg(txfRg.getText());
            funcionario.setEndereco(txfEndereco.getText());
            funcionario.setEmail(txfEmail.getText());
            funcionario.setNumero_celular(txfNumero_celular.getText());
            funcionario.setNome(txfNome.getText());
            funcionario.setComplemento(txfComplemento.getText());
            Calendar c = Calendar.getInstance();
            c.setTime(format.parse(txfData_nascimento.getText()));
            funcionario.setData_nascimento(c);
            funcionario.setCep(txfCep.getText());
            funcionario.setTipo("F");
            if (dataCadastroFunc != null){
                funcionario.setData_cadastro(dataCadastroFunc);
            }

            return funcionario;
        }

        return null;
    }

    public void setFuncionarioFormulario(Funcionario funcionario) {
        if(funcionario == null){
            txfCpf.setText("");
            txfRg.setText("");
            txfCep.setText("");
            txfComplemento.setText("");
            txfData_nascimento.setText("");
            txfEmail.setText("");
            txfEndereco.setText("");
            txfNome.setText("");
            txfNumero_celular.setText("");
            txfCargo.setSelectedIndex(0);
            txfNumero_pis.setText("");
            txfNumero_ctps.setText("");
            txfSenha.setText("");
            dataCadastroFunc = null;
            txfCpf.setEditable(true);
            funcionarioM=null;
        }else{
            funcionarioM = funcionario;
            txfCpf.setEditable(false);
            txfCpf.setText(funcionarioM.getCpf());
            txfRg.setText(funcionarioM.getRg());
            txfCep.setText(funcionarioM.getCep());
            txfComplemento.setText(funcionarioM.getComplemento());
            txfEmail.setText(funcionarioM.getEmail());
            txfEndereco.setText(funcionarioM.getEndereco());
            txfNome.setText(funcionarioM.getNome());
            txfNumero_celular.setText(funcionarioM.getNumero_celular());
            txfData_nascimento.setText(format.format(funcionarioM.getData_nascimento().getTime()));
            txfCargo.getModel().setSelectedItem(funcionarioM.getCargo());
            txfNumero_pis.setText(funcionarioM.getNumero_pis());
            txfNumero_ctps.setText(funcionarioM.getNumero_ctps());
            txfSenha.setText(funcionarioM.getSenha());
            dataCadastroFunc = funcionarioM.getData_cadastro();
        }
    }

    private void initComponents() {

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        tbpAbas = new JTabbedPane();
        this.add(tbpAbas, BorderLayout.CENTER);

        pnlDadosCadastrais = new JPanel();
        gridBagLayoutDadosCadastrais = new GridBagLayout();
        pnlDadosCadastrais.setLayout(gridBagLayoutDadosCadastrais);

        lblCpf = new JLabel("CPF:");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblCpf, posicionador);//o add adiciona o rotulo no painel

        txfCpf = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfCpf, posicionador);//o add adiciona o rotulo no painel

        lblCargo = new JLabel("Cargo:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 0;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblCargo, posicionador);//o add adiciona o rotulo no painel

        txfCargo = new JComboBox();
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posi????o da coluna (horizontal)
        txfCargo.addItem(Cargo.ADESTRADOR);
        txfCargo.addItem(Cargo.ATENDENTE);
        txfCargo.addItem(Cargo.AUXILIAR_VETERINARIO);
        pnlDadosCadastrais.add(txfCargo, posicionador);//o add adiciona o rotulo no painel

        lblNumero_ctps = new JLabel("CTPS:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 0;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblNumero_ctps, posicionador);//o add adiciona o rotulo no painel

        txfNumero_ctps = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 1;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfNumero_ctps, posicionador);//o add adiciona o rotulo no painel

        lblNumero_pis = new JLabel("PIS:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 0;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblNumero_pis, posicionador);//o add adiciona o rotulo no painel

        txfNumero_pis = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 1;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfNumero_pis, posicionador);//o add adiciona o rotulo no painel

        lblSenha = new JLabel("Senha:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblSenha, posicionador);//o add adiciona o rotulo no painel

        txfSenha = new JPasswordField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfSenha, posicionador);//o add adiciona o rotulo no painel

        lblRg = new JLabel("RG:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 2;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblRg, posicionador);//o add adiciona o rotulo no painel

        txfRg = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 3;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfRg, posicionador);//o add adiciona o rotulo no painel

        lblData_nascimento = new JLabel("Data de Nacimento:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 2;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblData_nascimento, posicionador);//o add adiciona o rotulo no painel

        txfData_nascimento = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 3;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfData_nascimento, posicionador);//o add adiciona o rotulo no painel

        lblEmail = new JLabel("Email:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 2;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblEmail, posicionador);//o add adiciona o rotulo no painel

        txfEmail = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 3;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfEmail, posicionador);//o add adiciona o rotulo no painel

        lblNome = new JLabel("Nome:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 2;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblNome, posicionador);//o add adiciona o rotulo no painel

        txfNome = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 3;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfNome, posicionador);//o add adiciona o rotulo no painel

        lblNumero_celular = new JLabel("Numero Celular:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 2;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblNumero_celular, posicionador);//o add adiciona o rotulo no painel

        txfNumero_celular = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 3;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfNumero_celular, posicionador);//o add adiciona o rotulo no painel

        lblCep = new JLabel("Cep:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 0;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblCep, posicionador);//o add adiciona o rotulo no painel

        txfCep = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 1;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfCep, posicionador);//o add adiciona o rotulo no painel

        lblComplemento = new JLabel("Complemento:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;//policao da linha (vertical)
        posicionador.gridx = 0;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblComplemento, posicionador);//o add adiciona o rotulo no painel

        txfComplemento = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 8;//policao da linha (vertical)
        posicionador.gridx = 1;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfComplemento, posicionador);//o add adiciona o rotulo no painel

        lblEndereco = new JLabel("Endereco:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;//policao da linha (vertical)
        posicionador.gridx = 0;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(lblEndereco, posicionador);//o add adiciona o rotulo no painel

        txfEndereco = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 9;//policao da linha (vertical)
        posicionador.gridx = 1;// posi????o da coluna (horizontal)
        pnlDadosCadastrais.add(txfEndereco, posicionador);//o add adiciona o rotulo no painel

        tbpAbas.addTab("Dados Cadastrais", pnlDadosCadastrais);


        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);    //acessibilidade
        btnGravar.setToolTipText("btnGravarFuncionario"); //acessibilidade
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_funcionario");

        pnlSul.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    //acessibilidade
        btnCancelar.setToolTipText("btnCancelarFuncionario"); //acessibilidade
        btnCancelar.setActionCommand("botao_cancelar_formulario_funcionario");

        pnlSul.add(btnCancelar);

        this.add(pnlSul, BorderLayout.SOUTH);

        format = new SimpleDateFormat("dd/MM/yyyy");

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getActionCommand().equals(btnGravar.getActionCommand())) {
            Funcionario f = null;
            try {
                f = getFuncionariobyFormulario();
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar Funcionario! : " + ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

            if (f != null) {

                try {

                    pnlAFuncionario.getControle().getConexaoJDBC().persist(f);

                    JOptionPane.showMessageDialog(this, "Funcionario e Pessoa armazenados!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    pnlAFuncionario.showTela("tela_funcionario_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Funcionario! : " + ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {

                JOptionPane.showMessageDialog(this, "Preencha o formul??rio!", "Edi????o", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (arg0.getActionCommand().equals(btnCancelar.getActionCommand())) {


            pnlAFuncionario.showTela("tela_funcionario_listagem");

        }
    }


}

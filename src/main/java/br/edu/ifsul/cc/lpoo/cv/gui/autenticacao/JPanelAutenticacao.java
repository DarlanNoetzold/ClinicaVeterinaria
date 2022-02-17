package br.edu.ifsul.cc.lpoo.cv.gui.autenticacao;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class JPanelAutenticacao extends JPanel implements ActionListener {

    private Controle controle;
    private GridBagLayout gridLayout;
    private GridBagConstraints posicionador;

    private JLabel  lblCPF;
    private JLabel lblSenha;
    private JTextField txfCPF;
    private JPasswordField psfSenha;
    private JButton btnLogar;

    //construtor da classe que recebe um parametro.
    public JPanelAutenticacao(Controle controle){

        this.controle = controle;
        initComponents();
    }

    private void initComponents(){

        gridLayout = new GridBagLayout();//inicializando o gerenciador de layout
        this.setLayout(gridLayout);//definie o gerenciador para este painel.

        lblCPF = new JLabel("CPF:");
        lblCPF.setFocusable(true);    //acessibilidade
        lblCPF.setToolTipText("lblCPF"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblCPF, posicionador);//o add adiciona o rotulo no painel

        txfCPF = new JTextField(10);
        txfCPF.setFocusable(true);    //acessibilidade
        txfCPF.setToolTipText("txfCPF"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(txfCPF, posicionador);//o add adiciona o rotulo no painel

        lblSenha = new JLabel("Senha:");
        lblSenha.setFocusable(true);    //acessibilidade
        lblSenha.setToolTipText("lblSenha"); //acessibilidade

        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblSenha, posicionador);//o add adiciona o rotulo no painel

        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);    //acessibilidade
        psfSenha.setToolTipText("psfSenha"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(psfSenha, posicionador);//o add adiciona o rotulo no painel

        btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);    //acessibilidade
        btnLogar.setToolTipText("btnLogar"); //acessibilidade
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        btnLogar.addActionListener(this);//registrar o botão no Listener
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);//o add adiciona o rotulo no painel

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //testa para verificar se o botão btnLogar foi clicado.
        if(e.getActionCommand().equals(btnLogar.getActionCommand())){

            //validacao do formulario.
            if(txfCPF.getText().trim().length() > 4 && new String(psfSenha.getPassword()).trim().length() > 3 ){

                controle.autenticar(txfCPF.getText().trim(), new String(psfSenha.getPassword()).trim());

            }else{

                JOptionPane.showMessageDialog(this, "Informe os dados para CPF e Senha!", "Autenticação", JOptionPane.ERROR_MESSAGE);

            }

        }

    }

}

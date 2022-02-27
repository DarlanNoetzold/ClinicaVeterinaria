package br.edu.ifsul.cc.lpoo.cv.gui.receita.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;
import br.edu.ifsul.cc.lpoo.cv.model.Pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class JPanelAReceitaFormulario extends JPanel implements ActionListener{


    private JPanelAReceita pnlAReceita;
    private Controle controle;

    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;

    private JPanel pnlDadosReceitas;

    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblId;
    private JTextField txfId;

    private JLabel lblOrientacao;
    private JTextField txfOrientacao;

    private JLabel lblConsulta;
    private JComboBox cbxConsulta;


    private SimpleDateFormat format;

    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;

    private Receita receitaM;




    public JPanelAReceitaFormulario(JPanelAReceita pnlAReceita, Controle controle){

        this.pnlAReceita = pnlAReceita;
        this.controle = controle;

        initComponents();

    }

    public void populaComboConsulta(){

        cbxConsulta.removeAllItems();

        DefaultComboBoxModel model =  (DefaultComboBoxModel) cbxConsulta.getModel();

        model.addElement("Selecione");
        try {

            List<Consulta> listConsultas = controle.getConexaoJDBC().listPesistenciaConsulta();
            for(Consulta c : listConsultas){
                model.addElement(c);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro ao listar Consultas -:"+ex.getLocalizedMessage(), "Consultas", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }


    }


    private Receita getReceitabyFormulario() {
        Receita c = new Receita();
        if(!txfId.getText().equals(""))
            c.setId(Integer.valueOf(txfId.getText()));
        c.setOrientacao(txfOrientacao.getText());
        c.setConsulta((Consulta) cbxConsulta.getSelectedItem());

        return c;
    }

    public void setReceitaFormulario(Receita c) {
        if (c == null) {
            txfId.setText("");
            cbxConsulta.setSelectedIndex(0);
            txfOrientacao.setText("");
            receitaM = null;
        } else {
            receitaM = c;
            txfId.setEditable(false);
            txfId.setText(String.valueOf(receitaM.getId()));
            txfOrientacao.setText(c.getOrientacao());
            cbxConsulta.getModel().setSelectedItem(receitaM.getConsulta());
        }
    }

    private void initComponents(){

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        tbpAbas = new JTabbedPane();
        this.add(tbpAbas, BorderLayout.CENTER);

        pnlDadosReceitas = new JPanel();
        gridBagLayoutDadosCadastrais = new GridBagLayout();
        pnlDadosReceitas.setLayout(gridBagLayoutDadosCadastrais);

        lblId = new JLabel("Id:");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosReceitas.add(lblId, posicionador);//o add adiciona o rotulo no painel

        txfId = new JTextField(20);
        txfId.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosReceitas.add(txfId, posicionador);//o add adiciona o rotulo no painel

        lblOrientacao = new JLabel("Orientação:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosReceitas.add(lblOrientacao, posicionador);//o add adiciona o rotulo no painel

        txfOrientacao = new JTextField(20);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosReceitas.add(txfOrientacao, posicionador);//o add adiciona o rotulo no painel

        lblConsulta = new JLabel("Consulta:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosReceitas.add(lblConsulta, posicionador);//o add adiciona o rotulo no painel

        cbxConsulta = new JComboBox();
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosReceitas.add(cbxConsulta, posicionador);//o add adiciona o rotulo no painel

        tbpAbas.addTab("Receita", pnlDadosReceitas);


        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);    //acessibilidade
        btnGravar.setToolTipText("btnGravarReceita"); //acessibilidade
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_receita");

        pnlSul.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    //acessibilidade
        btnCancelar.setToolTipText("btnCancelarReceita"); //acessibilidade
        btnCancelar.setActionCommand("botao_cancelar_formulario_receita");

        pnlSul.add(btnCancelar);

        this.add(pnlSul, BorderLayout.SOUTH);

        format = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){

            Receita receita = getReceitabyFormulario();

            if(receita != null){

                try {

                    pnlAReceita.getControle().getConexaoJDBC().persist(receita);

                    JOptionPane.showMessageDialog(this, "Receita armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    pnlAReceita.showTela("tela_receita_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Receita! : "+ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }else{

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){
            pnlAReceita.showTela("tela_receita_listagem");

        }
    }

}

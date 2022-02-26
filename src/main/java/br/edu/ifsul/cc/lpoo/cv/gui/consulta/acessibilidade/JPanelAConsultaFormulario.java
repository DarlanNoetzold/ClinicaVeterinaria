package br.edu.ifsul.cc.lpoo.cv.gui.consulta.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Cargo;
import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;
import br.edu.ifsul.cc.lpoo.cv.model.Pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class JPanelAConsultaFormulario extends JPanel implements ActionListener{
    
    
    private JPanelAConsulta pnlAConsulta;
    private Controle controle;
    
    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;

    private JPanel pnlDadosConsultas;

    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblId;
    private JTextField txfId;

    private JLabel lblMedico;
    private JComboBox cbxMedico;

    private JLabel lblPet;
    private JComboBox cbxPet;


    private SimpleDateFormat format;

    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;

    private Consulta consultaM;


    
    
    public JPanelAConsultaFormulario(JPanelAConsulta pnlAConsulta, Controle controle){
        
        this.pnlAConsulta = pnlAConsulta;
        this.controle = controle;
        
        initComponents();
        
    }

    public void populaComboMedico(){

        cbxMedico.removeAllItems();

        DefaultComboBoxModel model =  (DefaultComboBoxModel) cbxMedico.getModel();

        model.addElement("Selecione");
        try {

            List<Medico> listMedicos = controle.getConexaoJDBC().listMedico();
            for(Medico m : listMedicos){
                model.addElement(m);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro ao listar Medicos -:"+ex.getLocalizedMessage(), "Medicos", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }


    }

    public void populaComboPet(){

        cbxPet.removeAllItems();//zera o combo

        DefaultComboBoxModel model =  (DefaultComboBoxModel) cbxPet.getModel();

        model.addElement("Selecione"); //primeiro item
        try {

            List<Pet> listPets = controle.getConexaoJDBC().listPet();
            for(Pet p : listPets){
                model.addElement(p);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro ao listar Pets -:"+ex.getLocalizedMessage(), "Pets", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }


    }

    private Consulta getConsultabyFormulario() {
        Consulta c = new Consulta();
        if(!txfId.getText().equals(""))
            c.setId(Integer.valueOf(txfId.getText()));
        c.setMedico((Medico) cbxMedico.getSelectedItem());
        c.setPet((Pet) cbxPet.getSelectedItem());

        return c;
    }

    public void setConsultaFormulario(Consulta c) {
        if(c == null){//se o parametro estiver nullo, limpa o formulario
            txfId.setText("");
            cbxMedico.setSelectedIndex(0);
            cbxPet.setSelectedIndex(0);

            consultaM = null;
        }else{
            consultaM = c;
            txfId.setEditable(false);
            txfId.setText(String.valueOf(consultaM.getId()));
            cbxMedico.getModel().setSelectedItem(consultaM.getMedico());
            cbxPet.getModel().setSelectedItem(consultaM.getPet());
        }
    }
    
    private void initComponents(){

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        tbpAbas = new JTabbedPane();
        this.add(tbpAbas, BorderLayout.CENTER);

        pnlDadosConsultas = new JPanel();
        gridBagLayoutDadosCadastrais = new GridBagLayout();
        pnlDadosConsultas.setLayout(gridBagLayoutDadosCadastrais);

        lblId = new JLabel("Id:");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosConsultas.add(lblId, posicionador);//o add adiciona o rotulo no painel

        txfId = new JTextField(20);
        txfId.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosConsultas.add(txfId, posicionador);//o add adiciona o rotulo no painel

        lblMedico = new JLabel("Medico:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosConsultas.add(lblMedico, posicionador);//o add adiciona o rotulo no painel

        cbxMedico = new JComboBox();
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosConsultas.add(cbxMedico, posicionador);//o add adiciona o rotulo no painel

        lblPet = new JLabel("Pet:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosConsultas.add(lblPet, posicionador);//o add adiciona o rotulo no painel

        cbxPet = new JComboBox();
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosConsultas.add(cbxPet, posicionador);//o add adiciona o rotulo no painel

        tbpAbas.addTab("Consulta", pnlDadosConsultas);


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

        format = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){

            Consulta consulta = getConsultabyFormulario();

            if(consulta != null){

                try {

                    pnlAConsulta.getControle().getConexaoJDBC().persist(consulta);

                    JOptionPane.showMessageDialog(this, "Consulta armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    pnlAConsulta.showTela("tela_consulta_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Consulta! : "+ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }else{

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){
            pnlAConsulta.showTela("tela_consulta_listagem");

        }
    }




}

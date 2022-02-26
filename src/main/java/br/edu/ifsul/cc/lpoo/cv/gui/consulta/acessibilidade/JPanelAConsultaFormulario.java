package br.edu.ifsul.cc.lpoo.cv.gui.consulta.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Cargo;
import br.edu.ifsul.cc.lpoo.cv.model.Consulta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

public class JPanelAConsultaFormulario extends JPanel implements ActionListener{
    
    
    private JPanelAConsulta pnlAConsulta;
    private Controle controle;
    
    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;

    private JPanel pnlDadosConsultas;

    private JPanel pnlCentroDadosCadastrais;
    
    private GridBagLayout gridBagLayoutDadosCadastrais;

    private SimpleDateFormat format;

    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;


    
    
    public JPanelAConsultaFormulario(JPanelAConsulta pnlAConsulta, Controle controle){
        
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

        pnlDadosConsultas = new JPanel();
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

            Consulta consulta = new Consulta();


            //controle.cadastrar(consulta);

            pnlAConsulta.showTela("tela_consulta_listagem");
            
            
        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){
            
        }
    }
}

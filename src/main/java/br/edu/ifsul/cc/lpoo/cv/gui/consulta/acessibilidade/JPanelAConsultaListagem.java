
package br.edu.ifsul.cc.lpoo.cv.gui.consulta.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;


public class JPanelAConsultaListagem extends JPanel implements ActionListener{
    
    private JPanelAConsulta pnlAConsulta;
    private Controle controle;
    
    private BorderLayout borderLayout;
    private JPanel pnlNorte;
    private JLabel lblFiltroConsulta;
    private JTextField txfFiltroConsulta;
    private JButton btnFiltroConsulta;
    
    private JPanel pnlCentro;
    private JScrollPane scpListagem;
    private JTable tblListagem;
    private DefaultTableModel modeloTabela;
    
    private JPanel pnlSul;
    private JButton btnNovo;
    private JButton btnAlterar;
    private JButton btnRemover;
    
    
    public JPanelAConsultaListagem(JPanelAConsulta pnlAConsulta, Controle controle){

        this.pnlAConsulta = pnlAConsulta;
        this.controle = controle;
        
        initComponents();
    }

    public void populaTable(){

        DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();//recuperacao do modelo da tabela

        model.setRowCount(0);//elimina as linhas existentes (reset na tabela)
        try {
            List<Consulta> listConsultas = controle.getConexaoJDBC().listPesistenciaConsulta();
            for(Consulta c : listConsultas){
                if(txfFiltroConsulta.getText().equals("")){
                    model.addRow(new Object[]{c, c.getMedico().getCpf(), c.getPet().getNome()});
                }else if(txfFiltroConsulta.getText().equals(String.valueOf(c.getId()))) {
                    model.addRow(new Object[]{c, c.getMedico().getCpf(), c.getPet().getNome()});
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar Consultas -:"+ex.getLocalizedMessage(), "Consulta", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }



    private void initComponents(){
        
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);//seta o gerenciado border para este painel
        
        pnlNorte = new JPanel();
        pnlNorte.setLayout(new FlowLayout());
        
        lblFiltroConsulta = new JLabel("Filtrar por Id:");
        pnlNorte.add(lblFiltroConsulta);
        
        txfFiltroConsulta = new JTextField(20);
        pnlNorte.add(txfFiltroConsulta);
        
        btnFiltroConsulta = new JButton("Filtrar");
        btnFiltroConsulta.addActionListener(this);
        btnFiltroConsulta.setFocusable(true);    //acessibilidade
        btnFiltroConsulta.setToolTipText("btnFiltrar"); //acessibilidade
        btnFiltroConsulta.setActionCommand("botao_filtro");
        pnlNorte.add(btnFiltroConsulta);
        
        this.add(pnlNorte, BorderLayout.NORTH);//adiciona o painel na posicao norte.
        
        pnlCentro = new JPanel();
        pnlCentro.setLayout(new BorderLayout());
        
            
        scpListagem = new JScrollPane();
        tblListagem =  new JTable();

        modeloTabela = new DefaultTableModel(
            new String [] {
                "Id", "CPF Médico", "Nome do Pet"
            }, 0){
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        tblListagem.setModel(modeloTabela);
        scpListagem.setViewportView(tblListagem);

        pnlCentro.add(scpListagem, BorderLayout.CENTER);
    
        
        this.add(pnlCentro, BorderLayout.CENTER);//adiciona o painel na posicao norte.
        
        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());
        
        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(this);
        btnNovo.setFocusable(true);    //acessibilidade    
        btnNovo.setToolTipText("btnNovo"); //acessibilidade
        btnNovo.setMnemonic(KeyEvent.VK_N);
        btnNovo.setActionCommand("botao_novo");
        
        pnlSul.add(btnNovo);
        
        btnAlterar = new JButton("Editar");
        btnAlterar.addActionListener(this);
        btnAlterar.setFocusable(true);    //acessibilidade    
        btnAlterar.setToolTipText("btnAlterar"); //acessibilidade
        btnAlterar.setActionCommand("botao_alterar");
        
        pnlSul.add(btnAlterar);
        
        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(this);
        btnRemover.setFocusable(true);    //acessibilidade    
        btnRemover.setToolTipText("btnRemvoer"); //acessibilidade
        btnRemover.setActionCommand("botao_remover");
        
        pnlSul.add(btnRemover);//adiciona o botao na fila organizada pelo flowlayout
        
        
        this.add(pnlSul, BorderLayout.SOUTH);//adiciona o painel na posicao norte.
      
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    
        if(arg0.getActionCommand().equals(btnNovo.getActionCommand())){
            
            pnlAConsulta.showTela("tela_consulta_formulario");
            pnlAConsulta.getFormulario().setConsultaFormulario(null);
            
        }else if(arg0.getActionCommand().equals(btnAlterar.getActionCommand())){
            int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Consulta c = (Consulta) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                pnlAConsulta.showTela("tela_consulta_formulario");
                pnlAConsulta.getFormulario().setConsultaFormulario(c);
                pnlAConsulta.getFormulario().populaTableReceitas();
            }else{
                JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(arg0.getActionCommand().equals(btnRemover.getActionCommand())){
            int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Consulta c = (Consulta) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                try {
                    pnlAConsulta.getControle().getConexaoJDBC().remover(c);
                    JOptionPane.showMessageDialog(this, "Consulta removido!", "Jogador", JOptionPane.INFORMATION_MESSAGE);
                    populaTable(); //refresh na tabela

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Jogador -:"+ex.getLocalizedMessage(), "Jogadores", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }else if(arg0.getActionCommand().equals(btnFiltroConsulta.getActionCommand())){
            populaTable();
        }
    
    
    }
    
}

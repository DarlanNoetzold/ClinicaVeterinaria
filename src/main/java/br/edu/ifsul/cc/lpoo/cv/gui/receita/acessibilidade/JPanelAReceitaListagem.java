
package br.edu.ifsul.cc.lpoo.cv.gui.receita.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;


public class JPanelAReceitaListagem extends JPanel implements ActionListener{

    private JPanelAReceita pnlAReceita;
    private Controle controle;

    private BorderLayout borderLayout;
    private JPanel pnlNorte;
    private JLabel lblFiltroReceita;
    private JTextField txfFiltroReceita;
    private JButton btnFiltroReceita;

    private JPanel pnlCentro;
    private JScrollPane scpListagem;
    private JTable tblListagem;
    private DefaultTableModel modeloTabela;

    private JPanel pnlSul;
    private JButton btnNovo;
    private JButton btnAlterar;
    private JButton btnRemover;


    public JPanelAReceitaListagem(JPanelAReceita pnlAReceita, Controle controle){

        this.pnlAReceita = pnlAReceita;
        this.controle = controle;

        initComponents();
    }

    public void populaTable(){

        DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel();//recuperacao do modelo da tabela

        model.setRowCount(0);//elimina as linhas existentes (reset na tabela)
        try {
            List<Receita> listReceitas = controle.getConexaoJDBC().listReceitas();
            for(Receita r : listReceitas){
                if(txfFiltroReceita.getText().equals("")){
                    model.addRow(new Object[]{r, r.getOrientacao(), r.getConsulta().getId()});
                }else if(txfFiltroReceita.getText().equals(String.valueOf(r.getId()))){
                    model.addRow(new Object[]{r, r.getOrientacao(), r.getConsulta().getId()});
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar Receitas -:"+ex.getLocalizedMessage(), "Receita", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    private void initComponents(){

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);//seta o gerenciado border para este painel

        pnlNorte = new JPanel();
        pnlNorte.setLayout(new FlowLayout());

        lblFiltroReceita = new JLabel("Filtrar por Id:");
        pnlNorte.add(lblFiltroReceita);

        txfFiltroReceita = new JTextField(20);
        pnlNorte.add(txfFiltroReceita);

        btnFiltroReceita = new JButton("Filtrar");
        btnFiltroReceita.addActionListener(this);
        btnFiltroReceita.setFocusable(true);    //acessibilidade
        btnFiltroReceita.setToolTipText("btnFiltrar"); //acessibilidade
        btnFiltroReceita.setActionCommand("botao_filtro");
        pnlNorte.add(btnFiltroReceita);

        this.add(pnlNorte, BorderLayout.NORTH);//adiciona o painel na posicao norte.

        pnlCentro = new JPanel();
        pnlCentro.setLayout(new BorderLayout());


        scpListagem = new JScrollPane();
        tblListagem =  new JTable();

        modeloTabela = new DefaultTableModel(
                new String [] {
                        "Id", "Orieta????o", "Consulta Id"
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

            pnlAReceita.showTela("tela_receita_formulario");
            pnlAReceita.getFormulario().setReceitaFormulario(null);

        }else if(arg0.getActionCommand().equals(btnAlterar.getActionCommand())){
            int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Receita c = (Receita) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                pnlAReceita.showTela("tela_receita_formulario");
                pnlAReceita.getFormulario().setReceitaFormulario(c);
            }else{
                JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!", "Edi????o", JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(arg0.getActionCommand().equals(btnRemover.getActionCommand())){
            int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Receita c = (Receita) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                try {
                    pnlAReceita.getControle().getConexaoJDBC().remover(c);
                    JOptionPane.showMessageDialog(this, "Receita removido!", "Jogador", JOptionPane.INFORMATION_MESSAGE);
                    populaTable(); //refresh na tabela

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Jogador -:"+ex.getLocalizedMessage(), "Jogadores", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remo????o", JOptionPane.INFORMATION_MESSAGE);
            }

        }else if(arg0.getActionCommand().equals(btnFiltroReceita.getActionCommand())){
            populaTable();
        }


    }

}

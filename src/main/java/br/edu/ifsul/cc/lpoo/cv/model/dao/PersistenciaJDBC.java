package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.*;

import java.sql.*;
import java.util.Calendar;


public class PersistenciaJDBC implements InterfacePersistencia {

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "admin";
    public static final String URL = "jdbc:postgresql://localhost:5432/clinica_vet_db";
    private Connection con = null;

    public PersistenciaJDBC () throws Exception {

        Class.forName(DRIVER);
        System.out.println("Tentando estabelecer conexao JDBC com : "+URL+" ...");

        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);

    }


    @Override
    public Boolean conexaoAberta() {

        try {
            if(con != null)
                return !con.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    @Override
    public void fecharConexao() {

        try{
            this.con.close();
            System.out.println("Fechou conexao JDBC");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Object find(Class c, Object id) throws Exception {

        if (c == Consulta.class) {

            PreparedStatement ps = this.con.prepareStatement("select id, medico_id, pet_id from tb_consulta where id = ? ");
            ps.setInt(1, Integer.parseInt(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Consulta con = new Consulta();
                con.setId(rs.getInt("id"));
                con.setMedico((Medico) find(Medico.class, rs.getInt("medico_id")));
                con.setPet((Pet) find(Pet.class, rs.getInt("pet_id")));

                ps.close();

                return con;
            }

        } else if (c == Medico.class) {
            PreparedStatement ps = this.con.prepareStatement("select numero_csmv, cpf from tb_medico where id = ? ");
            ps.setInt(1, Integer.parseInt(id.toString()));
            ResultSet rsMedico = ps.executeQuery();
            if (rsMedico.next()) {
                Medico m = new Medico();
                m.setNumero_crmv(rsMedico.getString("numero_csmv"));
                m.setCpf(rsMedico.getString("cpf"));

                ps.close();

                return m;
            }
        } else if (c == Pet.class) {
            PreparedStatement ps = this.con.prepareStatement("select id, data_nascimento, nome, observacao, cliente_id, raca_id from tb_pet where id = ? ");
            ps.setInt(1, Integer.parseInt(id.toString()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pet p = new Pet();
                p.setId(rs.getInt("id"));
                Calendar dtU = Calendar.getInstance();
                dtU.setTimeInMillis(rs.getDate("data_nascimento").getTime());
                p.setData_nascimento(dtU);
                p.setNome(rs.getString("nome"));
                p.setObservacao(rs.getString("observacao"));
                p.setCliente((Cliente) find(Cliente.class, rs.getInt("cliente_id")));
                p.setRaca((Raca) find(Raca.class, rs.getInt("raca_id")));

                ps.close();

                return p;
            }

        } else if (c == Cliente.class) {
            PreparedStatement ps = this.con.prepareStatement("select cpf, data_ultima_visita from tb_cliente where id = ? ");
            ps.setInt(1, Integer.parseInt(id.toString()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cl = new Cliente();
                Calendar dtU = Calendar.getInstance();
                dtU.setTimeInMillis(rs.getDate("data_ultima_visita").getTime());
                cl.setData_ultima_visita(dtU);
                cl.setCpf(rs.getString("cpf"));

                ps.close();

                return cl;
            }
        } else if (c == Raca.class) {
            PreparedStatement ps = this.con.prepareStatement("select id, nome, especie_id from tb_raca where id = ? ");
            ps.setInt(1, Integer.parseInt(id.toString()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Raca ra = new Raca();
                ra.setId(rs.getInt("id"));
                ra.setNome(rs.getString("nome"));
                ra.setEspecie((Especie) find(Especie.class, rs.getInt("especie_id")));

                ps.close();

                return ra;
            }
        } else if (c == Especie.class) {
            PreparedStatement ps = this.con.prepareStatement("select id, nome, especie_id from tb_raca where id = ? ");
            ps.setInt(1, Integer.parseInt(id.toString()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Especie esp = new Especie();
                esp.setId(rs.getInt("id"));
                esp.setNome(rs.getString("nome"));

                ps.close();

                return esp;
            }
        } else if (c == Receita.class) {
            PreparedStatement ps = this.con.prepareStatement("select id, orientacao, consulta_id where id = ? ");
            ps.setInt(1, Integer.parseInt(id.toString()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Receita rec = new Receita();
                rec.setId(rs.getInt("id"));
                rec.setConsulta((Consulta) find(Consulta.class, rs.getInt("consulta_id")));

                ps.close();

                return rec;
            }
        }
        return null;
    }
    @Override
    public void persist(Object o) throws Exception {

        if(o instanceof Consulta){

            Consulta c = (Consulta) o;

            if(c.getId() == null){

                PreparedStatement ps = this.con.prepareStatement("insert into tb_consulta "
                                                                                + "(id, medico_id, pet_id) values "
                                                                                + "(nextval('seq_endereco_id'), ?, ?)", new String[]{"id"});

                ps.setString(1, c.getMedico().getCpf());
                ps.setInt(2, c.getPet().getId());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    c.setId(rs.getInt(1));
                }
            }else{

                //update.
                PreparedStatement ps = this.con.prepareStatement("update tb_consulta set "
                        + "medico_id = ?, "
                        + "pet_id = ? "
                        + "where id = ?");
                ps.setString(1, c.getMedico().getCpf());
                ps.setInt(2, c.getPet().getId());
                ps.setInt(3, c.getId());

                ps.execute();//executa o comando.
            }


        }else if (o instanceof Receita){

            Receita r = (Receita) o; //converter o para o e que é do tipo Endereco
            if(r.getId() == null){

                //insert.

            }else{

                //update.
            }

        }
    }

    @Override
    public void remover(Object o) throws Exception {

    }
}
package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PersistenciaJDBC implements InterfacePersistencia {

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "admin";
    public static final String URL = "jdbc:postgresql://localhost:5432/clinica_vet_db";
    private Connection con = null;

    public PersistenciaJDBC() throws Exception {

        Class.forName(DRIVER);
        System.out.println("Tentando estabelecer conexao JDBC com : " + URL + " ...");

        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);

    }


    @Override
    public Boolean conexaoAberta() {

        try {
            if (con != null)
                return !con.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    @Override
    public void fecharConexao() {

        try {
            this.con.close();
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException e) {
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
                con.setReceitas(listReceitasDeConsulta(con));

                ps.close();

                return con;
            }

        } else if(c == Pessoa.class){

            PreparedStatement ps = this.con.prepareStatement("select cpf, rg, nome, senha, numero_celular, email, data_cadastro, data_nascimento, cep, endereco, complemento from tb_pessoa where cpf = ?");

            ps.setString(1, id.toString());
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                Pessoa p = new Pessoa();
                p.setCpf(rs.getString("cpf"));
                p.setRg(rs.getString("rg"));
                p.setNome(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                p.setNumero_celular(rs.getString("numero_celular"));
                p.setEmail(rs.getString("email"));
                Calendar dc = Calendar.getInstance();
                dc.setTimeInMillis(rs.getDate("data_cadastro").getTime());
                p.setData_cadastro(dc);
                Calendar dn = Calendar.getInstance();
                dn.setTimeInMillis(rs.getDate("data_nascimento").getTime());
                p.setData_nascimento(dn);
                p.setCep(rs.getString("cep"));
                p.setEndereco(rs.getString("endereco"));
                p.setComplemento(rs.getString("complemento"));

                ps.close();
                return p;
            }
        }else if (c == Medico.class) {
            PreparedStatement ps = this.con.prepareStatement("select numero_crmv, cpf from tb_medico where cpf = ?");
            ps.setString(1, id.toString());
            ResultSet rsMedico = ps.executeQuery();
            if (rsMedico.next()) {
                Medico m = new Medico();
                m.setNumero_crmv(rsMedico.getString("numero_crmv"));
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
                p.setCliente((Cliente) find(Cliente.class, rs.getString("cliente_id")));
                p.setRaca((Raca) find(Raca.class, rs.getInt("raca_id")));

                ps.close();

                return p;
            }

        } else if (c == Cliente.class) {
            PreparedStatement ps = this.con.prepareStatement("select cpf, data_ultima_visita from tb_cliente where cpf = ? ");
            ps.setString(1, id.toString());
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
            PreparedStatement ps = this.con.prepareStatement("select id, nome from tb_especie where id = ? ");
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
            PreparedStatement ps = this.con.prepareStatement("select id, orientacao, consulta_id from tb_receita where id = ? ");
            ps.setInt(1, Integer.parseInt(id.toString()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Receita rec = new Receita();
                rec.setId(rs.getInt("id"));
                rec.setOrientacao((rs.getString("orientacao")));
                rec.setConsulta((Consulta) find(Consulta.class, rs.getInt("consulta_id")));

                ps.close();

                return rec;
            }
        }
        return null;
    }

    @Override
    public void persist(Object o) throws Exception {

        if (o instanceof Consulta) {

            Consulta c = (Consulta) o;

            if (c.getId() == null) {
                System.out.println("Iniciando Insert em Consulta...");
                PreparedStatement ps = this.con.prepareStatement("insert into tb_consulta "
                        + "(id, medico_id, pet_id) values "
                        + "(nextval('seq_consulta_id'), ?, ?)");

                ps.setString(1, c.getMedico().getCpf());
                ps.setInt(2, c.getPet().getId());

                ps.executeUpdate();
            } else {
                System.out.println("Iniciando Update em Consulta...");
                PreparedStatement ps = this.con.prepareStatement("update tb_consulta set "
                        + "medico_id = ?, "
                        + "pet_id = ? "
                        + "where id = ?");
                ps.setString(1, c.getMedico().getCpf());
                ps.setInt(2, c.getPet().getId());
                ps.setInt(3, c.getId());

                ps.execute();
            }


        } else if (o instanceof Receita) {
            Receita r = (Receita) o;

            if (r.getId() == null) {
                System.out.println("Iniciando Insert em Receita...");

                PreparedStatement ps = this.con.prepareStatement("insert into tb_receita "
                        + "(id, orientacao, consulta_id) values "
                        + "(nextval('seq_receita_id'), ?, ?)");

                ps.setString(1, r.getOrientacao());
                ps.setInt(2, r.getConsulta().getId());

                ps.executeUpdate();
            } else {
                System.out.println("Iniciando Update em Receita...");
                PreparedStatement ps = this.con.prepareStatement("update tb_receita set "
                        + "orientacao = ?, "
                        + "consulta_id = ? "
                        + "where id = ?");
                ps.setString(1, r.getOrientacao());
                ps.setInt(2, r.getConsulta().getId());
                ps.setInt(3, r.getId());

                ps.execute();
            }
        } else if (o instanceof Medico) {
            Medico m = (Medico) o;

            if (m.getData_cadastro_Medico() == null) {
                System.out.println("Iniciando Insert em Medico...");
                PreparedStatement ps = this.con.prepareStatement("insert into tb_medico "
                        + "(data_cadastro_medico, numero_crmv, cpf) values "
                        + "(now(),?, ?)");
                ps.setString(1, m.getNumero_crmv());
                ps.setString(2, m.getCpf());

                ps.executeUpdate();
            } else {
                System.out.println("Iniciando Update em Medico...");

                PreparedStatement ps = this.con.prepareStatement("update tb_medico set "
                        + "numero_crmv = ?, "
                        + "cpf = ? "
                        + "where data_cadastro_medico = ?");
                ps.setString(1, m.getNumero_crmv());
                ps.setString(2, m.getCpf());
                Date dtU = null;
                dtU.setTime(m.getData_cadastro().getTimeInMillis());
                ps.setDate(3, dtU);

                ps.execute();
            }
        }else if (o instanceof Pet) {

            Pet p = (Pet) o;

            if (p.getId() == null) {
                System.out.println("Iniciando Insert em Pet...");
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pet "
                        + "(id, data_nascimento, nome, observacao, cliente_id, raca_id) values "
                        + "(nextval('seq_pet_id'), ?, ?, ?, ?, ?)");
                Date dtU = new Date(System.currentTimeMillis());
                dtU.setTime(p.getData_nascimento().getTimeInMillis());
                ps.setDate(1, dtU);
                ps.setString(2, p.getNome());
                ps.setString(3, p.getObservacao());
                ps.setString(4, p.getCliente().getCpf());
                ps.setInt(5, p.getRaca().getId());


                ps.executeUpdate();
            } else {
                System.out.println("Iniciando Update em Pet...");

                PreparedStatement ps = this.con.prepareStatement("update tb_pet set "
                        + "data_nascimento = ?, "
                        + "nome = ?, "
                        + "observacao = ?, "
                        + "cliente_id = ?, "
                        + "raca_id = ? "
                        + "where id = ?");
                Date dtU = null;
                dtU.setTime(p.getData_nascimento().getTimeInMillis());
                ps.setDate(1, dtU);
                ps.setString(2, p.getNome());
                ps.setString(3, p.getObservacao());
                ps.setString(4, p.getCliente().getCpf());
                ps.setInt(5, p.getRaca().getId());
                ps.setInt(6, p.getId());

                ps.execute();
            }
        }else if (o instanceof Cliente) {
            Cliente c = (Cliente) o;

            if (c.getData_cadastro_Cliente() == null) {
                System.out.println("Iniciando Insert de Cliente...");
                PreparedStatement ps = this.con.prepareStatement("insert into tb_cliente "
                        + "(data_cadastro_cliente,data_ultima_visita, cpf) values "
                        + "(now(),?, ?)");
                Date dtU = new Date(System.currentTimeMillis());
                dtU.setTime(c.getData_ultima_visita().getTimeInMillis());
                ps.setDate(1, dtU);
                ps.setString(2, c.getCpf());

                ps.executeUpdate();
            } else {
                System.out.println("Iniciando Update de Cliente...");
                PreparedStatement ps = this.con.prepareStatement("update tb_cliente set "
                        + "data_ultima_visita = ? "
                        + "where data_cadastro_cliente = ?");
                Date dtU = new Date(System.currentTimeMillis());
                dtU.setTime(c.getData_ultima_visita().getTimeInMillis());
                ps.setDate(1, dtU);
                dtU.setTime(System.currentTimeMillis());
                ps.setDate(2, dtU);
                ps.execute();
            }
        }else if (o instanceof Funcionario) {
            Funcionario c = (Funcionario) o;

            if (c.getData_cadastro() == null) {
                System.out.println("Iniciando Insert de Pessoa...");
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa "
                        + "(data_cadastro, tipo, cpf, cep, complemento, data_nascimento, email, endereco, nome, numero_celular, rg, senha) values "
                        + "(now(),?,?,?,?,?,?,?,?,?,?,?)");

                ps.setString(1, c.getTipo());
                ps.setString(2, c.getCpf());
                ps.setString(3, c.getCep());
                ps.setString(4, c.getComplemento());
                Date dtU = new Date(System.currentTimeMillis());
                dtU.setTime(c.getData_nascimento().getTimeInMillis());
                ps.setDate(5, dtU);
                ps.setString(6, c.getEmail());
                ps.setString(7, c.getEndereco());
                ps.setString(8, c.getNome());
                ps.setString(9, c.getNumero_celular());
                ps.setString(10, c.getRg());
                ps.setString(11, c.getSenha());

                ps.executeUpdate();

                System.out.println("Iniciando Insert de Funcionario...");
                ps = this.con.prepareStatement("insert into tb_funcionario "
                        + "(cargo,numero_ctps, numero_pis, cpf) values "
                        + "(?,?,?,?)");
                ps.setString(1, c.getCargo().toString());
                ps.setString(2, c.getNumero_ctps());
                ps.setString(3, c.getNumero_pis());
                ps.setString(4, c.getCpf());

                ps.executeUpdate();
            } else {
                System.out.println("Inciando Update de Pessoa...");
                PreparedStatement ps = this.con.prepareStatement("update tb_pessoa set "
                        + "tipo = ?, "
                        + "cep = ?, "
                        + "complemento = ?, "
                        + "data_nascimento = ?, "
                        + "email = ?, "
                        + "endereco = ?, "
                        + "nome = ?, "
                        + "numero_celular = ?, "
                        + "rg = ?, "
                        + "senha = ?, "
                        + "data_cadastro = now()"
                        + "where cpf = ?");
                ps.setString(1, c.getTipo());
                ps.setString(2, c.getCep());
                ps.setString(3, c.getComplemento());
                Date dtU = new Date(System.currentTimeMillis());
                dtU.setTime(c.getData_nascimento().getTimeInMillis());
                ps.setDate(4, dtU);
                ps.setString(5, c.getEmail());
                ps.setString(6, c.getEndereco());
                ps.setString(7, c.getNome());
                ps.setString(8, c.getNumero_celular());
                ps.setString(9, c.getRg());
                ps.setString(10, c.getSenha());
                ps.setString(11, c.getCpf());
                ps.execute();

                System.out.println("Iniciando Update de Funcionario...");
                ps = this.con.prepareStatement("update tb_funcionario set "
                        + "cargo = ?, "
                        + "numero_ctps = ?, "
                        + "numero_pis = ? "
                        + "where cpf = ?");
                ps.setString(1, c.getCargo().toString());
                ps.setString(2, c.getNumero_ctps());
                ps.setString(3, c.getNumero_pis());
                ps.setString(4, c.getCpf());
                ps.execute();
            }
        }else if (o instanceof Raca) {

            Raca r = (Raca) o;

            if (r.getId() == null) {
                System.out.println("Iniciando Insert de Raca...");
                PreparedStatement ps = this.con.prepareStatement("insert into tb_raca "
                        + "(id, nome, especie_id) values "
                        + "(nextval('seq_raca_id'), ?, ?)");

                ps.setString(1, r.getNome());
                ps.setInt(2, r.getEspecie().getId());

                ps.executeUpdate();
            } else {
                System.out.println("Iniciando Update de Raca...");
                PreparedStatement ps = this.con.prepareStatement("update tb_raca set "
                        + "nome = ?, "
                        + "especie_id = ?, "
                        + "where id = ?");
                ps.setString(1, r.getNome());
                ps.setInt(1, r.getEspecie().getId());
                ps.setInt(1, r.getId());
                ps.execute();
            }
        }else if (o instanceof Especie) {

            Especie e = (Especie) o;

            if (e.getId() == null) {
                System.out.println("Iniciando Insert de Especie...");

                PreparedStatement ps = this.con.prepareStatement("insert into tb_especie "
                        + "(id, nome) values "
                        + "(nextval('seq_especie_id'), ?)");

                ps.setString(1, e.getNome());

                ps.executeUpdate();
            } else {
                System.out.println("Iniciando Update de Especie...");
                PreparedStatement ps = this.con.prepareStatement("update tb_especie set "
                        + "nome = ?, "
                        + "where id = ?");
                ps.setString(1, e.getNome());
                ps.setInt(1, e.getId());
                ps.execute();
            }
        }else if (o instanceof Pessoa) {
            Pessoa p = (Pessoa) o;

            if (testaId(o)) {
                System.out.println("Iniciando Insert de Pessoa...");
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa "
                        + "(data_cadastro, tipo, cpf, cep, complemento, data_nascimento, email, endereco, nome, numero_celular, rg, senha) values "
                        + "(now(),?,?,?,?,?,?,?,?,?,?,?)");

                ps.setString(1, p.getTipo());
                ps.setString(2, p.getCpf());
                ps.setString(3, p.getCep());
                ps.setString(4, p.getComplemento());
                Date dtU = new Date(System.currentTimeMillis());
                dtU.setTime(p.getData_nascimento().getTimeInMillis());
                ps.setDate(5, dtU);
                ps.setString(6, p.getEmail());
                ps.setString(7, p.getEndereco());
                ps.setString(8, p.getNome());
                ps.setString(9, p.getNumero_celular());
                ps.setString(10, p.getRg());
                ps.setString(11, p.getSenha());


                ps.executeUpdate();
            } else {
                System.out.println("Inciando Update de Pessoa...");
                PreparedStatement ps = this.con.prepareStatement("update tb_pessoa set "
                        + "tipo = ?, "
                        + "cep = ?, "
                        + "complemento = ?, "
                        + "data_nascimento = ?, "
                        + "email = ?, "
                        + "endereco = ?, "
                        + "nome = ?, "
                        + "numero_celular = ?, "
                        + "rg = ?, "
                        + "senha = ?, "
                        + "data_cadastro = now()"
                        + "where cpf = ?");
                ps.setString(1, p.getTipo());
                ps.setString(2, p.getCep());
                ps.setString(3, p.getComplemento());
                Date dtU = new Date(System.currentTimeMillis());
                dtU.setTime(p.getData_nascimento().getTimeInMillis());
                ps.setDate(4, dtU);
                ps.setString(5, p.getEmail());
                ps.setString(6, p.getEndereco());
                ps.setString(7, p.getNome());
                ps.setString(8, p.getNumero_celular());
                ps.setString(9, p.getRg());
                ps.setString(10, p.getSenha());
                ps.setString(11, p.getCpf());
                ps.execute();
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if (o instanceof Consulta) {
            Consulta c = (Consulta) o;
            PreparedStatement ps1 = this.con.prepareStatement("update tb_receita set consulta_id=Null where consulta_id = ?");
            ps1.setInt(1, c.getId());
            ps1.execute();

            PreparedStatement ps2 = this.con.prepareStatement("delete from tb_consulta where id = ?");
            ps2.setInt(1, c.getId());
            ps2.execute();

        } else if (o instanceof Receita) {
            Receita r = (Receita) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_receita where id = ?");
            ps.setInt(1, r.getId());
            ps.execute();

        } else if (o instanceof Medico) {
            Medico m = (Medico) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_medico where cpf = ?");
            ps.setString(1, m.getCpf());
            ps.execute();

        } else if (o instanceof Cliente) {
            Cliente c = (Cliente) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_cliente where cpf = ?");
            ps.setString(1, c.getCpf());
            ps.execute();

        } else if (o instanceof Pet) {
            Pet p = (Pet) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_pet where id = ?");
            ps.setInt(1, p.getId());
            ps.execute();

        } else if (o instanceof Raca) {
            Raca r = (Raca) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_raca where id = ?");
            ps.setInt(1, r.getId());
            ps.execute();

        } else if (o instanceof Especie) {
            Especie e = (Especie) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_especie where id = ?");
            ps.setInt(1, e.getId());
            ps.execute();

        }else if (o instanceof Funcionario) {
            Funcionario f = (Funcionario) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_funcionario where cpf = ?");
            ps.setString(1, f.getCpf());
            ps.execute();

            PreparedStatement psPessoa = this.con.prepareStatement("delete from tb_pessoa where cpf = ?");
            psPessoa.setString(1, f.getCpf());
            psPessoa.execute();

        }
    }

    public List<Receita> listReceitasDeConsulta(Consulta con) throws Exception {

        List<Receita> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select id, orientacao, consulta_id from tb_receita where consulta_id = ?");
        ps.setInt(1, con.getId());

        ResultSet rs = ps.executeQuery();

        lista = new ArrayList<>();
        while (rs.next()) {

            Receita rec = new Receita();
            rec.setId(rs.getInt("id"));
            rec.setOrientacao(rs.getString("orientacao"));
            rec.setConsulta(con);
            lista.add(rec);

        }
        return lista;

    }

    public List<Consulta> listPesistenciaConsulta() throws Exception {

        List<Consulta> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select id, medico_id, pet_id from tb_consulta");

        ResultSet rs = ps.executeQuery();

        lista = new ArrayList<>();
        while (rs.next()) {
            Consulta con = new Consulta();
            con.setId(rs.getInt("id"));
            con.setMedico((Medico) find(Medico.class, rs.getString("medico_id")));
            con.setPet((Pet) find(Pet.class, rs.getInt("pet_id")));
            con.setReceitas(listReceitasDeConsulta(con));
            lista.add(con);

        }
        return lista;

    }

    //Método usado para testar se o CPF de teste já existe na entidade Pessoa
    public boolean testaId(Object o) throws Exception{
        Pessoa p = (Pessoa) o;
        PreparedStatement psTest = this.con.prepareStatement("select cpf from tb_pessoa");

        ResultSet rs = psTest.executeQuery();

        while (rs.next()) {
            if (p.getCpf().equals(rs.getString("cpf")))
                return false;
        }

        return true;

    }

    //Método auxiliar para encontrar o último registro, usado para testes
    public Object ultimoId(Class c) throws Exception{

        if (c == Consulta.class) {

            PreparedStatement ps = this.con.prepareStatement("select id from tb_consulta order by id desc limit 1");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                ps.close();

                return id;
            }

        } else if (c == Medico.class) {
            PreparedStatement ps = this.con.prepareStatement("select cpf from tb_medico order by cpf desc limit 1");
            ResultSet rsMedico = ps.executeQuery();
            if (rsMedico.next()) {
                String id = rsMedico.getString("cpf");
                ps.close();

                return id;
            }
        } else if (c == Pet.class) {
            PreparedStatement ps = this.con.prepareStatement("select id from tb_pet order by id desc limit 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                ps.close();

                return id;
            }

        } else if (c == Cliente.class) {
            PreparedStatement ps = this.con.prepareStatement("select cpf from tb_cliente order by cpf desc limit 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("cpf");
                ps.close();

                return id;
            }
        } else if (c == Raca.class) {
            PreparedStatement ps = this.con.prepareStatement("select id from tb_raca order by id desc limit 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                ps.close();

                return id;
            }
        } else if (c == Especie.class) {
            PreparedStatement ps = this.con.prepareStatement("select id from tb_especie order by id desc limit 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                ps.close();

                return id;
            }
        } else if (c == Receita.class) {
            PreparedStatement ps = this.con.prepareStatement("select id from tb_receita order by id desc limit 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                ps.close();

                return id;
            }
        }
        return null;
    }

    public Funcionario doLogin(String cpf, String senha) throws Exception {
        Funcionario funcionario = null;

        PreparedStatement psCpf =
                this.con.prepareStatement("select p.cpf from tb_funcionario p where p.cpf = ?");

        psCpf.setString(1, cpf);

        ResultSet rsCpf = psCpf.executeQuery();
        funcionario = new Funcionario();
        if(rsCpf.next()){
            funcionario.setCpf(rsCpf.getString("cpf"));
            System.out.println(funcionario.getCpf());
        }
        psCpf.close();

        PreparedStatement psSenha =
                this.con.prepareStatement("select p.senha from tb_pessoa p where p.senha = ? ");

        psSenha.setString(1, senha);

        ResultSet rsSenha = psSenha.executeQuery();

        if(rsSenha.next()){
            funcionario.setSenha(rsSenha.getString("senha"));
            System.out.println(funcionario.getSenha());
        }

        psSenha.close();
        return funcionario;

    }

    public List<Funcionario> listFuncionario() throws SQLException {
        List<Funcionario> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select cargo, numero_ctps, numero_pis, cpf from tb_funcionario");
        ResultSet rs = ps.executeQuery();

        lista = new ArrayList<>();
        while (rs.next()) {
            Funcionario func = new Funcionario();
            func.setCpf(rs.getString("cpf"));
            func.setCargo(Cargo.valueOf(rs.getString("cargo").toUpperCase()));
            func.setNumero_ctps(rs.getString("numero_ctps"));
            func.setNumero_pis(rs.getString("numero_pis"));

            PreparedStatement psPessoa = this.con.prepareStatement("select rg, nome, senha, numero_celular, email, data_cadastro, data_nascimento, cep, endereco, complemento from tb_pessoa where cpf = ?");

            psPessoa.setString(1, func.getCpf());
            ResultSet rsPessoa = psPessoa.executeQuery();

            if (rsPessoa.next()) {
                func.setRg(rsPessoa.getString("rg"));
                func.setNome(rsPessoa.getString("nome"));
                func.setSenha(rsPessoa.getString("senha"));
                func.setNumero_celular(rsPessoa.getString("numero_celular"));
                func.setEmail(rsPessoa.getString("email"));
                Calendar dc = Calendar.getInstance();
                dc.setTimeInMillis(rsPessoa.getDate("data_cadastro").getTime());
                func.setData_cadastro(dc);
                Calendar dn = Calendar.getInstance();
                dn.setTimeInMillis(rsPessoa.getDate("data_nascimento").getTime());
                func.setData_nascimento(dn);
                func.setCep(rsPessoa.getString("cep"));
                func.setEndereco(rsPessoa.getString("endereco"));
                func.setComplemento(rsPessoa.getString("complemento"));

                psPessoa.close();
            }
            lista.add(func);
        }
        ps.close();
        return lista;
    }

    public List<Medico> listMedicos() throws SQLException {
        List<Medico> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select data_cadastro_medico, numero_crmv, cpf from tb_medico");

        ResultSet rs = ps.executeQuery();

        lista = new ArrayList<>();
        while (rs.next()) {
            Medico med = new Medico();
            med.setCpf(rs.getString("cpf"));
            med.setNumero_crmv(rs.getString("numero_crmv"));
            Calendar dtU = Calendar.getInstance();
            dtU.setTimeInMillis(rs.getDate("data_cadastro_medico").getTime());
            med.setData_cadastro_Medico(dtU);

            lista.add(med);
        }
        ps.close();
        return lista;
    }

    public List<Pet> listPets() throws Exception {
        List<Pet> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select id, data_nascimento, nome, observacao, cliente_id, raca_id from tb_pet");

        ResultSet rs = ps.executeQuery();

        lista = new ArrayList<>();
        while (rs.next()) {
            Pet p = new Pet();
            p.setId(rs.getInt("id"));
            Calendar dtU = Calendar.getInstance();
            dtU.setTimeInMillis(rs.getDate("data_nascimento").getTime());
            p.setData_nascimento(dtU);
            p.setNome(rs.getString("nome"));
            p.setObservacao(rs.getString("observacao"));
            p.setCliente((Cliente) find(Cliente.class, rs.getString("cliente_id")));
            p.setRaca((Raca) find(Raca.class, rs.getInt("raca_id")));

            lista.add(p);
        }
        ps.close();
        return lista;
    }

    public List<Receita> listReceitas() throws Exception {
        List<Receita> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select id, orientacao, consulta_id from tb_receita");

        ResultSet rs = ps.executeQuery();

        lista = new ArrayList<>();
        while (rs.next()) {
            Receita r = new Receita();
            r.setId(rs.getInt("id"));
            r.setOrientacao(rs.getString("orientacao"));
            r.setConsulta((Consulta) find(Consulta.class, rs.getInt("consulta_id")));

            lista.add(r);
        }
        ps.close();
        return lista;
    }
}
package br.edu.ifsul.cc.lpoo.clinicaVet.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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

        return null;
    }

    @Override
    public void persist(Object o) throws Exception {

    }

    @Override
    public void remover(Object o) throws Exception {

    }
}
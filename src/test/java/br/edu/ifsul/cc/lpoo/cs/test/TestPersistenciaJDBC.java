package br.edu.ifsul.cc.lpoo.cs.test;

import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;

public class TestPersistenciaJDBC {

    //@Test
    public void testConexao() throws Exception {

        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JDBC");

            persistencia.fecharConexao();

        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }

    }

}
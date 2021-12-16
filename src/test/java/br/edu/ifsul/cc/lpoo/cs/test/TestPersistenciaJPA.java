package br.edu.ifsul.cc.lpoo.cs.test;

import br.edu.ifsul.cc.lpoo.clinicaVet.model.dao.PersistenciaJPA;
import java.util.List;
import org.junit.Test;

public class TestPersistenciaJPA {

    @Test
    public void testConexaoGeracaoTabelas(){

        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");

            persistencia.fecharConexao();

        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }

    }

}
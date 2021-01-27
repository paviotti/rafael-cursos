package dao;

import beans.Curso;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CursoDAO {

    private Conexao conexao;
    private Connection conn;
     

    //construtor que instancia a classe
    public CursoDAO() {
        this.conexao = new Conexao(); //instancia quando a classe é chamada
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Curso curso) {
        String sql = "INSERT INTO cursos(nome, nivel, duracao) VALUES " //ESPAÇO DEPOIS DE VALUES
                + "(?, ?, ?)"; //evita o sql injection
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, curso.getNomecurso());
            stmt.setString(2, curso.getNivel());
            stmt.setInt(3, curso.getDuracao());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao inserir curso" + e.getMessage());
        }

    }
    
    public Curso getCurso(int id){ //pega um curso à partir de um id
        
        String sql = "SELECT * FROM CURSOS WHERE id = ?";
        try {
            
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery(); //traz uma query e grava em ResultSet
            Curso curso = new Curso();
             System.out.println("Até aqui vai");
            //pegando os dados
            rs.first(); //posiciona no ínicio da tabela
             System.out.println("Aqui não vai");
            curso.setId(id); //escreve como esta no banco
            System.out.println(" teste: " + curso.getId()); //não executa
            curso.setNomecurso(rs.getString("nome"));
            curso.setNivel(rs.getString("nivel"));
            curso.setDuracao(rs.getInt("duracao")); 
            return curso; 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null; //se retornar nulo, coloca um alerta na view
        }
        
    }

}

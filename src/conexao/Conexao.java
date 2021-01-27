package conexao;

/* esta classe cria a conexão com o BD
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public Connection getConexao() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/projetojava?serverTimezone=UTC&useSSL=false", //linha de conexão + timezone
                "root", //usuario do mysql
                "567809" //senha do mysql
            );    
        } catch (Exception e) {
            System.out.println("Erro ao conectar" + e.getMessage());  
        }
        return conn;
    }
}

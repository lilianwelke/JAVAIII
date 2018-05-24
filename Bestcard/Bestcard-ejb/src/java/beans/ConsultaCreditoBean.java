package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author lw005973
 */
@Stateless // anotação: passa um comportamento/diretiva da classe para o container (glassfish) ou a vm
// Stateless -> indica que o tipo do ejb (é sem estado)
public class ConsultaCreditoBean implements ConsultaCreditoBeanRemote, ConsultaCreditoBeanLocal {

    public double getCredito(int cliente_id) { 
        double credito = 0.0;
        try {            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/bestcard", "sa", "sa");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM CLIENTE WHERE ID=?");
            ps.setInt(1, cliente_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { 
                credito = rs.getDouble("valor_credito");
            }
            
        } catch (ClassNotFoundException ex) {
            System.out.println(""+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(""+ex.getMessage());
        }
        
        return credito;
    }
    
}

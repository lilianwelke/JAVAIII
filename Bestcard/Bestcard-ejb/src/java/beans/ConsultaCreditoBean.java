package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
            System.out.println("" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }

        return credito;
    }

    public boolean registrarCompra(int compras_id, int cliente_id, Date compra_data, String loja_nome, double compra_valor) {
        double saldo = 0.0;
        if (getCredito(cliente_id) >= compra_valor) {            
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/bestcard", "sa", "sa");
                PreparedStatement ps = con.prepareStatement("INSERT INTO COMPRAS (ID_COMPRAS, ID_CLIENTE, DATA_COMPRA, NOME_LOJA, VALOR_COMPRA) VALUES (?, ?, ?, ?, ?)");
                
                ps.setInt(1, compras_id);
                ps.setInt(2, cliente_id);
                ps.setDate(3, new java.sql.Date (compra_data.getTime()));
                ps.setString(4, loja_nome);
                ps.setDouble(5, compra_valor);               
                ps.execute();
                ps.close();
                
                PreparedStatement p = con.prepareStatement("UPDATE CLIENTE SET VALOR_CREDITO=? WHERE ID=?");
                
                saldo = getCredito(cliente_id) - compra_valor;
                
                p.setDouble(1, saldo);
                p.setInt(2, cliente_id);
                p.execute();
                
            } catch (ClassNotFoundException ex) {
                System.out.println("" + ex.getMessage());
            } catch (SQLException ex) {
                System.out.println("" + ex.getMessage());
            }
            return true;
        } else {
            return false;
        }
    }
}
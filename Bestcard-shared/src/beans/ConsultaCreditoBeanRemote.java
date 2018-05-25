package beans;

import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author lw005973
 */
@Remote
public interface ConsultaCreditoBeanRemote {
    public double getCredito(int cliente_id);
    public boolean registrarCompra(int compras_id, int cliente_id, Date compra_data, String loja_nome, double compra_valor);
}

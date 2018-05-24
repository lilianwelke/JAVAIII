package beans;

import javax.ejb.Remote;

/**
 *
 * @author lw005973
 */
@Remote
public interface ConsultaCreditoBeanRemote {
    public double getCredito(int cliente_id);
}

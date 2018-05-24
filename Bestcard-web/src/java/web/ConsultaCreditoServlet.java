package web;

import beans.ConsultaCreditoBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.lang.Integer.parseInt;
import javax.ejb.EJB;

/**
 *
 * @author lw005973
 */
@WebServlet(name = "ConsultaCreditoServlet", urlPatterns = {"/consulta-credito"})
public class ConsultaCreditoServlet extends HttpServlet {

    @EJB
    private ConsultaCreditoBeanRemote bean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter saida = response.getWriter();
        
        int client_id = parseInt (request.getParameter("id"));
        saida.write("O crédito do cliente é: " + bean.getCredito(client_id));
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}

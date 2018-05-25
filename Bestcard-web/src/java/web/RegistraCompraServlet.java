package web;

import beans.ConsultaCreditoBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lw005973
 */
public class RegistraCompraServlet extends HttpServlet {
    
    @EJB
    private ConsultaCreditoBeanRemote bean;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter conteudo = response.getWriter();
        int id_compra = parseInt (request.getParameter("id_compra"));
        int id_cliente = parseInt (request.getParameter("id_cliente"));        
        Date data_compra = new Date();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy"); 
        try {
            data_compra = dataFormatada.parse(request.getParameter("data_compra"));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        String nome_loja = request.getParameter("nome_loja");
        double valor_compra = parseDouble (request.getParameter("valor_compra"));
        
        if (bean.registrarCompra(id_compra, id_cliente, data_compra, nome_loja, valor_compra)) { 
            conteudo.write("Compra realizada com sucesso!");
        } else { 
            conteudo.write("Falha!");
        }
        
    }    

}

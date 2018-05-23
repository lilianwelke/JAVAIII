package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lw005973
 */
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        
        PrintWriter saida = resp.getWriter();
        saida.write("<html>");
        //saida.write("Hoje é: " + new Date());
        
        String nome = req.getParameter("nome");
        saida.write("O nome digitado é: " + nome); //?nome=lilian
        
        saida.write("</html>");
    }    

}

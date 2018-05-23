package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;

/**
 *
 * @author lw005973
 */
public class MercadoriasServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        resp.setContentType("text/html");
        PrintWriter saida = resp.getWriter();
        Produto a = new Produto("Feijão", 7.00);
        Produto b = new Produto("Fanta", 6.00);
        Produto c = new Produto("Bife", 14.50);
        
        List<Produto> lista = new ArrayList<>();
        lista.add(a); lista.add(b); lista.add(c);
        
        saida.println("<b>Lista de Produtos:</b> <br><br>");
        
        String pesquisa = req.getParameter("pesquisa");
        String preco = req.getParameter("preco");
                
        if (pesquisa == null) { 
            pesquisa = "";
        }
        
        if (preco == null) { 
            preco = "";            
        }
                
        for(Produto item : lista) {
            if (item.getNome().contains(pesquisa)) {
                saida.println(item.getNome());
                if (preco.equals("sim")) {
                    saida.println(" - " + item.getPreco());
                }
            saida.println("<br>");
            }                         
        } 
    }    
}

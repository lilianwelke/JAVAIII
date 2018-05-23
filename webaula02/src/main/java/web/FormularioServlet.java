package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lw005973
 */
public class FormularioServlet extends HttpServlet {

    private int numAcessos = 0;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String clienteCpf = req.getParameter("cpf");
        String clienteNome = req.getParameter("nome");
        
        resp.setContentType("text/html;charset-UTF-8");
        
        numAcessos++;
        
        PrintWriter conteudo = resp.getWriter();
        conteudo.write("<html> \n");
        conteudo.write("<p> CPF: " + clienteCpf + "</p>\n");
        conteudo.write("<p> Nome: " + clienteNome + "</p>\n");
        conteudo.write("<p> Acessos: " + this.numAcessos + "</p>\n");
        conteudo.write("</html>");
        
    }
    
}
/* 
    get > envia pouca (url) coisa e recebe muita (pagina)
    post > envia/processa muitos dados do navegador para o servidor 
    primeira vez que o servlet é acessado ele cria um objeto que só é reiniciado se o servidor for reiniciado
    todos os usuários compartilham a mesma aplicação
*/
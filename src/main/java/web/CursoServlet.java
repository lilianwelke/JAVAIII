package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lw005973
 */
public class CursoServlet extends HttpServlet {
    
    private static Connection connection;
     
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //para escrever no navegador 
        resp.setContentType("text/html");
        PrintWriter conteudo = resp.getWriter();
        
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver"); 
            Connection connection = DriverManager.getConnection("jdbc:firebirdsql:192.168.21.154/3050:/databases/erpcolegio.fdb?encoding=WIN1252", "SYSDBA", "masterkey");
            
            PreparedStatement p = connection.prepareStatement("SELECT * FROM CURSO");
            ResultSet rs = p.executeQuery();
            conteudo.write("<html><ul>");
            while (rs.next()) { 
                conteudo.write ("<li>" + rs.getString("nome_curso") + "</li>");
            }
            conteudo.write("</ul></html>");
        } catch (SQLException ex) {
            throw new ServletException (ex);
        }   catch (ClassNotFoundException ex) {
            Logger.getLogger(CursoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //para inserir no banco de dados 
        resp.setContentType("text/html");
        PrintWriter conteudo = resp.getWriter();
        
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver"); 
            Connection connection = DriverManager.getConnection("jdbc:firebirdsql:192.168.21.154/3050:/databases/erpcolegio.fdb?encoding=WIN1252", "SYSDBA", "masterkey");
            
            PreparedStatement p = connection.prepareStatement("INSERT INTO CURSO (NOME_CURSO, TURNO, QTD_ESTUDANTES, DATA_CADASTRO) VALUES (?, ?, ?, ?)");
            
            p.setString(1, req.getParameter("nome_curso"));
            p.setString(2, req.getParameter("turno"));
            p.setString(3, req.getParameter("qtd_estudantes"));
            p.setString(4, req.getParameter("data_cadastro"));
            p.execute();            
        } catch (SQLException ex) {
            throw new ServletException (ex);
        }   catch (ClassNotFoundException ex) {
            Logger.getLogger(CursoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        conteudo.write("Concluído!");
               
    }   
}

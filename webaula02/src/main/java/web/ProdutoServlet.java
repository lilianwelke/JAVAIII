package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lw005973
 */
public class ProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter saida = resp.getWriter();

        List<String> lista = new ArrayList<>();
        lista.add("Pepsi");
        lista.add("Guaraná");
        lista.add("Pastel Frito");

        //saida.write("" + lista.get(0));

        List<String> listaPreco = new ArrayList<>();
        listaPreco.add("R$ 5,00");
        listaPreco.add("R$ 4,00");
        listaPreco.add("R$ 3,00");
        
        String pesquisa = req.getParameter("pesquisa");
        String preco = req.getParameter("preco");

        if (pesquisa == null) {
            pesquisa = "";
        }
        if (preco == null) {
            preco = "";
        }

        for (int i = 0; i < lista.size(); i++) {
            if ((lista.get(i).contains(pesquisa)) && (preco.equals("sim"))) {
                saida.write(lista.get(i) + " " + listaPreco.get(i) + "<br>");
            } else if (lista.get(i).contains(pesquisa)) {
                saida.write(lista.get(i) + "<br>");
            }  
        }              
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String nomeCliente = req.getParameter("nomeCliente");
        String cnpj = req.getParameter("cnpj");
        String endereco = req.getParameter("endereco");
        String nomeProduto = req.getParameter("nomeProduto");
        String qtdProduto = req.getParameter("qtdProduto");
        String valorProduto = req.getParameter("valorProduto");
        double valorTotal = Double.parseDouble(qtdProduto) * Double.parseDouble(valorProduto);
        
        resp.setContentType("text/html;charset-UTF-8");
        
        PrintWriter conteudo = resp.getWriter();
        conteudo.write("<html> \n");
        conteudo.write("<p> Nome do Cliente: " + nomeCliente + "</p>\n");
        conteudo.write("<p> CNPJ: " + cnpj + "</p>\n");
        conteudo.write("<p> Endereço: " + endereco + "</p>\n");
        conteudo.write("<p> Nome do Produto: " + nomeProduto + "</p>\n");
        conteudo.write("<p> Quantidade do Produto: " + qtdProduto + "</p>\n");
        conteudo.write("<p> Valor do Produto: " + valorProduto + "</p>\n");        
        conteudo.write("<p> Valor Total do Produto: " + valorTotal + "</p>\n");
        conteudo.write("</html>");
        
    }
    
}

/*
Server.UrlEncode(pesquisa);
Server.UrlDecode(pesquisa);
for (int i = 0; i < lista.size(); i++) {
            listaNova.add(lista.get(i));
            listaNova.add(listaPreco.get(i));            
        }

for (String produ : lista

    
        ) {
                    lista.add(lista.get());
        lista.add(listaPreco.get(produ));
    }

    saida.write (prod 

+ "<br>");


for (String prod : lista) {
            for (String prec : listaNova) {
                if (precos == "sim") {
                    if (prod.contains(pesquisa)) {
                        saida.write(prec + "<br>");
                    }
                } else if (prod.contains(pesquisa)) {
                    saida.write(prod + "<br>");
                }
            }
        }

for (String prec : listaNova) {
                
                    if (prec.contains(pesquisa)) {
                        saida.write(prec + "<br>");
                    }
                
            }


for (String prod : lista) {
            
                if ((prod.contains(pesquisa)) && (precos != "sim")) {
                    saida.write(prod + "<br>");
                } else if ((prod.contains(pesquisa)) && (precos == "sim")) {
                    saida.write(prod + listaPreco + "<br>");
                }
            
        }
for (String prec : listaNova) {

            if (prec.contains(pesquisa)) {
                saida.write(prec + "<br>");
            }

            if ((precos) == "sim") {          
                saida.write(prec + "<br>");
            }                
        }

*/

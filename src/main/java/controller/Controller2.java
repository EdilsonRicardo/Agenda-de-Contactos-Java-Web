package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.DAO;
import model.JavaBeans;

@WebServlet(name = "Controller2", urlPatterns= {"/Controller2", "/main", "/insert" })

public class Controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contacto = new JavaBeans();

	public Controller2() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// TEste de conexao
		// dao.testeCon();
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			// Metodo para lista contactos
			contactos(request, response);
		}else {
			if (action.equals("/insert")) {
				novoContacto(request, response);
			}else {
				response.sendRedirect("index.html");
			}
		}
	}

	// Listar Contactos
	protected void contactos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Redireccionar requisicao para o documento agenda.jsp
		response.sendRedirect("agenda.jsp");
	}
	
	protected void novoContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("telefone"));
		System.out.println(request.getParameter("email"));
		
		//Setar variaveis javabeans
		contacto.setNome(request.getParameter("nome"));
		contacto.setNome(request.getParameter("telefone"));
		contacto.setNome(request.getParameter("email"));
	}

}

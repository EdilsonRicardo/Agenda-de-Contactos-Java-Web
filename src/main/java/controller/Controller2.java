package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		//response.sendRedirect("agenda.jsp");
		//Criacao de objecto que recebra dados da classe JavaBeans por um array
		ArrayList<JavaBeans> lista = dao.listarContactos();
		//Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contactos", lista); //setar atributo do documento jsp com a lista
		//Despachar a lista ao documento jsp
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
		
		
		
		
		/*
		 * //teste de recebimento da lista
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getIdcon());
			System.out.println(lista.get(i).getNome());
			System.out.println(lista.get(i).getTelefone());
			System.out.println(lista.get(i).getEmail());
		}*/
	}
	
	protected void novoContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* 
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("telefone"));
		System.out.println(request.getParameter("email"));*/
		
		//Setar variaveis javabeans
		contacto.setNome(request.getParameter("nome"));
		contacto.setTelefone(request.getParameter("telefone"));
		contacto.setEmail(request.getParameter("email"));
		
		// Invocar o metodo inserirContacto passando o objecto contacto
		dao.inserirContacto(contacto);
		//Redireccionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

}

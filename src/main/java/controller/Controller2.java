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

@WebServlet(name = "Controller2", urlPatterns = { "/Controller2", "/main", "/insert", "/select", "/update" })

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
		} else {
			if (action.equals("/insert")) {
				novoContacto(request, response);
			} else {
				if (action.equals("/select")) {
					listarContacto(request, response);
				} else {
					if (action.equals("/update")) {
						editarContacto(request, response);
					}else {
						response.sendRedirect("index.html");
					}
				}
			}
		}
	}

	// Listar Contactos
	protected void contactos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Redireccionar requisicao para o documento agenda.jsp
		// response.sendRedirect("agenda.jsp");
		
		// Criacao de objecto que recebra dados da classe JavaBeans por um array
		ArrayList<JavaBeans> lista = dao.listarContactos();
		
		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contactos", lista); // setar atributo do documento jsp com a lista
		
		// Despachar a lista ao documento jsp
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

		/*
		 * //teste de recebimento da lista for (int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getIdcon());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getTelefone());
		 * System.out.println(lista.get(i).getEmail()); }
		 */
	}

	protected void novoContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("telefone"));
		 * System.out.println(request.getParameter("email"));
		 */

		// Setar variaveis javabeans
		contacto.setNome(request.getParameter("nome"));
		contacto.setTelefone(request.getParameter("telefone"));
		contacto.setEmail(request.getParameter("email"));

		// Invocar o metodo inserirContacto passando o objecto contacto
		dao.inserirContacto(contacto);
		// Redireccionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	// Editar Contacto
	protected void listarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Recebimento do id do contacto que sera editado
		String idcon = request.getParameter("idcon");
		//System.out.println(idcon);
		
		//setar variavel JavaBeans
		contacto.setIdcon(Integer.parseInt(idcon));
		//Executar metodo seleccionar contacto
		dao.seleccionarContacto(contacto);
		//teste de recebimento
		//System.out.println(contacto.getIdcon());
		//System.out.println(contacto.getNome());
		
		//Setar os atributos do formulario com os atrubutos do JavaBeans (passagem de dados da classe javabeans(model) para controller
		request.setAttribute("idcon", contacto.getIdcon());
		request.setAttribute("nome", contacto.getNome());
		request.setAttribute("telefone", contacto.getTelefone());
		request.setAttribute("email", contacto.getEmail());
		
		//Encaminhar ao documento jsp (os dados a serem encaminhados sao os "get's" do contacto. passagem de dados da classe controller para view 
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	protected void editarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Teste de recebimento de dados do formulario de contactos
		/*System.out.println(request.getParameter("idcon"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("telefone"));
		System.out.println(request.getParameter("email"));*/
		
		
		//setar as variaveis JavBeans 
		//A parte do codigo request.getParameter("idcon"), faz captura de dado vindo do formulario
		contacto.setIdcon(Integer.parseInt(request.getParameter("idcon")));
		contacto.setNome(request.getParameter("nome"));
		contacto.setTelefone(request.getParameter("telefone"));
		contacto.setEmail(request.getParameter("email"));
		//executar o metodo de alteracao de contacto
		dao.alterarContacto(contacto);
		//redireccionar de forma directa ao documento agenda.jsp (com alteracoes actualizadas)
		response.sendRedirect("main");
	}
	
}

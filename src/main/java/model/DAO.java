package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/* Modulo de conexao */

	// Parametros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";

	// Metodo de conexao
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/* CRUD CREATE */
	public void inserirContacto(JavaBeans contacto) {
		String sql = "insert into contactos (nome, telefone, email) values (?,?,?)";
		try {
			// Abrir a conexao
			Connection con = conectar();
			// Preparar a query para execucao no Banco de dados
			PreparedStatement pst = con.prepareStatement(sql);
			// Substituir os parametros (?) pelo conteudo das variaveis JavaBeans
			pst.setString(1, contacto.getNome());
			pst.setString(2, contacto.getTelefone());
			pst.setString(3, contacto.getEmail());
			// Executar a query
			pst.executeUpdate();
			// Fechar a conexao do banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/* CRUD READ */
	public ArrayList<JavaBeans> listarContactos() {
		// Criacao de objecto para acessar a classe Javabeans
		ArrayList<JavaBeans> contactos = new ArrayList<>();
		String sql = "select * from contactos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			// Laco s=executado enquanto houver contactos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				int idcon = Integer.parseInt(rs.getString(1));
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);
				// Preenchendo o arraylist
				contactos.add(new JavaBeans(nome, telefone, email, idcon));
			}
			con.close();
			return contactos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// TESTE DE CONEXAO
	public void testeCon() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}

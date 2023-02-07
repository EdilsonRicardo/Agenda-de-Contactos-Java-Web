package model;

public class JavaBeans {
	private String nome, email, telefone;
	private int idcon;
	
	
	
	public JavaBeans(String nome, String email, String telefone, int idcon) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.idcon = idcon;
	}
	
	public JavaBeans() {
		super();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getIdcon() {
		return idcon;
	}
	public void setIdcon(int idcon) {
		this.idcon = idcon;
	}
	
}

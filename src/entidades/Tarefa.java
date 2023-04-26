package entidades;

public class Tarefa {
	private int id;
	private String descricao;
	private int prioridade;
	
	public Tarefa(String descricao, int prioridade) {
		this.descricao = descricao;
		this.prioridade = prioridade;
	}
	
	public Tarefa(int id, String descricao, int prioridade) {
		this.id = id;
		this.descricao = descricao;
		this.prioridade = prioridade;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getPrioridade() {
		return prioridade;
	}
	
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
}

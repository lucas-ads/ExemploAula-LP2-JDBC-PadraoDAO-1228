package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Tarefa;

public class DaoTarefa {

	public Connection getConexao() throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/tasksdb1228";
		String username = "root";
		String senha = "";
		
		Connection conexao = DriverManager.getConnection(dbURL, username, senha);
		return conexao;
	}
	
	public boolean inserir(Tarefa tarefa) throws SQLException {
		
		Connection conexao = this.getConexao();
		
		String sql = "insert into tarefas (descricao, prioridade) values(? , ?);";
		PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, tarefa.getDescricao());
		ps.setInt(2, tarefa.getPrioridade());

		int linhasAfetadas = ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			tarefa.setId(id);
		}
		
		return (linhasAfetadas == 1 ? true : false);
	}

	public boolean atualizar(Tarefa tarefa) throws SQLException {
		Connection con = this.getConexao();
		
		String sql = "update tarefas set descricao = ?, prioridade = ? where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, tarefa.getDescricao());
		ps.setInt(2, tarefa.getPrioridade());
		ps.setInt(3, tarefa.getId());
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean excluir(int id) throws SQLException {
		Connection con = this.getConexao();
		
		String sql = "delete from tarefas where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		if( ps.executeUpdate() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public Tarefa buscar(int idBuscado) throws SQLException {
		
		Connection con = this.getConexao();
		
		String sql = "select * from tarefas where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idBuscado);
		
		ResultSet result = ps.executeQuery();
		
		Tarefa tarefa = null;
		
		if( result.next() ) {
			int id = result.getInt("id");
			String descricao = result.getString("descricao");
			int prioridade = result.getInt("prioridade");
			
			tarefa = new Tarefa(id, descricao, prioridade);
		}
		
		return tarefa;
	}

	public List<Tarefa> buscarTodas() throws SQLException {
		Connection con = this.getConexao();
		
		String sql = "select * from tarefas";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		while( result.next() ) {
			int id = result.getInt("id");
			String descricao = result.getString("descricao");
			int prioridade = result.getInt("prioridade");
			
			Tarefa t = new Tarefa(id, descricao, prioridade);
	
			tarefas.add(t);
		}
		
		return tarefas;
	}

	public List<Tarefa> pesquisarPorDescricao(String texto) throws SQLException {
		Connection con = this.getConexao();
		
		String sql = "select * from tarefas where descricao like ? ";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "%"+texto+"%");
		
		ResultSet result = ps.executeQuery();
		
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		while( result.next() ) {
			int id = result.getInt("id");
			String descricao = result.getString("descricao");
			int prioridade = result.getInt("prioridade");
			
			Tarefa t = new Tarefa(id, descricao, prioridade);
	
			tarefas.add(t);
		}
		
		return tarefas;
	}
}

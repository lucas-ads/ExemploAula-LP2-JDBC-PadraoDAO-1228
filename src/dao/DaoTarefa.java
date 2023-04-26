package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entidades.Tarefa;

public class DaoTarefa {

	public void inserir(Tarefa tarefa) throws SQLException {

		String dbURL = "jdbc:mysql://localhost:3306/tasksdb1228";
		String username = "root";
		String senha = "";
		
		Connection conexao = DriverManager.getConnection(dbURL, username, senha);
		
		String sql = "insert into tarefas (descricao, prioridade) values(? , ?);";
		PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, tarefa.getDescricao());
		ps.setInt(2, tarefa.getPrioridade());

		ps.executeUpdate();
		
		ResultSet r = ps.getGeneratedKeys();
		
		if( r.next() ) {
			int id = r.getInt(1);	
			tarefa.setId(id);
		}
	}

	public void atualizar() {

	}

	public void excluir() {

	}

	public Tarefa buscar() {
		return null;
	}

	public List<Tarefa> buscarTodas() {

		return null;
	}
}

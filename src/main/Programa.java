package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dao.DaoTarefa;
import entidades.Tarefa;

public class Programa {

	public static void main(String[] args) throws SQLException {
		
		DaoTarefa daoTarefa = new DaoTarefa();
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite a tarefa: ");
		String descricao = scanner.nextLine();
		System.out.println("Informe a prioridade: ");
		int prioridade = Integer.parseInt(scanner.nextLine());

		Tarefa t = new Tarefa(descricao, prioridade);
		
		daoTarefa.inserir( t );
		
		System.out.println("Tarefa cadastrada sob o ID " + t.getId());
		
		
		//Select
		
		/*String sql = "select * from tarefas";
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ResultSet resultSet = ps.executeQuery();
		
		while( resultSet.next() ) {
			String descricao = resultSet.getString("descricao");
			int prioridade = resultSet.getInt("prioridade");
			
			System.out.println("Descrição: " + descricao);
			System.out.println("Prioridade: " + prioridade + "\n");
		}
		
		//Update
		/*String sql = "update tarefas set prioridade = ? where prioridade > ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ps.setInt(1, 5);
		ps.setInt(2, 5);
		
		System.out.println("Linhas afetadas: " + ps.executeUpdate());*/
		
		//Delete
		
		/*String sql = "delete from tarefas where id = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ps.setInt(1, 2);
		
		System.out.println("Linhas afetadas: " + ps.executeUpdate());*/
		
		
	}

}

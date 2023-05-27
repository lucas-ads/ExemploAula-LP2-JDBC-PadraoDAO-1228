package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DaoTarefa;
import dao.DaoUsuario;
import entidades.Tarefa;
import entidades.Usuario;

public class Programa {

	private static DaoTarefa daoTarefa = new DaoTarefa();
	private static DaoUsuario daoUsuario = new DaoUsuario();
	
	public static void main(String[] args) throws SQLException {
		
		Scanner scanner = new Scanner(System.in);
		int opcao;
		
		do {
			System.out.println("Digite:");
			System.out.println("1 - Cadastrar tarefa");
			System.out.println("2 - Atualizar tarefa");
			System.out.println("3 - Buscar tarefa");
			System.out.println("4 - Excluir tarefa");
			System.out.println("5 - Listar tarefas");
			System.out.println("6 - Pesquisar tarefas");
			
			System.out.println("7 - Cadastrar Usuário");
			System.out.println("8 - Listar usuários");
			
			System.out.println("9 - Listar tarefas por usuário");
			
			System.out.println("0 - Sair");
			
			opcao = Integer.parseInt( scanner.nextLine() );
			
			switch(opcao) {
				case 1:
					cadastrarTarefa();
					break;
				case 2:
					atualizarTarefa();
					break;
				case 3:
					buscarTarefa();
					break;
				case 4:
					excluirTarefa();
					break;
				case 5:
					listarTarefas();
					break;
				case 6:
					pesquisarTarefas();
					break;
				case 7:
					cadastrarUsuario();
					break;
				case 8:
					listarUsuarios();
					break;
				case 9:
					listarTarefasPorUsuario();
					break;
				case 0:
					System.out.println("Até mais.");
					break;
				default:
					System.out.println("Opção inválida!");
			}
			
		}while(opcao != 0);
	}
	
	public static void cadastrarTarefa() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite a tarefa: ");
		String descricao = scanner.nextLine();
		
		System.out.println("Informe a prioridade: ");
		int prioridade = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Informe o ID do Usuário: ");
		int idUser = Integer.parseInt(scanner.nextLine());

		Usuario u = daoUsuario.buscarPorId(idUser);
		
		if(u != null) {
			Tarefa t = new Tarefa(descricao, prioridade, u);
	
			System.out.println( daoTarefa.inserir( t ) ? "Cadastrou!" : "Falha do cadastro...");
	
			System.out.println("Tarefa cadastrada sob o ID " + t.getId());
		}else {
			System.out.println("Não existe usuário com o ID informado!");
		}
	}
	
	public static void atualizarTarefa() throws SQLException{
		System.out.println("##### Atualizando Tarefa #####");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		Tarefa tarefa = daoTarefa.buscar(id);
		
		if(tarefa != null) {
			
			System.out.println("Descrição atual da tarefa: " + tarefa.getDescricao());
			System.out.println("Informe a nova descrição ou pressione enter:");
			
			String desc = scanner.nextLine();
			
			if(desc.length() > 0) {
				tarefa.setDescricao(desc);
			}
			
			System.out.println("Prioridade atual da tarefa: " + tarefa.getPrioridade());
			System.out.println("Informe a nova prioridade ou pressione enter:");
			
			String priori = scanner.nextLine();
			
			if(priori.length() > 0) {
				tarefa.setPrioridade( Integer.parseInt(priori) );
			}
			
			if( daoTarefa.atualizar(tarefa) ) {
				System.out.println("Tarefa atualizada!");
			}else {
				System.out.println("Houve um erro ao atualizar.");
			}
			
		}else {
			System.out.println("Erro ao localizar tarefa. A tarefa "+ id +" existe?");
		}
	}
	
	public static void buscarTarefa() throws SQLException {
		System.out.println("\n##### Buscando Tarefa por ID ######");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		Tarefa t = daoTarefa.buscar(id);
		
		if(t != null) {
			System.out.println("ID: " + t.getId());
			System.out.println("Descrição: " + t.getDescricao());
			System.out.println("Prioridade: " + t.getPrioridade());
			System.out.println("Usuário: " + t.getUsuario().getEmail() +"\n");
		}else {
			System.out.println("Tarefa não existe...");
		}
	}

	public static void excluirTarefa() throws SQLException{
		System.out.println("\n##### Excluindo Tarefa por ID ######");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		boolean r = daoTarefa.excluir(id);
		
		if( r ) {
			System.out.println("Tarefa excluída!");
		}else {
			System.out.println("Houve um erro ao excluir. A tarefa "+ id +" existe?");
		}
	}
	
	public static void listarTarefas() throws SQLException {
		
		System.out.println("\n##### Listar Tarefas #####\n");
		
		List<Tarefa> tasks = daoTarefa.buscarTodas();

		Scanner scanner = new Scanner(System.in);
		
		for(Tarefa t : tasks) {
			System.out.println("ID: " + t.getId());
			System.out.println("Descrição: " + t.getDescricao());
			System.out.println("Prioridade: " + t.getPrioridade());
			System.out.println("Usuário: " + t.getUsuario().getEmail() +"\n");

			
			scanner.nextLine();
		}
	}

	public static void pesquisarTarefas() throws SQLException {
		System.out.println("\n##### Buscando Tarefas por Descrição ######");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o descrição: ");
		String pesquisa = scanner.nextLine();

		List<Tarefa> tasks = daoTarefa.pesquisarPorDescricao(pesquisa);
		
		for(Tarefa t : tasks) {
			System.out.println("ID: " + t.getId());
			System.out.println("Descrição: " + t.getDescricao());
			System.out.println("Prioridade: " + t.getPrioridade());
			System.out.println("Usuário: " + t.getUsuario().getEmail() +"\n");
			
			scanner.nextLine();
		}
	}

	public static void cadastrarUsuario() throws SQLException{
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite seu email: ");
		String email = scanner.nextLine();
		
		System.out.println("Informe sua senha: ");
		String senha = scanner.nextLine();

		Usuario usuario = new Usuario(email, senha);

		System.out.println( daoUsuario.inserir( usuario ) ? "Cadastrou!" : "Falha do cadastro...");

		System.out.println("Usuario cadastrado sob o ID " + usuario.getId());
	}
	
	public static void listarUsuarios() throws SQLException {
		
		System.out.println("\n##### Listar Usuários #####\n");
		
		List<Usuario> users = daoUsuario.buscarTodos();
		
		for(Usuario u : users) {
			System.out.println("ID: " + u.getId());
			System.out.println("E-mail: " + u.getEmail());
			System.out.println("Senha: " + u.getSenha());
		}
	}
	
	public static void listarTarefasPorUsuario() throws SQLException{
		System.out.println("\n##### Listar Tarefas por Usuário #####\n");
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o email do usuário: ");
		String email = scanner.nextLine();
		
		List<Tarefa> tasks = daoTarefa.tarefasPorUsuario(email);
		
		for(Tarefa t : tasks) {
			System.out.println("ID: " + t.getId());
			System.out.println("Descrição: " + t.getDescricao());
			System.out.println("Prioridade: " + t.getPrioridade());
			System.out.println("Usuário: " + t.getUsuario().getEmail() +"\n");
			
			scanner.nextLine();
		}
	}
}
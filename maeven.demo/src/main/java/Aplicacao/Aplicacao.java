package Aplicacao;

import java.util.List;

import DAO.demo.dao;
import DAO.demo.UsuarioDao;
import model.Usuario;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		
		UsuarioDao usuarioDAO = new UsuarioDao();
		
		System.out.println("\n\n==== Inserir usuário === ");
		Usuario usuario = new Usuario(11, "pablo", "pablo",'M');
		if(usuarioDAO.insert(usuario) == true) {
			System.out.println("Inserção com sucesso -> " + usuario.toString());
		}
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", "pablo"));
			
		System.out.println("\n\n==== Mostrar usuários do sexo masculino === ");
		List<Usuario> usuarios = usuarioDAO.getSexoMasculino();
		for (Usuario u: usuarios) {
			System.out.println(u.toString());
		}

		System.out.println("\n\n==== Atualizar senha (código (" + usuario.getCodigo() + ") === ");
		usuario.setSenha(dao.toMD5("pablo"));
		usuarioDAO.update(usuario);
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", dao.toMD5("pablo")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));

		System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
		usuarios = usuarioDAO.getOrderByCodigo();
		for (Usuario u: usuarios) {
			System.out.println(u.toString());
		}
		
		System.out.println("\n\n==== Excluir usuário (código " + usuario.getCodigo() + ") === ");
		usuarioDAO.delete(usuario.getCodigo());
		
		System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
		usuarios = usuarioDAO.getOrderByLogin();
		for (Usuario u: usuarios) {
			System.out.println(u.toString());
		}
	}
}
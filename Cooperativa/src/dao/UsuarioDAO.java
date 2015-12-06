package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDAO {
	public List<Usuario> listaUsuarios() throws Exception;
	public void insertarUsuario(Usuario usuario) throws Exception;
	public void modificarUsuario(Usuario usuario) throws Exception;
	public void eliminarUsuario(Usuario usuario) throws Exception;
	public Usuario buscarUsuario(String username,String password) throws Exception;
}

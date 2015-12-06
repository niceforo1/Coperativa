package dao;

import java.util.List;

import model.UsuarioTipo;

public interface UsuarioTiposDAO {
	public List<UsuarioTipo> listaUsuariosTipo() throws Exception;
	public void insertarUsuarioTipo(UsuarioTipo tipoUsuario) throws Exception;
	public void modificarUsuarioTipo(UsuarioTipo tipoUsuario) throws Exception;
	public void eliminarUsuarioTipo(UsuarioTipo tipoUsuario) throws Exception;
	public UsuarioTipo buscarUsuarioTipoId(Long tipoUsuarioId) throws Exception;
}

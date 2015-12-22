package dao;

import java.util.List;

import model.CategoriaConexion;

public interface CategoriaConexionDAO {
	public List<CategoriaConexion> listaCategoriaConexion() throws Exception;
	public void insertarCategoriaConexion(CategoriaConexion categoria) throws Exception;
	public void modificarCategoriaConexion(CategoriaConexion categoria) throws Exception;
	public void eliminarCategoriaConexion(CategoriaConexion categoria) throws Exception;
	public CategoriaConexion buscarCategoriaConexionId(Long id) throws Exception;
}

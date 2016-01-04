package dao;

import java.util.List;

import model.EstadoConexion;

public interface EstadoConexionDAO {
	public List<EstadoConexion> listaEstados() throws Exception;
	public void insertarEstadoConexion(EstadoConexion estado) throws Exception;
	public void modificarEstadoConexion(EstadoConexion estado) throws Exception;
	public void eliminarEstadoConexion(EstadoConexion estado) throws Exception;
	public EstadoConexion buscarEstadoConexionId(Long id) throws Exception;
	public EstadoConexion buscarEstadoConexion(String descripcion) throws Exception;
}

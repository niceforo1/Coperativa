package dao;

import java.util.List;

import model.EstadoSocio;

public interface EstadoSocioDAO {
	public List<EstadoSocio> listaEstadoSocio() throws Exception;
	public void insertarEstadoSocio(EstadoSocio estadoSocio) throws Exception;
	public void modificarEstadoSocio(EstadoSocio estadoSocio) throws Exception;
	public void eliminarEstadoSocio(EstadoSocio estadoSocio) throws Exception;
	public EstadoSocio buscarEstadoSocio(Long id) throws Exception;
	public EstadoSocio buscarEstadoSocio(String codigo) throws Exception;
}

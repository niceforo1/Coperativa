package dao;

import java.util.List;

import model.ZonaConexion;

public interface ZonaConexionDAO {
	public List<ZonaConexion> listaZonas() throws Exception;
	public void insertarZonaConexion(ZonaConexion zona) throws Exception;
	public void modificarZonaConexion(ZonaConexion zona) throws Exception;
	public void eliminarZonaConexion(ZonaConexion zona) throws Exception;
	public ZonaConexion buscarZonaConexionId(Long id) throws Exception;
}

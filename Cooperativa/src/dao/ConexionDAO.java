package dao;

import java.util.List;
import model.Conexion;

public interface ConexionDAO {
	public List<Conexion> listaConexion() throws Exception;
	public void insertarConexion(Conexion conexion) throws Exception;
	public void modificarConexion(Conexion conexion)throws Exception;
	public void eliminarConexion(Conexion conexion) throws Exception;
	public Conexion buscarConexionID(Long id) throws Exception;
}

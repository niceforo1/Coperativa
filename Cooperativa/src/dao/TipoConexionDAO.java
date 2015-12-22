package dao;

import java.util.List;
import model.TipoConexion;

public interface TipoConexionDAO {
	public List<TipoConexion> listaTipoConexion() throws Exception;
	public void insertarTipoConexion(TipoConexion tipo) throws Exception;
	public void modificarTipoConexion(TipoConexion tipo) throws Exception;
	public void eliminarTipoConexion(TipoConexion tipo) throws Exception;
	public TipoConexion buscarTipoConexionId(Long id) throws Exception;
}

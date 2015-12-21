package dao;

import java.util.List;

import model.TipoTerreno;

public interface TipoTerrenoDAO {
	public List<TipoTerreno> listaTipoTerreno() throws Exception;
	public void insertarTipoTerreno(TipoTerreno tipo) throws Exception;
	public void modificarTipoTerreno(TipoTerreno tipo) throws Exception;
	public void eliminarTipoTerreno(TipoTerreno tipo) throws Exception;
	public TipoTerreno buscarTipoTerrenoId(Long id) throws Exception;
}

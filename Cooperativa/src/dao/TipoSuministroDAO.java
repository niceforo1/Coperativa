package dao;

import java.util.List;

import model.TipoSuministro;

public interface TipoSuministroDAO {
	public List<TipoSuministro> listaTipoSuministro() throws Exception;
	public void insertarTipoSuministro(TipoSuministro tipo) throws Exception;
	public void modificarTipoSuministro(TipoSuministro tipo) throws Exception;
	public void eliminarTipoSuministro(TipoSuministro tipo) throws Exception;
	public TipoSuministro buscarTipoSuministroId(Long id) throws Exception;
}

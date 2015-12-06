package dao;

import java.util.List;

import model.TipoSocio;

public interface TipoSocioDAO {
	public List<TipoSocio> listaTipoSocio() throws Exception;
	public void insertarTipoSocio(TipoSocio tipoSocio) throws Exception;
	public void modificarTipoSocio(TipoSocio tipoSocio)throws Exception;
	public void eliminarTipoSocio(TipoSocio tipoSocio) throws Exception;
	public TipoSocio buscarTipoSocioId(Long id) throws Exception;	
}

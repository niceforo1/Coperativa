package dao;

import java.util.List;

import model.EstadoCivil;

public interface EstadoCivilDAO {
	public List<EstadoCivil> listaEstadoCivil() throws Exception;
	public void insertarEstadoCivil(EstadoCivil estadoCivil) throws Exception;
	public void modificarEstadoCivil(EstadoCivil estadoCivil)throws Exception;
	public void eliminarEstadoCivil(EstadoCivil estadoCivil) throws Exception;
	public EstadoCivil buscarEstadoCivilID(Long id) throws Exception;
}

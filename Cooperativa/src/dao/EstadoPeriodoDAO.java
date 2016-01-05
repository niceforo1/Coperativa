package dao;

import java.util.List;

import model.EstadoPeriodo;


public interface EstadoPeriodoDAO {
	public List<EstadoPeriodo> listaEstados() throws Exception;
	public void insertarEstadoPeriodo(EstadoPeriodo estado) throws Exception;
	public void modificarEstadoPeriodo(EstadoPeriodo estado) throws Exception;
	public void eliminarEstadoPeriodo(EstadoPeriodo estado) throws Exception;
	public EstadoPeriodo buscarEstadoPeriodoId(Long id) throws Exception;
	public EstadoPeriodo buscarEstadoPeriodo(String descripcion) throws Exception;
}

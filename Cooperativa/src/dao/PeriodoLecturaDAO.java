package dao;

import java.util.List;

import model.PeriodoLectura;

public interface PeriodoLecturaDAO {
	public List<PeriodoLectura> listaPeriodoLectura() throws Exception;
	public void insertarPeriodoLectura(PeriodoLectura periodo) throws Exception;
	public void modificarPeriodoLectura(PeriodoLectura periodo) throws Exception;
	public void eliminarPeriodoLectura(PeriodoLectura periodo) throws Exception;
	public PeriodoLectura buscarPeriodoLecturaId(Long id) throws Exception;
	public PeriodoLectura buscarPeriodoLecturaAbierto() throws Exception;
	public PeriodoLectura buscarPeriodoLecturaCerrado() throws Exception;
}

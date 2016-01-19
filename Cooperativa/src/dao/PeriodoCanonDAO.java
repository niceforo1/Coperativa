package dao;

import java.util.List;

import model.PeriodoCanon;


public interface PeriodoCanonDAO {
	public List<PeriodoCanon> listaPeriodosCanon() throws Exception;
	public void insertarPeriodosCanon(PeriodoCanon PeriodosCanon) throws Exception;
	public void modificarPeriodosCanon(PeriodoCanon PeriodosCanon) throws Exception;
	public void eliminarPeriodosCanon(PeriodoCanon PeriodosCanon) throws Exception;
	public PeriodoCanon buscarPeriodosCanonId(Long id) throws Exception;	
}

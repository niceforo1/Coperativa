package dao;

import java.util.Date;
import java.util.List;
import model.PeriodosSaldos;

public interface PeriodosSaldosDAO {
	public List<PeriodosSaldos> listaPeriodosSaldos() throws Exception;
	public void insertarPeriodosSaldos(PeriodosSaldos periodosSaldos) throws Exception;
	public void modificarPeriodosSaldos(PeriodosSaldos periodosSaldos) throws Exception;
	public void eliminarPeriodosSaldos(PeriodosSaldos periodosSaldos) throws Exception;
	public PeriodosSaldos buscarPeriodosSaldosId(Long id) throws Exception;
	public List<PeriodosSaldos> buscarPeriodosSaldosConexion(Long id, Date fechaDesde,Date fechaHasta) throws Exception;
	public PeriodosSaldos buscarPeriodosSaldosMesAnio(Long id, long mes, long anio) throws Exception;
}

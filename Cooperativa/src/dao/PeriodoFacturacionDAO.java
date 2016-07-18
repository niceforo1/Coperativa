package dao;

import java.util.List;

import model.PeriodoFacturacion;

public interface PeriodoFacturacionDAO {
	public List<PeriodoFacturacion> listaPeriodoFacturacion() throws Exception;
	public void insertarPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) throws Exception;
	public void modificarPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) throws Exception;
	public void eliminarPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) throws Exception;
	public PeriodoFacturacion buscarPeriodoFacturacionId(Long id) throws Exception;
	public PeriodoFacturacion buscarPeriodoFacturacionAbierto() throws Exception;
	public List<PeriodoFacturacion> lstPeriodosFacturacionNoHist() throws Exception;
}

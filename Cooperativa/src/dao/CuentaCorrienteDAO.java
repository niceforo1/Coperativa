package dao;

import java.util.Date;
import java.util.List;

import model.CuentaCorriente;

public interface CuentaCorrienteDAO {
	public List<CuentaCorriente> obtenerCuentaCorriente(Long conexionId,Date fechaDesde,Date fechaHasta) throws Exception;
}

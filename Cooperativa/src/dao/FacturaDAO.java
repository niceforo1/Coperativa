package dao;

import java.util.List;

import model.Conexion;
import model.Factura;
import model.PeriodosSaldos;

public interface FacturaDAO {
	public List<Factura> listaFactura() throws Exception;
	public List<Factura> listaFacturaConexion(Long conexionID) throws Exception;
	public void insertarFactura(Factura factura) throws Exception;
	public void modificarFactura(Factura factura) throws Exception;
	public void eliminarFactura(Factura factura) throws Exception;
	public Factura buscarFactura(Long id) throws Exception;
	public Factura buscarFacturaPerSaldo(PeriodosSaldos periodosSaldos) throws Exception;	
}

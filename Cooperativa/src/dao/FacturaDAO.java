package dao;

import java.util.List;

import model.Factura;

public interface FacturaDAO {
	public List<Factura> listaFactura() throws Exception;
	public void insertarFactura(Factura factura) throws Exception;
	public void modificarFactura(Factura factura) throws Exception;
	public void eliminarFactura(Factura factura) throws Exception;
	public Factura buscarFactura(Long id) throws Exception;	
}

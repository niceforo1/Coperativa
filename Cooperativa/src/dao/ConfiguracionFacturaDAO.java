package dao;

import java.util.List;

import model.ConfiguracionFactura;

public interface ConfiguracionFacturaDAO {
	public List<ConfiguracionFactura> obtenerConfiguracionFactura() throws Exception;
	public void insertarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception;
	public void modificarConfiguracionFactura(ConfiguracionFactura configuracionFactura)throws Exception;
	public void eliminarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception;
	public ConfiguracionFactura buscarConfiguracionFacturaId(Long id) throws Exception;
}

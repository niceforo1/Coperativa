package dao;

import model.ConfiguracionFactura;

public interface ConfiguracionFacturaDAO {
	public ConfiguracionFactura obtenerConfiguracionFactura() throws Exception;
	public void insertarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception;
	public void modificarConfiguracionFactura(ConfiguracionFactura configuracionFactura)throws Exception;
	public void eliminarConfiguracionFactura(ConfiguracionFactura configuracionFactura) throws Exception;
}

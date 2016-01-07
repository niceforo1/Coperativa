package dao;

import java.util.List;

import model.ConfiguracionLectura;

public interface ConfiguracionLecturaDAO {
	public List<ConfiguracionLectura> listaConfiguracionLectura() throws Exception;
	public void insertarConfiguracionLectura(ConfiguracionLectura configuracionLectura) throws Exception;
	public void modificarConfiguracionLectura(ConfiguracionLectura configuracionLectura)throws Exception;
	public void eliminarConfiguracionLectura(ConfiguracionLectura configuracionLectura) throws Exception;
}

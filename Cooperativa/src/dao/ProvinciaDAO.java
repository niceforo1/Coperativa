package dao;

import java.util.List;

import model.Provincia;

public interface ProvinciaDAO {
	public List<Provincia> listaProvincias() throws Exception;
	public void insertarProvincia(Provincia provincia) throws Exception;
	public void modificarProvincia(Provincia provincia) throws Exception;
	public void eliminarProvincia(Provincia provincia) throws Exception;
	public Provincia buscarProvinciaId(Long id) throws Exception;
}

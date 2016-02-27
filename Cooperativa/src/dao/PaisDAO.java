package dao;

import java.util.List;

import model.Pais;

public interface PaisDAO {
	public List<Pais> listaPais() throws Exception;
	public Long insertarPais(Pais pais) throws Exception;
	public void modificarPais(Pais pais) throws Exception;
	public void eliminarPais(Pais pais) throws Exception;
	public Pais buscarPaisId(Long id) throws Exception;
	public Pais buscarPaisDescripcion(String desc) throws Exception;
}

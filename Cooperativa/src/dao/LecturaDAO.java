package dao;

import java.util.List;

import model.Lectura;
public interface LecturaDAO {
	public List<Lectura> listaLectura() throws Exception;
	public void insertarLectura(Lectura lectura) throws Exception;
	public void modificarLectura(Lectura lectura) throws Exception;
	public void eliminarLectura(Lectura lectura) throws Exception;
	public Lectura buscarLecturaId(Long id) throws Exception;
}

package dao;

import java.util.List;

import model.Socio;


public interface SocioDAO {
	public List<Socio> listaSocio() throws Exception;
	public List<Socio> listaSociosActivos(String estado) throws Exception;
	public void insertarSocio(Socio socio) throws Exception;
	public void modificarSocio(Socio socio)throws Exception;
	public void eliminarSocio(Socio socio) throws Exception;
	public Socio buscarSocioID(Long id) throws Exception;
}

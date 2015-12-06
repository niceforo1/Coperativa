package dao;

import model.Domicilio;

public interface DomicilioDAO {
	public void insertarDomicilio(Domicilio domicilio) throws Exception;
	public void modificarDomicilio(Domicilio domicilio) throws Exception;
	public void eliminarDomicilio(Domicilio domicilio) throws Exception;
	public Domicilio buscarDomicilioId(Long id) throws Exception;
}

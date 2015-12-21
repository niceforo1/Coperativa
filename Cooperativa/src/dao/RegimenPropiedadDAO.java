package dao;

import java.util.List;

import model.RegimenPropiedad;

public interface RegimenPropiedadDAO {
	public List<RegimenPropiedad> listaRegimenPropiedad() throws Exception;
	public void insertarRegimenPropiedad(RegimenPropiedad regimen) throws Exception;
	public void modificarRegimenPropiedad(RegimenPropiedad regimen) throws Exception;
	public void eliminarRegimenPropiedad(RegimenPropiedad regimen) throws Exception;
	public RegimenPropiedad buscarRegimenPropiedadId(Long id) throws Exception;
}

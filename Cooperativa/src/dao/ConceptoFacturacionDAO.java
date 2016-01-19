package dao;

import java.util.List;

import model.ConceptoFacturacion;

public interface ConceptoFacturacionDAO {
	public List<ConceptoFacturacion> listaConceptoFacturacion() throws Exception;
	public void insertarConceptoFacturacion(ConceptoFacturacion conceptoFacturacion) throws Exception;
	public void modificarConceptoFacturacion(ConceptoFacturacion conceptoFacturacion)throws Exception;
	public void eliminarConceptoFacturacion(ConceptoFacturacion conceptoFacturacion) throws Exception;
	public ConceptoFacturacion buscarConceptoFacturacionId(Long id) throws Exception;
}

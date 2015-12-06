package dao;

import java.util.List;

import model.TipoDocumento;

public interface TipoDocumentoDAO {
	public List<TipoDocumento> listaTipoDocumento() throws Exception;
	public List<TipoDocumento> listaTipoDocumentoCUIT() throws Exception;
	public void insertarTipoDocumento(TipoDocumento tipoDocumento) throws Exception;
	public void modificarTipoDocumento(TipoDocumento tipoDocumento)throws Exception;
	public void eliminarTipoDocumento(TipoDocumento tipoDocumento) throws Exception;
	public TipoDocumento buscarTipoDocumentoID(Long id) throws Exception;
}

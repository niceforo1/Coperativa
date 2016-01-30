package dao;

import java.util.List;

import model.TipoComprobante;

public interface TipoComprobanteDAO {

	public List<TipoComprobante> listaTipoComprobante() throws Exception;
	public void insertarTipoComprobante(TipoComprobante tipoComprobante) throws Exception;
	public void modificarTipoComprobante(TipoComprobante tipoComprobante) throws Exception;
	public void eliminarTipoComprobante(TipoComprobante tipoComprobante) throws Exception;
	public TipoComprobante buscarTipoComprobanteId(Long id) throws Exception;
}

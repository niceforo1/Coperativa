package dao;

import java.util.List;

import model.Recibo;

public interface ReciboDAO {
	public List<Recibo> listaRecibo() throws Exception;
	public void insertarRecibo(Recibo recibo) throws Exception;
	public void modificarRecibo(Recibo recibo) throws Exception;
	public void eliminarRecibo(Recibo recibo) throws Exception;
	public Recibo buscarReciboId(Long id) throws Exception;
}

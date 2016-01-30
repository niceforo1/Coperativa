package dao;

import java.util.List;

import model.NotaCredito;

public interface NotaCreditoDAO {
	public List<NotaCredito> listaNotaCredito() throws Exception;
	public void insertarNotaCredito(NotaCredito notaCredito) throws Exception;
	public void modificarNotaCredito(NotaCredito notaCredito) throws Exception;
	public void eliminarNotaCredito(NotaCredito notaCredito) throws Exception;
	public NotaCredito buscarNotaCreditoId(Long id) throws Exception;
}

package dao;

import java.util.List;

import model.NotaDebito;

public interface NotaDebitoDAO {
	public List<NotaDebito> listaNotaDebito() throws Exception;
	public void insertarNotaDebito(NotaDebito notaDebito) throws Exception;
	public void modificarNotaDebito(NotaDebito notaDebito) throws Exception;
	public void eliminarNotaDebito(NotaDebito notaDebito) throws Exception;
	public NotaDebito buscarNotaDebitoId(Long id) throws Exception;
}

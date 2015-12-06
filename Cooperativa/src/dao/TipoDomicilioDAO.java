package dao;

import java.util.List;

import model.TipoDomicilio;

public interface TipoDomicilioDAO {
	public List<TipoDomicilio> listaTipoDomicilio() throws Exception;
	public void insertarTipoDomicilio(TipoDomicilio tipoDomicilio) throws Exception;
	public void modificarTipoDomicilio(TipoDomicilio tipoDomicilio)throws Exception;
	public void eliminarTipoDomicilio(TipoDomicilio tipoDomicilio) throws Exception;
	public TipoDomicilio buscarTipoDomicilioId(Long id) throws Exception;
}

package dao;

import java.util.List;

import model.FormaPago;

public interface FormaPagoDAO {
	public List<FormaPago> listaFormaPago() throws Exception;
	public void insertarFormaPago(FormaPago forma) throws Exception;
	public void modificarFormaPago(FormaPago forma) throws Exception;
	public void eliminarFormaPago(FormaPago forma) throws Exception;
	public FormaPago buscarFormaPagoId(Long id) throws Exception;
}


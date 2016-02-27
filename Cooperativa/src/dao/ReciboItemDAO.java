package dao;

import java.util.List;
import model.ReciboItem;

public interface ReciboItemDAO {
	public List<ReciboItem> listaReciboItem() throws Exception;
	public Long insertarReciboItem(ReciboItem recibo) throws Exception;
	public void modificarReciboItem(ReciboItem recibo) throws Exception;
	public void eliminarReciboItem(ReciboItem recibo) throws Exception;
	public ReciboItem buscarReciboItemId(Long id) throws Exception;
}

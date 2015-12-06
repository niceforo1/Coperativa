package dao;

import java.util.List;
import model.CondicionIva;


public interface CondicionIvaDAO {
	public List<CondicionIva> listaCondicionesIva() throws Exception;
	public void insertarCondicionIva(CondicionIva condicionIva) throws Exception;
	public void modificarCondicionIva(CondicionIva condicionIva)throws Exception;
	public void eliminarCondicionIva(CondicionIva condicionIva) throws Exception;
	public CondicionIva buscarCondicionIvaId(Long id) throws Exception;
}


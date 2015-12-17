package dao;
import model.Socios_Transacciones;

public interface Socios_TransaccionesDAO {
	public void insertarTransaccion(Socios_Transacciones transaccion) throws Exception;
	public void modificarTransaccion(Socios_Transacciones transaccion) throws Exception;
	public void eliminarTransaccion(Socios_Transacciones transaccion) throws Exception;
}

package dao;
import model.SociosTransacciones;

public interface Socios_TransaccionesDAO {
	public void insertarTransaccion(SociosTransacciones transaccion) throws Exception;
	public void modificarTransaccion(SociosTransacciones transaccion) throws Exception;
	public void eliminarTransaccion(SociosTransacciones transaccion) throws Exception;
}

package dao;
import java.util.List;
import model.TarjetaNaranja;

public interface TarjetaNaranjaDAO {
	public List<TarjetaNaranja> listaTarjetaNaranja() throws Exception;
	public void insertarTarjetaNaranja(TarjetaNaranja tarjetaNaranja) throws Exception;
	public void modificarTarjetaNaranja(TarjetaNaranja tarjetaNaranja)throws Exception;
	public void eliminarTarjetaNaranja(TarjetaNaranja tarjetaNaranja) throws Exception;
	public TarjetaNaranja buscarTarjetaNaranjaID(Long id) throws Exception;
}

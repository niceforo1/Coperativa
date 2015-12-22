package dao;

import java.util.List;
import model.TipoUbicacionCatastral;

public interface TipoUbicacionCatastralDAO {
	public List<TipoUbicacionCatastral> listaTipoUbicacionCatastral() throws Exception;
	public void insertarTipoUbicacionCatastral(TipoUbicacionCatastral tipo) throws Exception;
	public void modificarTipoUbicacionCatastral(TipoUbicacionCatastral tipo) throws Exception;
	public void eliminarTipoUbicacionCatastral(TipoUbicacionCatastral tipo) throws Exception;
	public TipoUbicacionCatastral buscarTipoUbicacionCatastralId(Long id) throws Exception;
	public TipoUbicacionCatastral buscarTipoUbicacionCatastralDescripcion(String desc) throws Exception;
}

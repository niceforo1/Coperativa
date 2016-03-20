package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import dao.TipoUbicacionCatastralDAO;
import dao.impl.TipoUbicacionCatastralDAOImplement;
import model.TipoUbicacionCatastral;

@ManagedBean(name="tipoUbicacionCatastralBean")
@ViewScoped
public class TipoUbicacionCatastralBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(TipoUbicacionCatastralBean.class);

	private List<TipoUbicacionCatastral> listaTipoUbicacionCatastral;
	private TipoUbicacionCatastral tipoUbicacionCatastral;
	
	public TipoUbicacionCatastralBean() {
		inicializar();
	}

	public List<TipoUbicacionCatastral> getListaTipoUbicacionCatastral() {
		TipoUbicacionCatastralDAO daoTipoUbicacionCatastral = new TipoUbicacionCatastralDAOImplement();
		try {
			listaTipoUbicacionCatastral = daoTipoUbicacionCatastral.listaTipoUbicacionCatastral();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			LOG.error("Error al obtener Lista Tipo Ubicacion Catastral: " + e.getMessage());
		}
		return listaTipoUbicacionCatastral;
	}

	public void setListaTipoUbicacionCatastral(
			List<TipoUbicacionCatastral> listaTipoUbicacionCatastral) {
		this.listaTipoUbicacionCatastral = listaTipoUbicacionCatastral;
	}

	public TipoUbicacionCatastral getTipoUbicacionCatastral() {
		return tipoUbicacionCatastral;
	}

	public void setTipoUbicacionCatastral(
			TipoUbicacionCatastral tipoUbicacionCatastral) {
		this.tipoUbicacionCatastral = tipoUbicacionCatastral;
	}

	public void insertarTipoUbicacionCatastral(){
		TipoUbicacionCatastralDAO daoTipoUbicacionCatastral = new TipoUbicacionCatastralDAOImplement();
		try {
			daoTipoUbicacionCatastral.insertarTipoUbicacionCatastral(tipoUbicacionCatastral);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se agrego correctamente"));
			inicializar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
			LOG.error("Error al Insertar Tipo Ubicacion Catastral: " + e.getMessage());
		}
	}
	
	public void eliminarTipoUbicacionCatastral(TipoUbicacionCatastral tipo){
		TipoUbicacionCatastralDAO daoTipoUbicacionCatastral = new TipoUbicacionCatastralDAOImplement();
		try {
			daoTipoUbicacionCatastral.eliminarTipoUbicacionCatastral(tipo);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se eliminó correctamente"));
			inicializar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
			LOG.error("Error al Eliminar Tipo Ubicacion Catastral: " + e.getMessage());
		}
	}
	private void inicializar(){
		tipoUbicacionCatastral = new TipoUbicacionCatastral();
	}
	
}

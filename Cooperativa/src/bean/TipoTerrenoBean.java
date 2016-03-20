package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import dao.TipoTerrenoDAO;
import dao.impl.TipoTerrenoDAOImplement;
import model.TipoTerreno;

@ManagedBean(name="tipoTerrenoBean")
@ViewScoped
public class TipoTerrenoBean implements Serializable{
	private static final Logger LOG = Logger.getLogger(TipoTerrenoBean.class);

	private List<TipoTerreno> listaTiposTerreno;
	private TipoTerreno tipoTerreno;
	
	public TipoTerrenoBean() {
		inicializar();
	}

	public List<TipoTerreno> getListaTiposTerreno() {
		TipoTerrenoDAO daotipoTerreno = new TipoTerrenoDAOImplement();
		try {
			listaTiposTerreno = daotipoTerreno.listaTipoTerreno();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			LOG.error("Error al obtener Lista Tipos Terreno: " + e.getMessage());
		}
		return listaTiposTerreno;
	}

	public void setListaTiposTerreno(List<TipoTerreno> listaTiposTerreno) {
		this.listaTiposTerreno = listaTiposTerreno;
	}

	public TipoTerreno getTipoTerreno() {
		return tipoTerreno;
	}

	public void setTipoTerreno(TipoTerreno tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}
	
	public void insertarTipoTerreno(){
		TipoTerrenoDAO daotipoTerreno = new TipoTerrenoDAOImplement();
		try {
			daotipoTerreno.insertarTipoTerreno(tipoTerreno);
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
			LOG.error("Error al Insertar Tipo Terreno: " + e.getMessage());
		}
	}
	
	public void eliminarTipoTerreno(TipoTerreno tipo){
		TipoTerrenoDAO daotipoTerreno = new TipoTerrenoDAOImplement();
		try {
			daotipoTerreno.eliminarTipoTerreno(tipo);
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
			LOG.error("Error al Eliminar Tipo Terreno: " + e.getMessage());
		}
	}
	
	private void inicializar(){
		tipoTerreno = new TipoTerreno();
	}
}

package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.ZonaConexionDAO;
import dao.impl.ZonaConexionDAOImplement;
import model.ZonaConexion;

@ManagedBean(name="zonaConexionBean")
@ViewScoped
public class ZonaConexionBean implements Serializable{
	
	private List<ZonaConexion> listaZonasConexion;
	private ZonaConexion zonaConexion;
	
	public ZonaConexionBean() {
		inicializar();
	}
	
	public List<ZonaConexion> getListaZonasConexion() {
		ZonaConexionDAO daoZonaConex = new ZonaConexionDAOImplement();
		try {
			listaZonasConexion = daoZonaConex.listaZonas();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return listaZonasConexion;
	}

	public void setListaZonasConexion(List<ZonaConexion> listaZonasConexion) {
		this.listaZonasConexion = listaZonasConexion;
	}

	public ZonaConexion getZonaConexion() {
		return zonaConexion;
	}

	public void setZonaConexion(ZonaConexion zonaConexion) {
		this.zonaConexion = zonaConexion;
	}

	public void insertarZonaConexion(){
		ZonaConexionDAO daoZonaConex = new ZonaConexionDAOImplement();
		try {
			daoZonaConex.insertarZonaConexion(zonaConexion);
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
		}
	}
	
	public void eliminarZonaConexion(ZonaConexion zona){
		ZonaConexionDAO daoZonaConex = new ZonaConexionDAOImplement();
		try {
			daoZonaConex.eliminarZonaConexion(zona);
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
		}
	}
	
	private void inicializar(){
		zonaConexion = new ZonaConexion();
	}

}

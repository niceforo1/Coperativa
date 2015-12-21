package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.EstadoConexionDAO;
import dao.impl.EstadoConexionDAOImplement;
import model.EstadoConexion;

@ManagedBean(name="estadoConexionBean")
@ViewScoped
public class EstadoConexionBean implements Serializable {

	private List<EstadoConexion> listaEstadoConexion;
	private EstadoConexion estadoConexion;
	
	public EstadoConexionBean() {
		inicializar();
	}
	
	public List<EstadoConexion> getListaEstadoConexion() {
		EstadoConexionDAO daoEstConex = new EstadoConexionDAOImplement();
		try {
			listaEstadoConexion = daoEstConex.listaEstados();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return listaEstadoConexion;
	}


	public void setListaEstadoConexion(List<EstadoConexion> listaEstadoConexion) {
		this.listaEstadoConexion = listaEstadoConexion;
	}


	public EstadoConexion getEstadoConexion() {
		return estadoConexion;
	}


	public void setEstadoConexion(EstadoConexion estadoConexion) {
		this.estadoConexion = estadoConexion;
	}

	public void insertarEstadoConexion(){
		EstadoConexionDAO daoEstConex = new EstadoConexionDAOImplement();
		try {
			daoEstConex.insertarEstadoConexion(estadoConexion);
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
	
	public void eliminarEstadoConexion(EstadoConexion estado){
		EstadoConexionDAO daoEstConex = new EstadoConexionDAOImplement();
		try {
			daoEstConex.eliminarEstadoConexion(estado);
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
		estadoConexion = new EstadoConexion();
	}
}

package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.EstadoSocioDAO;
import dao.impl.EstadoSocioDAOImplement;
import model.EstadoSocio;

@ManagedBean(name="estadoSocioBean")
@ViewScoped

public class EstadoSocioBean implements Serializable {

	private List<EstadoSocio> listaEstadoSocio;
	private EstadoSocio estadoSocio;
		
	public EstadoSocioBean() {
		inicializar();
	}	
	
	public List<EstadoSocio> getListaEstadoSocio() {
		try{
			EstadoSocioDAO daoEstadoSocio = new EstadoSocioDAOImplement();
			listaEstadoSocio = daoEstadoSocio.listaEstadoSocio();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return listaEstadoSocio;
	}
	public void setListaEstadoSocio(List<EstadoSocio> listaEstadoSocio) {
		this.listaEstadoSocio = listaEstadoSocio;
	}
	public EstadoSocio getEstadoSocio() {
		return estadoSocio;
	}
	public void setEstadoSocio(EstadoSocio estadoSocio) {
		this.estadoSocio = estadoSocio;
	}
	
	public void insertarEstadoSocio(){
		try{
			EstadoSocioDAO daoEstadoSocio = new EstadoSocioDAOImplement();
			daoEstadoSocio.insertarEstadoSocio(estadoSocio);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se agregó correctamente."));
			inicializar();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " +e.getMessage()));
		}
	}
	
	public void eliminarEstadoSocio(EstadoSocio estadoSocio){
		
		try{
			EstadoSocioDAO daoEstadoSocio = new EstadoSocioDAOImplement();
			daoEstadoSocio.eliminarEstadoSocio(estadoSocio);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se eliminó correctamente."));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
		}
	}
	
	private void inicializar(){
		estadoSocio = new EstadoSocio();
	}
}

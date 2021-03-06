package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import dao.EstadoSocioDAO;
import dao.impl.EstadoSocioDAOImplement;
import model.EstadoSocio;

@ManagedBean(name="estadoSocioBean")
@ViewScoped

public class EstadoSocioBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(EstadoSocioBean.class);
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
			LOG.error("Error al obtener Lista Estado Socio");
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se agreg� correctamente."));
			inicializar();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " +e.getMessage()));
			LOG.error("Error al Insertar Estado Socio: " + e.getMessage());
		}
	}
	
	public void eliminarEstadoSocio(EstadoSocio estadoSocio){
		
		try{
			EstadoSocioDAO daoEstadoSocio = new EstadoSocioDAOImplement();
			daoEstadoSocio.eliminarEstadoSocio(estadoSocio);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se elimin� correctamente."));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
			LOG.error("Error al Eliminar Estado Socio: " + e.getMessage());
		}
	}
	
	private void inicializar(){
		estadoSocio = new EstadoSocio();
	}
}

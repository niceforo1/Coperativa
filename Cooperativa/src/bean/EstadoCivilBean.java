package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.EstadoCivilDAO;
import dao.impl.EstadoCivilDAOImplement;
import model.EstadoCivil;


@ManagedBean(name="estadoCivilBean")
@ViewScoped	

public class EstadoCivilBean implements Serializable{

	private List<EstadoCivil> listaEstadoCivil;
	private EstadoCivil estadoCivil;
	
	public EstadoCivilBean() {
		inicializar();
	}
	
	public List<EstadoCivil> getListaEstadoCivil() {
		try{
			EstadoCivilDAO daoEstCivil = new EstadoCivilDAOImplement();
			listaEstadoCivil = daoEstCivil.listaEstadoCivil();				
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}		
		return listaEstadoCivil;
	}
	
	public void setListaEstadoCivil(List<EstadoCivil> listaEstadoCivil) {
		this.listaEstadoCivil = listaEstadoCivil;
	}
	
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public void insertarEstadoCivil(){
		try{
			EstadoCivilDAO daoEstCivil = new EstadoCivilDAOImplement();		
			daoEstCivil.insertarEstadoCivil(estadoCivil);
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se agregó correctamente."));
	        inicializar();	
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
		}
	}
	
	public void eliminarEstadoCivil(EstadoCivil estadoCivil){		
		try{
			EstadoCivilDAO daoEstCivil = new EstadoCivilDAOImplement();
			daoEstCivil.eliminarEstadoCivil(estadoCivil);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se eliminó correctamente."));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
		}
	}
	
	private void inicializar(){
		estadoCivil = new EstadoCivil();
	}
	
	
}

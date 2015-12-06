package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.CondicionIvaDAO;
import dao.impl.CondicionIvaDAOImplement;
import model.CondicionIva;

@ManagedBean(name="condicionIvaBean")
@ViewScoped
public class CondicionIvaBean implements Serializable{
	private CondicionIva condicionIva;
	private List<CondicionIva> lstCondicionIva;
	
	public CondicionIvaBean() {
		inicializar();
	}

	public CondicionIva getCondicionIva() {
		return condicionIva;
	}


	public void setCondicionIva(CondicionIva condicionIva) {
		this.condicionIva = condicionIva;
	}


	public List<CondicionIva> getLstCondicionIva() {
		try{
			CondicionIvaDAO daoCondicionIva = new CondicionIvaDAOImplement();
			lstCondicionIva = daoCondicionIva.listaCondicionesIva();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}	
		return lstCondicionIva;
	}

	public void setLstCondicionIva(List<CondicionIva> lstCondicionIva) {
		this.lstCondicionIva = lstCondicionIva;
	}
	
	public void insertarCondicionIva(){
		try{
			CondicionIvaDAO daoCondicionIva = new CondicionIvaDAOImplement() ;		
			daoCondicionIva.insertarCondicionIva(condicionIva);
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se agregó correctamente."));
	        inicializar();	
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
		}
	}
	
	public void eliminarCondicionIva(CondicionIva condicionIva){		
		try{			
			CondicionIvaDAO daoCondicionIva = new CondicionIvaDAOImplement();			
			daoCondicionIva.eliminarCondicionIva(condicionIva);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se eliminó correctamente."));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
		}
	}
	private void inicializar(){
		condicionIva= new CondicionIva();
	}
}

package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext; 

import dao.TipoSocioDAO;
import dao.impl.TipoSocioDAOImplement;
import model.TipoSocio;

@ManagedBean(name="tipoSocioBean")
@ViewScoped
public class TipoSocioBean implements Serializable {
	
	private List<TipoSocio> listaTipoSocios;
	private TipoSocio tipoSocio;
		
	public TipoSocioBean(){
		inicializar();
	}

	public List<TipoSocio> getListaTipoSocios() {
		try{
			TipoSocioDAO daoTipSoc = new TipoSocioDAOImplement();
			listaTipoSocios = daoTipSoc.listaTipoSocio();				
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}		
		return listaTipoSocios;
	}

	public void setListaTipoSocios(List<TipoSocio> listaTipoSocios) {
		this.listaTipoSocios = listaTipoSocios;
	}

	public TipoSocio getTipoSocio() {
		return tipoSocio;
	}

	public void setTipoSocio(TipoSocio tipoSocio) {
		this.tipoSocio = tipoSocio;
	}
	public void insertarTipoSocio(){
		try{
			TipoSocioDAO daoTipSoc = new TipoSocioDAOImplement();		
			daoTipSoc.insertarTipoSocio(tipoSocio);
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se agregó correctamente."));
			inicializar();	
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
		}
	}
	public void eliminarTipoSocio(TipoSocio tipoSocio){		
		try{
			TipoSocioDAO daoTipoSoc = new TipoSocioDAOImplement();
			daoTipoSoc.eliminarTipoSocio(tipoSocio);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se eliminó correctamente."));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
		}
	}	
	private void inicializar(){
		tipoSocio = new TipoSocio();
	}
}

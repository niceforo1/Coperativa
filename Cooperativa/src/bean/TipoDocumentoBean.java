package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.TipoDocumentoDAO;
import dao.impl.TipoDocumentoDAOImplement;
import model.TipoDocumento;

@ManagedBean(name="tipoDocumentoBean")
@ViewScoped

public class TipoDocumentoBean implements Serializable{

	private List<TipoDocumento> listaTipoDocumento;
	private List<TipoDocumento> listaTipoDocumentoCUIT;
	private TipoDocumento tipoDocumento;
	
	public TipoDocumentoBean() {
		inicializar();
	}

	public List<TipoDocumento> getListaTipoDocumento(){
	    
		try{
			TipoDocumentoDAO daoTipoDocumento = new TipoDocumentoDAOImplement();
			listaTipoDocumento = daoTipoDocumento.listaTipoDocumento();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}
	
	public List<TipoDocumento> getListaTipoDocumentoCUIT() {
		try{
			TipoDocumentoDAO daoTipoDocumento = new TipoDocumentoDAOImplement();
			listaTipoDocumentoCUIT = daoTipoDocumento.listaTipoDocumentoCUIT();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return listaTipoDocumentoCUIT;
	}

	public void setListaTipoDocumentoCUIT(List<TipoDocumento> listaTipoDocumentoCUIT) {
		this.listaTipoDocumentoCUIT = listaTipoDocumentoCUIT;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public void insertarTipoDocumento(){
		try{
			TipoDocumentoDAO daoTipoDocumento = new TipoDocumentoDAOImplement();
			daoTipoDocumento.insertarTipoDocumento(tipoDocumento);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se agregó correctamente."));
			inicializar();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " +e.getMessage()));
		}
		
	}
	
	public void eliminarTipoDocumento(TipoDocumento tipoDocumento){
		try{
			TipoDocumentoDAO daoTipoDocumento = new TipoDocumentoDAOImplement();
			daoTipoDocumento.eliminarTipoDocumento(tipoDocumento);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se eliminó correctamente."));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
		}				
	}
	private void inicializar(){
		tipoDocumento = new TipoDocumento();
	}
	
}

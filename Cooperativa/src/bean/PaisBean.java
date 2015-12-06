package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.PaisDAO;
import dao.impl.PaisDAOImplement;
import model.Pais;

@ManagedBean(name = "paisBean")
@ViewScoped
public class PaisBean implements Serializable {
	private List<Pais> lstPaises;
	private Pais pais;

	public PaisBean() {
		inicializar();
	}

	public List<Pais> getLstPaises() {
		try {
			PaisDAO daoPais= new PaisDAOImplement();
			lstPaises = daoPais.listaPais();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
		}
		return lstPaises;
	}

	public void setLstPaises(List<Pais> lstPaises) {
		this.lstPaises = lstPaises;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	public void insertarPais(){
		try{
			PaisDAO daoPais= new PaisDAOImplement();
			daoPais.insertarPais(pais);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se agregó correctamente."));
			inicializar();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " +e.getMessage()));
		}
	}
	
	public void eliminarPais(Pais pais){
		try{
			PaisDAO daoPais= new PaisDAOImplement();
			daoPais.eliminarPais(pais);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se eliminó correctamente."));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: "+e.getMessage()));
		}
	}
	
	private void inicializar(){
		pais = new Pais();
	}
	
}

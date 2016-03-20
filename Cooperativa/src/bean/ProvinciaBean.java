package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import dao.ProvinciaDAO;
import dao.impl.ProvinciaDAOImplement;
import model.Provincia;

@ManagedBean(name = "provinciaBean")
@ViewScoped
public class ProvinciaBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(ProvinciaBean.class);

	private List<Provincia> lstProvincia;
	private Provincia provincia;

	public ProvinciaBean() {
		inicializar();
	}

	public List<Provincia> getLstProvincia() {
		try {
			ProvinciaDAO daoProvincia = new ProvinciaDAOImplement();
			lstProvincia = daoProvincia.listaProvincias();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e
							.getMessage()));
			LOG.error("Error al obtener Lista Provincia: " + e.getMessage());
		}
		return lstProvincia;
	}

	public void setLstProvincia(List<Provincia> lstProvincia) {
		this.lstProvincia = lstProvincia;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public void insertarProvincia() {
		try {
			ProvinciaDAO daoProvincia = new ProvinciaDAOImplement();
			daoProvincia.insertarProvincia(provincia);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se agregó correctamente."));
			inicializar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
			LOG.error("Error al Insertar Provincia: " + e.getMessage());
		}
	}

	public void eliminarProvincia(Provincia provincia) {
		try {
			ProvinciaDAO daoProvincia = new ProvinciaDAOImplement();
			daoProvincia.eliminarProvincia(provincia);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se eliminó correctamente."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
			LOG.error("Error al Eliminar Provincia: " + e.getMessage());
		}
	}
	private void inicializar(){
		provincia = new Provincia();
	}

}

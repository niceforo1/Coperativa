package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import dao.RegimenPropiedadDAO;
import dao.impl.RegimenPropiedadDAOImplement;
import model.RegimenPropiedad;

@ManagedBean(name="regimenPropiedadBean")
@ViewScoped
public class RegimenPropiedadBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(RegimenPropiedadBean.class);

	private List<RegimenPropiedad> listaRegimenPropiedad;
	private RegimenPropiedad regimenPropiedad;
	
	public RegimenPropiedadBean() {
		inicializar();
	}

	public List<RegimenPropiedad> getListaRegimenPropiedad() {
		RegimenPropiedadDAO daotipoRegimenPropiedad = new RegimenPropiedadDAOImplement();
		try {
			listaRegimenPropiedad = daotipoRegimenPropiedad.listaRegimenPropiedad();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			LOG.error("Error al obtener Lista Regimen Propiedad: " + e.getMessage());
		}
		return listaRegimenPropiedad;
	}

	public void setListaRegimenPropiedad(
			List<RegimenPropiedad> listaRegimenPropiedad) {
		this.listaRegimenPropiedad = listaRegimenPropiedad;
	}

	public RegimenPropiedad getRegimenPropiedad() {
		return regimenPropiedad;
	}

	public void setRegimenPropiedad(RegimenPropiedad regimenPropiedad) {
		this.regimenPropiedad = regimenPropiedad;
	}
	
	public void insertarRegimenPropiedad(){
		RegimenPropiedadDAO daotipoRegimenPropiedad = new RegimenPropiedadDAOImplement();
		try {
			daotipoRegimenPropiedad.insertarRegimenPropiedad(regimenPropiedad);
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
			LOG.error("Error al Insertar Regimen Propiedad: " + e.getMessage());
		}
	}
	
	public void eliminarRegimenPropiedad(RegimenPropiedad regimen){
		RegimenPropiedadDAO daotipoRegimenPropiedad = new RegimenPropiedadDAOImplement();
		try {
			daotipoRegimenPropiedad.eliminarRegimenPropiedad(regimen);
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
			LOG.error("Error al Eliminar Regimen Propiedad: " + e.getMessage());

		}
	}
	private void inicializar(){
		regimenPropiedad = new RegimenPropiedad();
	}

}

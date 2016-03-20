package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import dao.FormaPagoDAO;
import dao.TipoSuministroDAO;
import dao.impl.FormaPagoDAOImplement;
import dao.impl.TipoSuministroDAOImplement;
import model.FormaPago;
import model.TipoSuministro;

@ManagedBean(name="tipoSuministroBean")
@ViewScoped
public class TipoSuministroBean	implements Serializable {
	private static final Logger LOG = Logger.getLogger(TipoSuministroBean.class);

	private List<TipoSuministro> listaTipoSuministro;
	private TipoSuministro tipoSuministro;
	
	public TipoSuministroBean() {
		inicializar();
	}

	public List<TipoSuministro> getListaTipoSuministro() {
		TipoSuministroDAO daoTipoSuministro = new TipoSuministroDAOImplement();
		try {
			listaTipoSuministro = daoTipoSuministro.listaTipoSuministro();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			LOG.error("Error al obtener Lista Tipo Suministro: " + e.getMessage());
		}
		return listaTipoSuministro;
	}

	public void setListaTipoSuministro(List<TipoSuministro> listaTipoSuministro) {
		this.listaTipoSuministro = listaTipoSuministro;
	}

	public TipoSuministro getTipoSuministro() {
		return tipoSuministro;
	}

	public void setTipoSuministro(TipoSuministro tipoSuministro) {
		this.tipoSuministro = tipoSuministro;
	}
	
	public void insertarTipoSuministro(){
		TipoSuministroDAO daoTipoSuministro = new TipoSuministroDAOImplement();
		try {
			daoTipoSuministro.insertarTipoSuministro(tipoSuministro);
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
			LOG.error("Error al Insertar Tipo Suministro: " + e.getMessage());
		}
	}
	
	public void eliminarTipoSuministro(TipoSuministro tipo){
		TipoSuministroDAO daoTipoSuministro = new TipoSuministroDAOImplement();
		try {
			daoTipoSuministro.eliminarTipoSuministro(tipo);
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
			LOG.error("Error al Eliminar Tipo Suministro: " + e.getMessage());
		}
	}
	private void inicializar(){
		tipoSuministro = new TipoSuministro();
	}
}

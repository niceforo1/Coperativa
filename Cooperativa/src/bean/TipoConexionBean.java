package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import dao.TipoConexionDAO;
import dao.impl.TipoConexionDAOImplement;
import model.TipoConexion;

@ManagedBean(name="tipoConexionBean")
@ViewScoped
public class TipoConexionBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(TipoConexionBean.class);

	
	private List<TipoConexion> listaTipoConexion;
	private TipoConexion tipoConexion;
	
	public TipoConexionBean() {
		inicializar();
	}

	public List<TipoConexion> getListaTipoConexion() {
		TipoConexionDAO daoTipoConexion= new TipoConexionDAOImplement();
		try {
			listaTipoConexion = daoTipoConexion.listaTipoConexion();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			LOG.error("Error al obtener Lista Tipo Conexion: " + e.getMessage());
		}
		return listaTipoConexion;
	}

	public void setListaTipoConexion(List<TipoConexion> listaTipoConexion) {
		this.listaTipoConexion = listaTipoConexion;
	}

	public TipoConexion getTipoConexion() {
		return tipoConexion;
	}

	public void setTipoConexion(TipoConexion tipoConexion) {
		this.tipoConexion = tipoConexion;
	}

	public void insertarTipoConexion(){
		TipoConexionDAO daoTipoConexion= new TipoConexionDAOImplement();
		try {
			daoTipoConexion.insertarTipoConexion(tipoConexion);
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
			LOG.error("Error al Insertar Tipo Conexion: " + e.getMessage());
		}
	}
	
	public void eliminarTipoConexion(TipoConexion tipo){
		TipoConexionDAO daoTipoConexion= new TipoConexionDAOImplement();
		try {
			daoTipoConexion.eliminarTipoConexion(tipo);
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
			LOG.error("Error al Eliminar Tipo Conexion: " + e.getMessage());

		}
	}
	private void inicializar(){
		tipoConexion = new TipoConexion();
	}
}

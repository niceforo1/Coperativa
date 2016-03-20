package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import dao.CategoriaConexionDAO;
import dao.impl.CategoriaConexionDAOImplement;
import model.CategoriaConexion;

@ManagedBean(name="categoriaConexionBean")
@ViewScoped
public class CategoriaConexionBean implements Serializable{
	private static final Logger LOG = Logger.getLogger(CategoriaConexionBean.class); 

	private List<CategoriaConexion> listaCategoriaConexion;
	private CategoriaConexion categoriaConexion;
	
	public CategoriaConexionBean() {
		inicializar();
	}

	public List<CategoriaConexion> getListaCategoriaConexion() {
		CategoriaConexionDAO daoCategoriaConexion= new CategoriaConexionDAOImplement();
		try {
			listaCategoriaConexion = daoCategoriaConexion.listaCategoriaConexion();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			LOG.error("Error al obtener Lista Categoria Conexion: "+e.getMessage());
		}
		return listaCategoriaConexion;
	}

	public void setListaCategoriaConexion(
			List<CategoriaConexion> listaCategoriaConexion) {
		this.listaCategoriaConexion = listaCategoriaConexion;
	}

	public CategoriaConexion getCategoriaConexion() {
		return categoriaConexion;
	}

	public void setCategoriaConexion(CategoriaConexion categoriaConexion) {
		this.categoriaConexion = categoriaConexion;
	}

	public void insertarCategoriaConexion(){
		CategoriaConexionDAO daoCategoriaConexion= new CategoriaConexionDAOImplement();
		try {
			daoCategoriaConexion.insertarCategoriaConexion(categoriaConexion);
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
			LOG.error("Error al obtener Insertar Categoria Conexion: "+e.getMessage());

		}
	}
	
	public void eliminarCategoriaConexion(CategoriaConexion categoria){
		CategoriaConexionDAO daoCategoriaConexion= new CategoriaConexionDAOImplement();
		try {
			daoCategoriaConexion.eliminarCategoriaConexion(categoria);
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
			LOG.error("Error al obtener Eliminar Categoria Conexion: "+e.getMessage());

		}
	}
	private void inicializar(){
		categoriaConexion = new CategoriaConexion();
	}
	
}

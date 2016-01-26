package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import dao.ConfiguracionFacturaDAO;
import dao.impl.ConfiguracionFacturaDAOImplement;
import model.ConfiguracionFactura;

@ManagedBean(name="configuracionFacturaBean")
@ViewScoped
public class ConfiguracionFacturaBean implements Serializable{

	private List<ConfiguracionFactura> listaConfiguracionFactura;
	private ConfiguracionFactura configuracionFactura;
	private ConfiguracionFactura configuracionFacturaEditar;
	
	public ConfiguracionFacturaBean() {
		inicializar();
	}

	public List<ConfiguracionFactura> getListaConfiguracionFactura() {
		ConfiguracionFacturaDAO daoConfiguracionFactura= new ConfiguracionFacturaDAOImplement();
		try {
			listaConfiguracionFactura = daoConfiguracionFactura.obtenerConfiguracionFactura();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return listaConfiguracionFactura;
	}

	public void setListaConfiguracionFactura(
			List<ConfiguracionFactura> listaConfiguracionFactura) {
		this.listaConfiguracionFactura = listaConfiguracionFactura;
	}

	public ConfiguracionFactura getConfiguracionFactura() {
		return configuracionFactura;
	}

	public void setConfiguracionFactura(ConfiguracionFactura configuracionFactura) {
		this.configuracionFactura = configuracionFactura;
	}
	
	public ConfiguracionFactura getConfiguracionFacturaEditar() {
		return configuracionFacturaEditar;
	}

	public void setConfiguracionFacturaEditar(
			ConfiguracionFactura configuracionFacturaEditar) {
		this.configuracionFacturaEditar = configuracionFacturaEditar;
	}

	public void insertarConfiguracionLectura(){
		ConfiguracionFacturaDAO daoConfiguracionFactura= new ConfiguracionFacturaDAOImplement();
		try {
			daoConfiguracionFactura.insertarConfiguracionFactura(configuracionFactura);
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
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		try {
			ConfiguracionFacturaDAO daoConfiguracionFactura= new ConfiguracionFacturaDAOImplement();
		//	System.out.println("HOLA: " + configuracionFactura.getId() + " " + configuracionFactura.getTramo1());
			
			//daoConfiguracionFactura.modificarConfiguracionFactura(configFact);
			//System.out.println("HOLA HOLA"  + " " + ((ConfiguracionFactura) event.getObject()).getId() + " " +((ConfiguracionFactura) event.getObject()).getTramo1());
			
			System.out.println("HOLA: " + " " + configuracionFacturaEditar.getTramo1());
			
			daoConfiguracionFactura.modificarConfiguracionFactura(configuracionFacturaEditar);
	        
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se editó correctamente."));
			//inicializar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
		}
    }
	
	public void editarConfiguracion(ConfiguracionFactura conf){
		this.configuracionFacturaEditar = conf;
	}
	
	public boolean existeConfiguracion(){
		
		boolean existe;
		
		ConfiguracionFacturaDAO daoConfiguracionFactura= new ConfiguracionFacturaDAOImplement();
		try {
			listaConfiguracionFactura = daoConfiguracionFactura.obtenerConfiguracionFactura();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		
		if(listaConfiguracionFactura.isEmpty()){
			existe = false;
		}
		else{
			existe = true;
		}
		
		return existe;
	}
	
	private void inicializar(){
		configuracionFactura = new ConfiguracionFactura();
		configuracionFacturaEditar = new ConfiguracionFactura();
	}
}

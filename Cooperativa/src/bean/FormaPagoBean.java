package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.FormaPagoDAO;
import dao.impl.FormaPagoDAOImplement;
import model.FormaPago;

@ManagedBean(name="formaPagoBean")
@ViewScoped
public class FormaPagoBean implements Serializable	{

	private List<FormaPago> listaFormaPago;
	private FormaPago formaPago;
	
	public FormaPagoBean() {
		inicializar();
	}

	public List<FormaPago> getListaFormaPago() {
		FormaPagoDAO daoFormaPago = new FormaPagoDAOImplement();
		try {
			listaFormaPago = daoFormaPago.listaFormaPago();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return listaFormaPago;
	}

	public void setListaFormaPago(List<FormaPago> listaFormaPago) {
		this.listaFormaPago = listaFormaPago;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public void insertarFormaPago(){
		FormaPagoDAO daoFormaPago = new FormaPagoDAOImplement();
		try {
			daoFormaPago.insertarFormaPago(formaPago);
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
	
	public void eliminarFormaPago(FormaPago forma){
		FormaPagoDAO daoFormaPago = new FormaPagoDAOImplement();
		try {
			daoFormaPago.eliminarFormaPago(forma);
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
		}
	}
	private void inicializar(){
		formaPago = new FormaPago();
	}
}

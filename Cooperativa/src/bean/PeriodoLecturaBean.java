package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.EstadoPeriodoDAO;
import dao.PeriodoLecturaDAO;
import dao.impl.EstadoPeriodoDAOImplement;
import dao.impl.PeriodoLecturaDAOImplement;
import model.PeriodoLectura;

@ManagedBean(name = "periodoLecturaBean")
@ViewScoped
public class PeriodoLecturaBean implements Serializable {
	private PeriodoLectura periodoLectura;
	private List<PeriodoLectura> lstPeriodoLectura;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public PeriodoLecturaBean() {
		inicializar();
	}

	public PeriodoLectura getPeriodoLectura() {
		return periodoLectura;
	}

	public void setPeriodoLectura(PeriodoLectura periodoLectura) {
		this.periodoLectura = periodoLectura;
	}

	public List<PeriodoLectura> getLstPeriodoLectura() {
		return lstPeriodoLectura;
	}

	public void setLstPeriodoLectura(List<PeriodoLectura> lstPeriodoLectura) {
		this.lstPeriodoLectura = lstPeriodoLectura;
	}
	
	public void insertarPeriodoLectura() {
		EstadoPeriodoDAO estadoPeriodoDAO =new EstadoPeriodoDAOImplement();
		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		try {
			periodoLectura.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo("EN PROCESO"));
			periodoLectura.setUsuario(login.getUsuario());
			periodoLectura.setFechaUltimaMod(Calendar.getInstance().getTime()); 
			periodoLecturaDAO.insertarPeriodoLectura(periodoLectura);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "El Período se agregó correctamente."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
		}
		
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	private void inicializar() {
		periodoLectura = new PeriodoLectura();
	}
}

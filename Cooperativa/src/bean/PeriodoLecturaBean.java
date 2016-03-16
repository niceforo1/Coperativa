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
import dao.EstadoSocioDAO;
import dao.PeriodoLecturaDAO;
import dao.SocioDAO;
import dao.impl.EstadoPeriodoDAOImplement;
import dao.impl.EstadoSocioDAOImplement;
import dao.impl.PeriodoLecturaDAOImplement;
import dao.impl.SocioDAOImplement;
import model.PeriodoLectura;
import model.Socio;
import model.SociosTransacciones;

@ManagedBean(name = "periodoLecturaBean")
@ViewScoped
public class PeriodoLecturaBean implements Serializable {
	private PeriodoLectura periodoLectura;
	private List<PeriodoLectura> lstPeriodoLectura;
	private PeriodoLectura periodoLecturaEnProceso;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public PeriodoLecturaBean() {
		inicializar();
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public PeriodoLectura getPeriodoLectura() {
		return periodoLectura;
	}

	public void setPeriodoLectura(PeriodoLectura periodoLectura) {
		this.periodoLectura = periodoLectura;
	}

	public List<PeriodoLectura> getLstPeriodoLectura() {
		try {
			PeriodoLecturaDAO daoPeriodoLectura = new PeriodoLecturaDAOImplement();
			lstPeriodoLectura = daoPeriodoLectura.listaPeriodoLectura();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return lstPeriodoLectura;
	}

	public void setLstPeriodoLectura(List<PeriodoLectura> lstPeriodoLectura) {
		this.lstPeriodoLectura = lstPeriodoLectura;
	}

	public PeriodoLectura getPeriodoLecturaEnProceso() {
		try {
			PeriodoLecturaDAO daoPeriodoLectura = new PeriodoLecturaDAOImplement();
			periodoLecturaEnProceso = daoPeriodoLectura.buscarPeriodoLecturaAbierto();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return periodoLecturaEnProceso;
	}

	public void setPeriodoLecturaEnProceso(PeriodoLectura periodoLecturaEnProceso) {
		this.periodoLecturaEnProceso = periodoLecturaEnProceso;
	}

	public boolean periodoAbiertoCons(){
		if(periodoLecturaEnProceso!= null){
			return true;
		}else{
			return false;
		}		
	}
	public void insertarPeriodoLectura() {
		EstadoPeriodoDAO estadoPeriodoDAO = new EstadoPeriodoDAOImplement();
		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		List<PeriodoLectura> lstPer = new ArrayList<PeriodoLectura>();
		boolean existe = false;

		try {
			lstPer = periodoLecturaDAO.listaPeriodoLectura();

			for (PeriodoLectura per : lstPer) {
				if ((per.getAnio() == periodoLectura.getAnio()) && (per.getMes() == periodoLectura.getMes())) {
					existe = true;
				}
			}

			if (existe == false) {
				periodoLectura.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo("EN PROCESO"));
				periodoLectura.setUsuarioAltaPeriodo(login.getUsuario());
				periodoLectura.setFechaUltimaMod(Calendar.getInstance().getTime());
				periodoLecturaDAO.insertarPeriodoLectura(periodoLectura);

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Correctamente", "El Período se agregó correctamente."));
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: Período ya cargado. "));
			}
			inicializar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
		}

	}

	public void cambiarEstadoPeriodo(String estado, PeriodoLectura periodo) {
		EstadoPeriodoDAO estadoPeriodoDAO = new EstadoPeriodoDAOImplement();
		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		try {
			periodo.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo(estado));

			// se cambia ultima fecha mod
			periodo.setFechaUltimaMod(Calendar.getInstance().getTime());

			periodoLecturaDAO.modificarPeriodoLectura(periodo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Correctamente", "El período se modifico correctamente."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
		}
	}

	private void inicializar() {
		getPeriodoLecturaEnProceso();
		periodoLectura = new PeriodoLectura();
	}
}

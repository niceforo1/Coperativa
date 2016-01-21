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
import dao.PeriodoFacturacionDAO;
import dao.PeriodoLecturaDAO;
import dao.SocioDAO;
import dao.impl.EstadoPeriodoDAOImplement;
import dao.impl.EstadoSocioDAOImplement;
import dao.impl.PeriodoFacturacionDAOImplement;
import dao.impl.PeriodoLecturaDAOImplement;
import dao.impl.SocioDAOImplement;
import model.PeriodoFacturacion;
import model.PeriodoLectura;
import model.Socio;
import model.SociosTransacciones;

@ManagedBean(name = "periodoFacturacionBean")
@ViewScoped
public class PeriodoFacturacionBean implements Serializable {
	private PeriodoFacturacion periodoFacturacion;
	private List<PeriodoFacturacion> lstPeriodoFacturacion;
	private PeriodoFacturacion periodoFacturacionEnProceso;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public PeriodoFacturacionBean() {
		inicializar();
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public PeriodoFacturacion getPeriodoFacturacion() {
		return periodoFacturacion;
	}

	public void setPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) {
		this.periodoFacturacion = periodoFacturacion;
	}

	public List<PeriodoFacturacion> getLstPeriodoFacturacion() {
		try {
			PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
			lstPeriodoFacturacion = periodoFacturacionDAO.listaPeriodoFacturacion();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return lstPeriodoFacturacion;
		
	}

	public void setLstPeriodoFacturacion(
			List<PeriodoFacturacion> lstPeriodoFacturacion) {
		this.lstPeriodoFacturacion = lstPeriodoFacturacion;
	}
	
	public PeriodoFacturacion getPeriodoFacturacionEnProceso() {
		try {
			PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
			periodoFacturacionEnProceso = periodoFacturacionDAO.buscarPeriodoFacturacionAbierto();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		}
		return periodoFacturacionEnProceso;
	}

	public void setPeriodoFacturacionEnProceso(
			PeriodoFacturacion periodoFacturacionEnProceso) {
		this.periodoFacturacionEnProceso = periodoFacturacionEnProceso;
	}

	public boolean periodoAbiertoCons(){
	if(periodoFacturacionEnProceso != null){
		return true;
	}else{
		return false;
	}		
	}
	
	public void insertarPeriodoFacturacion() {
		EstadoPeriodoDAO estadoPeriodoDAO = new EstadoPeriodoDAOImplement();
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		List<PeriodoFacturacion> lstPer = new ArrayList<PeriodoFacturacion>();
		boolean existe = false;

		try {

			lstPer = periodoFacturacionDAO.listaPeriodoFacturacion();
			
			for (PeriodoFacturacion per : lstPer) {
				System.out.println("HOLA: " + per.getAnio() + "-" + per.getMes() + " " + periodoFacturacion.getAnio() + "-"
				+ periodoFacturacion.getMes());
				if ((per.getAnio() == periodoFacturacion.getAnio()) && (per.getMes() == periodoFacturacion.getMes())) {
					existe = true;
				}
			}
			
			if (existe == false) {
			periodoFacturacion.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo("EN PROCESO"));
			periodoFacturacionDAO.insertarPeriodoFacturacion(periodoFacturacion);
			
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

	public void cambiarEstadoPeriodo(String estado, PeriodoFacturacion periodo) {
		EstadoPeriodoDAO estadoPeriodoDAO = new EstadoPeriodoDAOImplement();
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		try {
			periodo.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo(estado));

			// se cambia ultima fecha mod
			//periodo.setFechaUltimaMod(Calendar.getInstance().getTime());

			periodoFacturacionDAO.modificarPeriodoFacturacion(periodo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Correctamente", "El período se modifico correctamente."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
		}
	}

	private void inicializar() {
		getPeriodoFacturacionEnProceso();
		periodoFacturacion = new PeriodoFacturacion();
	}
}

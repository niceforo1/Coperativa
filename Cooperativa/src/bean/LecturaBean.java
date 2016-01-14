package bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.sun.faces.context.flash.ELFlash;

import dao.ConexionDAO;
import dao.ConfiguracionLecturaDAO;
import dao.LecturaDAO;
import dao.PeriodoLecturaDAO;
import dao.impl.ConexionDAOImplement;
import dao.impl.ConfiguracionLecturaDAOImplement;
import dao.impl.LecturaDAOImplement;
import dao.impl.PeriodoLecturaDAOImplement;
import model.Conexion;
import model.ConfiguracionLectura;
import model.Lectura;
import model.PeriodoLectura;

@ManagedBean(name = "lecturaBean")
@ViewScoped
public class LecturaBean implements Serializable {

	private Lectura lectura;
	private String mensajeBlur;
	private long conexionID;
	private Conexion conexion;
	private PeriodoLectura periodo;
	private String lecturero;
	private Date fechaRegistro;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public LecturaBean() {
		inicializar();
	}

	public String getMensajeBlur() {
		return mensajeBlur;
	}

	public void setMensajeBlur(String mensajeBlur) {
		this.mensajeBlur = mensajeBlur;
	}

	public Lectura getLectura() {
		return lectura;
	}

	public void setLectura(Lectura lectura) {
		this.lectura = lectura;
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public long getConexionID() {
		return conexionID;
	}

	public void setConexionID(long conexionID) {
		this.conexionID = conexionID;
	}

	public PeriodoLectura getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoLectura periodo) {
		this.periodo = periodo;
	}

	public String getLecturero() {
		return lecturero;
	}

	public void setLecturero(String lecturero) {
		this.lecturero = lecturero;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void retornarConexion() {
		ConexionDAO conexionDAO = new ConexionDAOImplement();
		try {
			conexion = conexionDAO.buscarConexionID(conexionID);
			Collections.sort(conexion.getLecturas());
			lectura.setLecturaAnterior(conexion.getLecturas().get(0).getLecturaActual());
			if (conexion == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encuentra la conexión"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al buscar conexión: " + e.getMessage()));
		}

	}

	public void mostrarAviso() {
		if (lectura.getLecturaActual() > 10) {
			mensajeBlur = " Verifique este campo. ";
		} else {
			mensajeBlur = "";
		}
	}

	public void insertarLectura() {
		lectura.setFechaGeneracion(Calendar.getInstance().getTime());
		lectura.setPeriodoLectura(periodo);
		lectura.setUsuario(login.getUsuario());
		lectura.setLecturero(lecturero);
		lectura.setFechaRegistroLectura(fechaRegistro);
		// LecturaDAO lecturaDAO = new LecturaDAOImplement();
		try {
			ConexionDAO conexionDAO = new ConexionDAOImplement();
			conexion.getLecturas().add(lectura);
			conexionDAO.modificarConexion(conexion);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al Insertar Lectura: " + e.getMessage()));
		}
		lectura = new Lectura();
		conexion = new Conexion();
		conexionID = 0;
		mensajeBlur = "";
	}

	public String revisarLectura() {
		/*
		 * ConfiguracionLecturaDAO daoConfiguracionLectura = new
		 * ConfiguracionLecturaDAOImplement();
		 * 
		 * ConfiguracionLectura confLect;
		 * 
		 * String resultado = "";
		 * 
		 * PeriodoLecturaDAO periodoLecturaDAO = new
		 * PeriodoLecturaDAOImplement();
		 * 
		 * try { System.out.println(periodoLecturaId);
		 * lectura.setPeriodoLectura(periodoLecturaDAO.buscarPeriodoLecturaId(
		 * periodoLecturaId));
		 * 
		 * // OBTENER LECTURA ANTERIOR long anio =
		 * lectura.getPeriodoLectura().getAnio(); long mes =
		 * lectura.getPeriodoLectura().getMes(); if (mes == 1) { anio = anio -
		 * 1; mes = 12; } else { mes = mes - 1; }
		 * 
		 * for (Lectura lect : conexSeleccionada.getLecturas()) { if
		 * ((lect.getPeriodoLectura().getAnio() == anio) &&
		 * (lect.getPeriodoLectura().getMes() == mes)) {
		 * lectura.setLecturaAnterior(lect.getLecturaActual()); }
		 * 
		 * }
		 * 
		 * confLect =
		 * daoConfiguracionLectura.listaConfiguracionLectura().get(0);
		 * 
		 * if ((lectura.getLecturaActual() - lectura.getLecturaAnterior()) >
		 * confLect.getMonto()) { resultado = "Lectura Correcta?"; } else {
		 * resultado = "Lectura Correcta"; }
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * return resultado;
		 */
		return "";
	}

	public String pasarPagina(Conexion conexion) {
		String BEAN_KEY = "conex";
		ELFlash.getFlash().put(BEAN_KEY, conexion);
		return "/conexiones/agregarLectura.xhtml?faces-redirect=true";
	}

	/*
	 * public void obtenerConex() { inicializar(); String BEAN_KEY = "conex"; //
	 * ELFlash.getFlash().put(BEAN_KEY, ELFlash.getFlash().get(BEAN_KEY));
	 * this.conexSeleccionada = (Conexion) ELFlash.getFlash().get(BEAN_KEY); }
	 */

	public void verificarLectura() {
		Boolean lect = false;
		if (!(ELFlash.getFlash().get("lectura") == null)) {
			lect = (Boolean) ELFlash.getFlash().get("lectura");
			if (lect != null) {
				if (lect != false) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Lectura registrada correctamente."));
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error", "Error al procesar: Lectura actual debe ser mayor a 0"));
				}
			}
		}
	}

	private void inicializar() {
		lectura = new Lectura();
		conexion = new Conexion();
		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		try {
			periodo = periodoLecturaDAO.buscarPeriodoLecturaAbierto();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe Período Abierto."));
		}
	}

}

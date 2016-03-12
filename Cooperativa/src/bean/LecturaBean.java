package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
	private Long conexBus;
	private Conexion conexionBusqueda;
	private Conexion conexion;
	private PeriodoLectura periodo;
	private String lecturero;
	private Date fechaRegistro;
	private List<Lectura> lstLectCon;
	private boolean esCanon;
	private boolean permiteCarga;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public LecturaBean() {
		inicializar();
	}

	public Long getConexBus() {
		return conexBus;
	}

	public void setConexBus(Long conexBus) {
		this.conexBus = conexBus;
	}

	public List<Lectura> getLstLectCon() {
		return lstLectCon;
	}

	public void setLstLectCon(List<Lectura> lstLectCon) {
		this.lstLectCon = lstLectCon;
	}

	public Conexion getConexionBusqueda() {
		return conexionBusqueda;
	}

	public void setConexionBusqueda(Conexion conexionBusqueda) {
		this.conexionBusqueda = conexionBusqueda;
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

	public boolean isEsCanon() {
		return esCanon;
	}

	public void setEsCanon(boolean esCanon) {
		this.esCanon = esCanon;
	}

	public boolean isPermiteCarga() {
		return permiteCarga;
	}

	public void setPermiteCarga(boolean permiteCarga) {
		this.permiteCarga = permiteCarga;
	}

	public void retornarConexion() {
		ConexionDAO conexionDAO = new ConexionDAOImplement();
		String canon = "CANON";
		permiteCarga =false;

		try {
			conexion = conexionDAO.buscarConexionID(conexionID);

			if (conexion.getLecturas() != null) {
				Collections.sort(conexion.getLecturas());
			}

			try {
				lectura.setLecturaAnterior(conexion.getLecturas().get(0).getLecturaActual());
			} catch (Exception e) {
				lectura.setLecturaAnterior(0);
			}
			
			if(conexion.getLecturas().size() > 0){
				if(conexion.getLecturas().get(0).getPeriodoLectura().getId().equals(periodo.getId())){
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ya se encuentra una lectura cargada para este período."));

					permiteCarga = true;
				}				
			}

			lectura.setConexion(conexion);

			if (conexion == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encuentra la conexión"));
			}

			if (conexion.getCategoriaConexion().getDescripcion().equals(canon)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La conexión es de tipo CANON"));
				permiteCarga = true;
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al buscar conexión: " + e.getMessage()));
		}

	}

	public void mostrarAviso() {
		ConfiguracionLecturaDAO configuracionLecturaDAO = new ConfiguracionLecturaDAOImplement();
		ConfiguracionLectura confLectura = null;
		try {
			confLectura = configuracionLecturaDAO.obtenerConfiguracionLectura();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al buscar Configuración Lectura: " + e.getMessage()));
		}
		if ((lectura.getLecturaActual() - lectura.getLecturaAnterior()) > confLectura.getMonto()) {
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
		lectura.setConexion(conexion);

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
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se agregó correctamente."));
	}

	private void inicializar() {
		lectura = new Lectura();
		conexion = new Conexion();
		esCanon = false;
		permiteCarga = false;
		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		try {
			periodo = periodoLecturaDAO.buscarPeriodoLecturaAbierto();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe Período Abierto."));
		}
	}

	public void obtenerConexionBusq() {
		ConexionDAO conexionDAO = new ConexionDAOImplement();
		LecturaDAO lecturaDAO = new LecturaDAOImplement();
		try {
			conexionBusqueda = new Conexion();
			lstLectCon = new ArrayList<Lectura>();
			conexionBusqueda = conexionDAO.buscarConexionID(conexBus);
			lstLectCon = lecturaDAO.buscarLecturasPorConexion(conexionBusqueda);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
		}
	}

}

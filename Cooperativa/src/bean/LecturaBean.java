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

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

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
	private static final Logger LOG = Logger.getLogger(LecturaBean.class);
	private Lectura lectura;
	private String mensajeBlur;
	private String mensajeBlur2;
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
	private List<Lectura> lstListaLecturas;
	private Long periodoLectID;
	private boolean resetMed;

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

	public List<Lectura> getLstListaLecturas() {
		return lstListaLecturas;
	}

	public void setLstListaLecturas(List<Lectura> lstListaLecturas) {
		this.lstListaLecturas = lstListaLecturas;
	}

	public Long getPeriodoLectID() {
		return periodoLectID;
	}

	public void setPeriodoLectID(Long periodoLectID) {
		this.periodoLectID = periodoLectID;
	}

	public boolean isResetMed() {
		return resetMed;
	}

	public void setResetMed(boolean resetMed) {
		this.resetMed = resetMed;
	}

	public String getMensajeBlur2() {
		return mensajeBlur2;
	}

	public void setMensajeBlur2(String mensajeBlur2) {
		this.mensajeBlur2 = mensajeBlur2;
	}

	public void retornarConexion() {
		ConexionDAO conexionDAO = new ConexionDAOImplement();
		String canon = "CANON";
		permiteCarga = false;

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

			if (conexion.getLecturas().size() > 0) {
				if(periodo!= null){
					if (conexion.getLecturas().get(0).getPeriodoLectura().getId().equals(periodo.getId())) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Error", "Ya se encuentra una lectura cargada para este período."));
						permiteCarga = true;
					}
				}else{
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe Período Abierto."));
					permiteCarga = true;
					return;
				}				
			}

			if (conexion == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encuentra la conexión"));
			}

			if (conexion.getCategoriaConexion().getDescripcion().equals(canon)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La conexión es de tipo CANON."));
				permiteCarga = true;
			}
			if (conexion.getEstadoConexion().getDescripcion().trim().equals("BAJA")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La conexión es esta en estado BAJA."));
				permiteCarga = true;
			}
			lectura.setConexion(conexion);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al buscar conexión: " + e.getMessage()));
			LOG.error("Error al Retornar Conexion: " + e.getMessage());
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
			LOG.error("Error al Mostrar Aviso: " + e.getMessage());
		}
		if ((lectura.getLecturaActual() - lectura.getLecturaAnterior()) > confLectura.getMonto()) {
			mensajeBlur = "Verifique este campo. ";
		} else {
			mensajeBlur = "";
		}
		if(lectura.getLecturaActual()< lectura.getLecturaAnterior()){
			resetMed = true;
			mensajeBlur +=  "Se considera Reseteo de Medidor.";
			int consumo = (int) ((Integer.parseInt("9999")-lectura.getLecturaAnterior()) + lectura.getLecturaActual());
			mensajeBlur2 = ", se va a considerar reseteo Medidor, y el consumo será de: "+consumo;
		}else{
			resetMed= false;
			mensajeBlur2 ="";
			mensajeBlur += "";
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
			LOG.error("Error al Insertar Lectura: " + e.getMessage());
		}
		lectura = new Lectura();
		conexion = new Conexion();
		conexionID = 0;
		mensajeBlur = "";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", "Se agregó correctamente."));
	}

	private void inicializar() {
		periodoLectID = 0L;
		lectura = new Lectura();
		conexion = new Conexion();
		esCanon = false;
		permiteCarga = false;
		resetMed = false;
		lstListaLecturas = null;
		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		try {
			periodo = periodoLecturaDAO.buscarPeriodoLecturaAbierto();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No existe Período Abierto."));
			LOG.error("Error al Buscar Periodo Lectura Abierto: " + e.getMessage());
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
			LOG.error("Error al Obtener Conexion Busq: " + e.getMessage());
		}
	}

	public void consultarLecturas() {
		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		LecturaDAO lecturaDAO = new LecturaDAOImplement();
		try {
			lstListaLecturas = lecturaDAO.buscarLecturasPorPeriodo(periodoLecturaDAO.buscarPeriodoLecturaId(periodoLectID));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al Consultar Lecturas: " + e.getMessage()));
			LOG.error("Error al Consultar Lecturas: " + e.getMessage());
		}
	}

	public void onRowEdit(RowEditEvent event) {
		Lectura lectEdit = (Lectura) event.getObject();
		LecturaDAO lecturaDAO = new LecturaDAOImplement();
		try {
			lecturaDAO.modificarLectura(lectEdit);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al Modificar Lectura: " + e.getMessage()));
			LOG.error("Error al Modificar Lectura: " + e.getMessage());
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Lectura Modificada Correctamente: "));
		LOG.error("Se modifico la lectura: " + lectEdit.getId());
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Cancelado", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}

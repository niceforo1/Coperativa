package bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.sun.faces.context.flash.ELFlash;

import dao.ConexionDAO;
import dao.PeriodosSaldosDAO;
import dao.impl.ConexionDAOImplement;
import dao.impl.PeriodosSaldosDAOImplement;
import model.Conexion;
import model.PeriodosSaldos;

@ManagedBean(name = "consEstDeudaBean")
@ViewScoped
public class ConsultaEstadoDeudaSocioBean {
	private long conexionID;
	private Conexion conexion;
	private Date fechaDesde;
	private Date fechaHasta;
	private Float totalSaldo;
	private List<PeriodosSaldos> lstPeriodosSaldos;
	private PeriodosSaldos perSaldoSeleccionado;
	private List<PeriodosSaldos> lstPeriodosSaldosCobrar;

	public PeriodosSaldos getPerSaldoSeleccionado() {
		return perSaldoSeleccionado;
	}

	public void setPerSaldoSeleccionado(PeriodosSaldos perSaldoSeleccionado) {
		this.perSaldoSeleccionado = perSaldoSeleccionado;
	}

	public ConsultaEstadoDeudaSocioBean() {
		inicializar();
	}

	public List<PeriodosSaldos> getLstPeriodosSaldos() {
		return lstPeriodosSaldos;
	}

	public void setLstPeriodosSaldos(List<PeriodosSaldos> lstPeriodosSaldos) {
		this.lstPeriodosSaldos = lstPeriodosSaldos;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public long getConexionID() {
		return conexionID;
	}

	public void setConexionID(long conexionID) {
		this.conexionID = conexionID;
	}

	public Float getTotalSaldo() {
		return totalSaldo;
	}

	public void setTotalSaldo(Float totalSaldo) {
		this.totalSaldo = totalSaldo;
	}

	public List<PeriodosSaldos> getLstPeriodosSaldosCobrar() {
		return lstPeriodosSaldosCobrar;
	}

	public void setLstPeriodosSaldosCobrar(List<PeriodosSaldos> lstPeriodosSaldosCobrar) {
		this.lstPeriodosSaldosCobrar = lstPeriodosSaldosCobrar;
	}

	public void retornarConexion() {
		ConexionDAO conexionDAO = new ConexionDAOImplement();
		try {
			conexion = conexionDAO.buscarConexionID(conexionID);
			if (conexion == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encuentra la conexión"));
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al buscar conexión: " + e.getMessage()));
		}
	}

	public void retornarPeriodosFiltrados() {
		PeriodosSaldosDAO periodosSaldosDAO = new PeriodosSaldosDAOImplement();
		totalSaldo = 0F;
		try {
			lstPeriodosSaldos = periodosSaldosDAO.buscarPeriodosSaldosConexion(conexionID, fechaDesde, fechaHasta);
			for (PeriodosSaldos per : lstPeriodosSaldos) {
				totalSaldo += per.getSaldo();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al buscar periodo saldos: " + e.getMessage()));
		}
	}

	public String cobrarPeriodos() {
		/*
		 * System.out.println("Cantidad Periodos a cobrar: ");
		 * System.out.println(lstPeriodosSaldosCobrar.size());
		 * System.out.println("//////////////////////////////////"); for
		 * (PeriodosSaldos per : lstPeriodosSaldosCobrar) {
		 * System.out.println(per.toString()); }
		 * System.out.println("//////////////////////////////////");
		 */
		if (lstPeriodosSaldosCobrar.size() > 0) {
			String BEAN_KEY = "periodos";
			ELFlash.getFlash().put(BEAN_KEY, lstPeriodosSaldosCobrar);
			return "/facturacion/cobroPeriodoSaldos.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"No existen periodos seleccionados para cobrar"));
		}
		return "";
	}	

	private void inicializar() {
		conexion = new Conexion();
		conexionID = 0L;
		totalSaldo = 0f;
		lstPeriodosSaldos = new ArrayList<PeriodosSaldos>();
	}
}

package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.sun.faces.context.flash.ELFlash;

import dao.ConexionDAO;
import dao.FacturaDAO;
import dao.PeriodosSaldosDAO;
import dao.impl.ConexionDAOImplement;
import dao.impl.FacturaDAOImplement;
import dao.impl.PeriodosSaldosDAOImplement;
import model.Conexion;
import model.Factura;
import model.PeriodosSaldos;

@ManagedBean(name = "consEstDeudaBean")
@ViewScoped
public class ConsultaEstadoDeudaSocioBean implements Serializable{
	private static final Logger LOG = Logger.getLogger(ConsultaEstadoDeudaSocioBean.class); 

	private long conexionID;
	private Conexion conexion;
	private Date fechaDesde;
	private Date fechaHasta;
	private Double totalSaldo;
	private List<PeriodosSaldos> lstPeriodosSaldos;
	private PeriodosSaldos perSaldoSeleccionado;
	private List<PeriodosSaldos> lstPeriodosSaldosCobrar;
	private List<Factura> facturaPorConexion;

	public ConsultaEstadoDeudaSocioBean() {
		inicializar();
	}

	public List<Factura> getFacturaPorConexion() {
		facturaPorConexion = new ArrayList<Factura>();
		FacturaDAO facturaDAO = new FacturaDAOImplement();
		try {
			facturaPorConexion = facturaDAO.listaFacturaConexion(conexionID);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No puede buscar las factura para la conexión. "+e.getMessage()));
			LOG.error("Error al obtener lista Factura por Conexion: "+ e.getMessage());
		}
		return facturaPorConexion;
	}

	public void setFacturaPorConexion(List<Factura> facturaPorConexion) {
		this.facturaPorConexion = facturaPorConexion;
	}

	public PeriodosSaldos getPerSaldoSeleccionado() {
		return perSaldoSeleccionado;
	}

	public void setPerSaldoSeleccionado(PeriodosSaldos perSaldoSeleccionado) {
		this.perSaldoSeleccionado = perSaldoSeleccionado;
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

	public Double getTotalSaldo() {
		return totalSaldo;
	}

	public void setTotalSaldo(Double totalSaldo) {
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
			LOG.error("Error al Retornar Conexion: "+ e.getMessage());
		}
	}

	public void retornarPeriodosFiltrados() {
		PeriodosSaldosDAO periodosSaldosDAO = new PeriodosSaldosDAOImplement();
		totalSaldo = 0D;
		try {
			lstPeriodosSaldos = periodosSaldosDAO.buscarPeriodosSaldosConexion(conexionID, fechaDesde, fechaHasta);
			for (PeriodosSaldos per : lstPeriodosSaldos) {
				totalSaldo += per.getSaldo();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al buscar periodo saldos: " + e.getMessage()));
			LOG.error("Error al Retornar Periodos Filtrados: "+ e.getMessage());
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
			LOG.error("Error No existen periodos seleccionados para cobrar: ");
		}
		return "";
	}

	private void inicializar() {
		conexion = new Conexion();
		conexionID = 0L;
		totalSaldo = 0D;
		lstPeriodosSaldos = new ArrayList<PeriodosSaldos>();
	}
}

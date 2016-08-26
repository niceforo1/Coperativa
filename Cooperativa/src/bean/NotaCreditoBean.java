package bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import dao.ConceptoFacturacionDAO;
import dao.ConexionesSaldosDAO;
import dao.GeneradorNotaCredADAO;
import dao.GeneradorNotaCredBDAO;
import dao.NotaCreditoDAO;
import dao.PeriodoFacturacionDAO;
import dao.PeriodosSaldosDAO;
import dao.TipoComprobanteDAO;
import dao.impl.ConceptoFacturacionDAOImplement;
import dao.impl.ConexionesSaldosDAOImplement;
import dao.impl.GeneradorNotaCredADAOImplement;
import dao.impl.GeneradorNotaCredBDAOImplement;
import dao.impl.NotaCreditoDAOImplement;
import dao.impl.PeriodoFacturacionDAOImplement;
import dao.impl.PeriodosSaldosDAOImplement;
import dao.impl.TipoComprobanteDAOImplement;
import model.Conexion;
import model.ConexionesSaldos;
import model.NotaCredito;
import model.PeriodoFacturacion;
import model.PeriodosSaldos;
import model.generadores.GenNotaCreditoA;
import model.generadores.GenNotaCreditoB;

@ManagedBean(name = "notaCreditoBean")
@ViewScoped
public class NotaCreditoBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(NotaCreditoBean.class);

	private Conexion conexion;
	private boolean habilitado;
	private boolean notaCredX;
	private Long tipoNotaCredito;
	private Long conceptoID;
	private String observaciones;
	private Double importeX;
	private Long peridoIdFactEleg;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public NotaCreditoBean() {
		inicializar();
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Long getConceptoID() {
		return conceptoID;
	}

	public void setConceptoID(Long conceptoID) {
		this.conceptoID = conceptoID;
	}

	public Long getTipoNotaCredito() {
		return tipoNotaCredito;
	}

	public void setTipoNotaCredito(Long tipoNotaCredito) {
		this.tipoNotaCredito = tipoNotaCredito;
	}

	public boolean isNotaCredX() {
		return notaCredX;
	}

	public void setNotaCredX(boolean notaCredX) {
		this.notaCredX = notaCredX;
	}

	public void onRowSelect(SelectEvent event) {
		conexion = (Conexion) event.getObject();
		habilitado = true;
	}

	public void onRowUnselect(UnselectEvent event) {
		conexion = new Conexion();
		habilitado = false;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public Double getImporteX() {
		return importeX;
	}

	public void setImporteX(Double importeX) {
		this.importeX = importeX;
	}

	public Long getPeridoIdFactEleg() {
		return peridoIdFactEleg;
	}

	public void setPeridoIdFactEleg(Long peridoIdFactEleg) {
		this.peridoIdFactEleg = peridoIdFactEleg;
	}

	
	public void generarNotaCredito() {
		TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAOImplement();
		NotaCredito notaCredito = new NotaCredito();
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		ConceptoFacturacionDAO conceptoFacturacionDAO = new ConceptoFacturacionDAOImplement();
		NotaCreditoDAO notaCreditoDAO = new NotaCreditoDAOImplement();
		ConexionesSaldosDAO conexionesSaldosDAO = new ConexionesSaldosDAOImplement();
		PeriodosSaldosDAO periodosSaldosDAO = new PeriodosSaldosDAOImplement();

		try {
			PeriodoFacturacion perFact = periodoFacturacionDAO.buscarPeriodoFacturacionId(peridoIdFactEleg);
			notaCredito.setAnio(perFact.getAnio());
			notaCredito.setCesp(perFact.getPeriodoCesp().getCesp());
			notaCredito.setConexion(conexion);
			notaCredito.setFechaEmision(new Date());
			notaCredito.setFechaVtoCesp(perFact.getPeriodoCesp().getFechaVtoCesp());
			notaCredito.setMes(perFact.getMes());
			notaCredito.setObservaciones(observaciones);
			if (notaCredX) {
				notaCredito.setTipoComprobante(tipoComprobanteDAO.buscarTipoComprobanteId(4L));
				notaCredito.setIva(0D);
				notaCredito.setImporte(importeX);
			} else {
				notaCredito.setConceptoFacturacion(conceptoFacturacionDAO.buscarConceptoFacturacionId(conceptoID));
				if (conexion.getCondicionIva().getId().equals(4L)) {
					notaCredito.setTipoComprobante(tipoComprobanteDAO.buscarTipoComprobanteId(1L));
					GeneradorNotaCredADAO generadorNotaCredADAO = new GeneradorNotaCredADAOImplement();
					notaCredito.setNumeroNota(generadorNotaCredADAO.insertarNotaCredA(new GenNotaCreditoA()).toString());
				} else {
					notaCredito.setTipoComprobante(tipoComprobanteDAO.buscarTipoComprobanteId(2L));
					GeneradorNotaCredBDAO generadorNotaCredBDAO = new GeneradorNotaCredBDAOImplement();
					notaCredito.setNumeroNota(generadorNotaCredBDAO.insertarNotaCredB(new GenNotaCreditoB()).toString());
				}
				notaCredito.setImporte(importeX/notaCredito.getConceptoFacturacion().getAlicuotaIva());
				notaCredito.setIva(importeX-notaCredito.getImporte());
				/*notaCredito.setIva((notaCredito.getConceptoFacturacion().getAlicuotaIva()
									* importeX) / 100);
				if (notaCredito.getConceptoFacturacion().getId().equals(1)) {
					notaCredito.setIva((notaCredito.getConceptoFacturacion().getMontoPrecio()
							* notaCredito.getConexion().getSocio().getCondicionIva().getPorcentaje()) / 100);
				} else {
					notaCredito.setIva((notaCredito.getConceptoFacturacion().getMontoPrecio()
							* notaCredito.getConexion().getSocio().getCondicionIva().getIvaOtrosConceptos()) / 100);
				}*/
				notaCredito.setImporteTotal(importeX);
				notaCredito.setImporte(importeX - notaCredito.getIva());
			}			
			notaCredito.setUsuario(login.getUsuario());
			notaCreditoDAO.insertarNotaCredito(notaCredito);

			ConexionesSaldos conSaldo = null;
			try {
				conSaldo = conexionesSaldosDAO.buscarConexionesSaldosConexion(notaCredito.getConexion().getId());
			} catch (Exception ex) {
				LOG.error("Error al obtener Saldo Conexion: " + ex.getMessage());
				throw new Exception("Error al obtener Saldo Conexion: " + ex.getMessage());
			}
			try {
				if (conSaldo != null) {
					conSaldo.setSaldoTotal(conSaldo.getSaldoTotal() + notaCredito.getImporteTotal());
					conSaldo.setUltimoVencRegistrado(notaCredito.getFechaEmision());
					conexionesSaldosDAO.modificarConexionesSaldos(conSaldo);
				} else {
					conSaldo = new ConexionesSaldos();
					conSaldo.setConexion(notaCredito.getConexion());
					conSaldo.setUltimoVencRegistrado(notaCredito.getFechaEmision());
					conSaldo.setSaldoTotal(0F + notaCredito.getImporteTotal());
					conexionesSaldosDAO.insertarConexionesSaldos(conSaldo);
				}
			} catch (Exception ex) {
				LOG.error("Error modificar/grabar Saldo Conexion: " + ex.getMessage());
				throw new Exception("Error modificar/grabar Saldo Conexion: " + ex.getMessage());
			}
			try {
				PeriodosSaldos perSaldo = null;
				try {
					perSaldo = periodosSaldosDAO.buscarPeriodosSaldosMesAnio(notaCredito.getConexion().getId(),
							notaCredito.getMes(), notaCredito.getAnio());
				} catch (Exception ex) {

				}
				if (perSaldo != null) {
					perSaldo.setConexion(notaCredito.getConexion());
					perSaldo.setMes(notaCredito.getMes());
					perSaldo.setAnio(notaCredito.getAnio());
					perSaldo.setFechaVencimiento(notaCredito.getFechaEmision());
					perSaldo.setConsumo(0);
					perSaldo.setSaldo(perSaldo.getSaldo() + notaCredito.getImporteTotal());
					periodosSaldosDAO.modificarPeriodosSaldos(perSaldo);
				} else {
					perSaldo = new PeriodosSaldos();
					perSaldo.setConexion(notaCredito.getConexion());
					perSaldo.setMes(notaCredito.getMes());
					perSaldo.setAnio(notaCredito.getAnio());
					perSaldo.setFechaVencimiento(notaCredito.getFechaEmision());
					perSaldo.setConsumo(0);
					perSaldo.setSaldo(0F + notaCredito.getImporteTotal());
					periodosSaldosDAO.insertarPeriodosSaldos(perSaldo);
				}
			} catch (Exception ex) {
				LOG.error("Error modificar/grabar Saldo Periodo Conexion: " + ex.getMessage());
				throw new Exception("Error modificar/grabar Saldo Periodo Conexion: " + ex.getMessage());
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Nota de Crédito generada correctamente. "));
			inicializar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void inicializar() {
		conexion = new Conexion();
		habilitado = false;
		tipoNotaCredito = null;
		conceptoID = 0L;
		observaciones = null;
		notaCredX = false;
		importeX = null;
		peridoIdFactEleg = null;
	}
}

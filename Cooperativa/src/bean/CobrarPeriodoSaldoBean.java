package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import com.sun.faces.context.flash.ELFlash;

import dao.FacturaDAO;
import dao.FormaPagoDAO;
import dao.NotaDebitoDAO;
import dao.PeriodoCespDAO;
import dao.ReciboDAO;
import dao.TipoComprobanteDAO;
import dao.impl.FacturaDAOImplement;
import dao.impl.FormaPagoDAOImplement;
import dao.impl.NotaDebitoDAOImplement;
import dao.impl.PeriodoCespDAOImplement;
import dao.impl.ReciboDAOImplement;
import dao.impl.TipoComprobanteDAOImplement;
import model.Factura;
import model.FormaPago;
import model.NotaDebito;
import model.PeriodoCesp;
import model.PeriodosSaldos;
import model.Recibo;
import model.ReciboItem;
import model.TipoComprobante;

@ManagedBean(name = "cobrarPeriodoSaldoBean")
@ViewScoped
public class CobrarPeriodoSaldoBean implements Serializable {
	private List<PeriodosSaldos> lstPeriodosSaldos;
	private Float subTotal;
	private Float interesGlobal;
	private Float total;
	private String tipoPago;
	private long formaPagoID;
	final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; // Milisegundos al día

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	public List<PeriodosSaldos> getLstPeriodosSaldos() {
		return lstPeriodosSaldos;
	}

	public void setLstPeriodosSaldos(List<PeriodosSaldos> lstPeriodosSaldos) {
		this.lstPeriodosSaldos = lstPeriodosSaldos;
	}

	public Float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	public Float getInteresGlobal() {
		return interesGlobal;
	}

	public void setInteresGlobal(Float interesGlobal) {
		this.interesGlobal = interesGlobal;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Long getFormaPagoID() {
		return formaPagoID;
	}

	public void setFormaPagoID(Long formaPagoID) {
		this.formaPagoID = formaPagoID;
	}

	private void inicializar() {
		total = 0F;
		subTotal = 0F;
		interesGlobal = 0F;
		tipoPago = null;
		//formaPagoID = null;
		tipoPago = "";
	}

	public void cobrar() {
		PeriodoCespDAO cespDAO = new PeriodoCespDAOImplement();
		FormaPagoDAO formaPagoDAO =new FormaPagoDAOImplement();
		FormaPago formaPago = null;
		PeriodoCesp perCesp = null;
		try {
			perCesp = cespDAO.buscarUltimoPeriodoCesp();
			formaPago = formaPagoDAO.buscarFormaPagoId(formaPagoID);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al buscar periodo Cesp", e.getMessage()));
			return;
		}
		Date fechaAct = new Date();
		if (fechaAct == null || fechaAct.after(perCesp.getFechaVtoCesp())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"No se puede realizar el cobro, no existe CESP válido."));
			return;
		}
		for (PeriodosSaldos periodo : lstPeriodosSaldos) {
			TipoComprobante tipoComprobante = null;
			FacturaDAO facturaDAO = new FacturaDAOImplement();
			Factura fact = new Factura();
			List<ReciboItem> lstReciboItems = new ArrayList<ReciboItem>();
			ReciboDAO reciboDAO = new ReciboDAOImplement();
			try {
				fact = facturaDAO.buscarFacturaPerSaldo(periodo);
				TipoComprobanteDAO tipoComprobanteDAO = new TipoComprobanteDAOImplement();
				if (fact.getConexion().getSocio().getCondicionIva().getCodigo().equals("R INSC")) {
					tipoComprobante = tipoComprobanteDAO.buscarTipoComprobanteId(1L);
				} else {
					tipoComprobante = tipoComprobanteDAO.buscarTipoComprobanteId(2L);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			Recibo recibo = new Recibo();
			recibo.setConexion(periodo.getConexion());
			recibo.setFechaEmision(new Date());
			recibo.setFormaPago(formaPago);
			recibo.setObservaciones(null);
			recibo.setUsuario(login.getUsuario());

			ReciboItem reciboItem = new ReciboItem();
			reciboItem.setAnio(periodo.getAnio());
			reciboItem.setComprobanteOrigen("FC");
			reciboItem.setImporte(fact.getImporteTotal());
			reciboItem.setMes(periodo.getMes());
			reciboItem.setNroComprobante(fact.getId());
			reciboItem.setTipoComprobante(tipoComprobante);
			reciboItem.setTipoPago(tipoPago);
			lstReciboItems.add(reciboItem);
			if (interesGlobal != null && interesGlobal > 0F) {
				Float interes = 0F;
				float iva = 0F;
				NotaDebitoDAO nDebitoDAO = new NotaDebitoDAOImplement();

				this.subTotal += Math.abs(periodo.getSaldo());

				NotaDebito notaDebito = new NotaDebito();
				notaDebito.setAnio(periodo.getAnio());
				notaDebito.setCesp(perCesp.getCesp());
				notaDebito.setConceptoFacturacion(null);
				notaDebito.setConexion(periodo.getConexion());
				notaDebito.setFechaEmision(new Date());
				notaDebito.setFechaVtoCesp(perCesp.getFechaVtoCesp());
				notaDebito.setMes(periodo.getMes());
				notaDebito.setTipoComprobante(tipoComprobante);
				notaDebito.setUsuario(login.getUsuario());
				Date fechaActual = new Date();

				if (fechaActual.after(fact.getPeriodoFacturacion().getFechaSegundoVencimientoFactura())) {
					long dif = (fechaActual.getTime()
							- fact.getPeriodoFacturacion().getFechaSegundoVencimientoFactura().getTime())
							/ MILLSECS_PER_DAY;

					System.out.println("DIAS DE DIFERENCIA: " + dif);
					System.out.println("INTERESES * 1000: " + interes);
					fact.getPeriodoFacturacion().getFechaSegundoVencimientoFactura();
					interes += (((fact.getInteresesSegVenc() / 30) * (7 + dif)) * fact.getImporteTotal() / 100);
				} else {
					interes += (((fact.getInteresesSegVenc() / 30) * 7) * fact.getImporteTotal() / 100);
				}
				iva = ((fact.getConexion().getSocio().getCondicionIva().getPorcentaje() * interes) / 100);
				notaDebito.setImporte(interes + iva);
				notaDebito.setIva(iva);

				try {
					notaDebito.setId(nDebitoDAO.insertarNotaDebito(notaDebito));
				} catch (Exception e) {
					e.printStackTrace();
				}
				ReciboItem reciboItem2 = new ReciboItem();
				reciboItem2.setAnio(periodo.getAnio());
				reciboItem2.setComprobanteOrigen("ND");
				reciboItem2.setImporte(fact.getImporteTotal());
				reciboItem2.setMes(periodo.getMes());
				reciboItem2.setNroComprobante(notaDebito.getId());
				reciboItem2.setTipoComprobante(tipoComprobante);
				reciboItem2.setTipoPago(tipoPago);

				lstReciboItems.add(reciboItem2);

				total = (subTotal + interes);
			}
			recibo.setLstReciboItems(lstReciboItems);
			try {
				reciboDAO.insertarRecibo(recibo);
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error",
						"No se pudo cobrar el recibo: " + e.getMessage()));
				return;
			}
		}
	}

	public void obtenerComp() {
		inicializar();
		String BEAN_KEY = "periodos";
		this.lstPeriodosSaldos = (List<PeriodosSaldos>) ELFlash.getFlash().get(BEAN_KEY);
		FacturaDAO facturaDAO = new FacturaDAOImplement();
		for (PeriodosSaldos periodo : lstPeriodosSaldos) {
			Factura fact = new Factura();
			this.subTotal += Math.abs(periodo.getSaldo());
			try {
				Date fechaActual = new Date();
				fact = facturaDAO.buscarFacturaPerSaldo(periodo);
				if (fechaActual.after(fact.getPeriodoFacturacion().getFechaSegundoVencimientoFactura())) {
					// CALCULAR DE OTRA MANERA
					long dif = (fechaActual.getTime()
							- fact.getPeriodoFacturacion().getFechaSegundoVencimientoFactura().getTime())
							/ MILLSECS_PER_DAY;
					fact.getPeriodoFacturacion().getFechaSegundoVencimientoFactura();
					interesGlobal += (((fact.getInteresesSegVenc() / 30) * (7 + dif)) * fact.getImporteTotal() / 100);
				} else {
					interesGlobal += (((fact.getInteresesSegVenc() / 30) * 7) * fact.getImporteTotal() / 100);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			total = (subTotal + interesGlobal);
		}
	}
}

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

import dao.ConexionesSaldosDAO;
import dao.FacturaDAO;
import dao.FormaPagoDAO;
import dao.NotaDebitoDAO;
import dao.PeriodoCespDAO;
import dao.PeriodosSaldosDAO;
import dao.ReciboDAO;
import dao.TipoComprobanteDAO;
import dao.impl.ConexionesSaldosDAOImplement;
import dao.impl.FacturaDAOImplement;
import dao.impl.FormaPagoDAOImplement;
import dao.impl.NotaDebitoDAOImplement;
import dao.impl.PeriodoCespDAOImplement;
import dao.impl.PeriodosSaldosDAOImplement;
import dao.impl.ReciboDAOImplement;
import dao.impl.TipoComprobanteDAOImplement;
import model.ConexionesSaldos;
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
	private Double subTotal;
	private Double interesGlobal;
	private Double total;
	private Double importe;
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

	public Double getSubTotal() {
		return (Math.rint(subTotal * 100) / 100);
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = (Math.rint(subTotal * 100) / 100);
	}

	public Double getInteresGlobal() {
		return (Math.rint(interesGlobal * 100) / 100);
	}

	public void setInteresGlobal(Double interesGlobal) {
		this.interesGlobal = (Math.rint(interesGlobal * 100) / 100);
	}

	public Double getTotal() {
		return (Math.rint(total * 100) / 100);
	}

	public void setTotal(Double total) {
		this.total = (Math.rint(total * 100) / 100);
	}

	public Double getImporte() {
		return (Math.rint(importe * 100) / 100);
	}

	public void setImporte(Double importe) {
		this.importe = (Math.rint(importe * 100) / 100);
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
		total = 0D;
		subTotal = 0D;
		interesGlobal = 0D;
		tipoPago = null;
		// formaPagoID = null;
		tipoPago = "";
		importe = 0D;
	}

	public void cobrar() {
		PeriodoCespDAO cespDAO = new PeriodoCespDAOImplement();
		FormaPagoDAO formaPagoDAO = new FormaPagoDAOImplement();
		ConexionesSaldosDAO conexionesSaldosDAO = new ConexionesSaldosDAOImplement();
		FormaPago formaPago = null;
		PeriodoCesp perCesp = null;
		Double totalConexSaldo = 0D;
		try {
			perCesp = cespDAO.buscarUltimoPeriodoCesp();
			formaPago = formaPagoDAO.buscarFormaPagoId(formaPagoID);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al buscar periodo Cesp", e.getMessage()));			
		}
		Date fechaAct = new Date();
		if (fechaAct == null || fechaAct.after(perCesp.getFechaVtoCesp())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"No se puede realizar el cobro, no existe CESP válido."));
		}else{
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
				if(tipoPago.equals("PAGO TOTAL")){
					reciboItem.setImporte(Math.abs(periodo.getSaldo()));
				}else{
					reciboItem.setImporte(importe);
				}
				reciboItem.setMes(periodo.getMes());
				reciboItem.setNroComprobante(fact.getId());
				reciboItem.setTipoComprobante(tipoComprobante);
				reciboItem.setTipoPago(tipoPago);
				lstReciboItems.add(reciboItem);
				if (interesGlobal != null && interesGlobal > 0F) {
					Double interes = 0D;
					Double iva = 0D;
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
					reciboItem2.setImporte(notaDebito.getImporte());
					reciboItem2.setMes(periodo.getMes());
					reciboItem2.setNroComprobante(notaDebito.getId());
					reciboItem2.setTipoComprobante(tipoComprobante);
					reciboItem2.setTipoPago(tipoPago);

					lstReciboItems.add(reciboItem2);

					total = (subTotal + interes);
					totalConexSaldo = reciboItem2.getImporte();
				}
				totalConexSaldo = reciboItem.getImporte();
				recibo.setLstReciboItems(lstReciboItems);
				try {
					reciboDAO.insertarRecibo(recibo);
					ConexionesSaldos conSaldo = conexionesSaldosDAO
							.buscarConexionesSaldosConexion(fact.getConexion().getId());
					if (conSaldo != null) {
						System.out.println("ENTRA POR IF CON SALDO COBRO");
						conSaldo.setSaldoTotal(conSaldo.getSaldoTotal() + totalConexSaldo);
						conSaldo.setUltimoVencRegistrado(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
						conexionesSaldosDAO.modificarConexionesSaldos(conSaldo);
					} else {
						System.out.println("ENTRA POR EL ELSE CON SALDO COBRO");
						conSaldo = new ConexionesSaldos();
						conSaldo.setConexion(fact.getConexion());
						conSaldo.setUltimoVencRegistrado(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
						conSaldo.setSaldoTotal(0F + totalConexSaldo);
						conexionesSaldosDAO.insertarConexionesSaldos(conSaldo);
					}
					PeriodosSaldosDAO periodosSaldosDAO = new PeriodosSaldosDAOImplement();
					periodo.setSaldo(periodo.getSaldo() +totalConexSaldo );
					periodosSaldosDAO.modificarPeriodosSaldos(periodo);
				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error",
							"No se pudo cobrar el recibo: " + e.getMessage()));
				}
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
				}
				if (fechaActual.after(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura())) {
					interesGlobal += (((fact.getInteresesSegVenc() / 30) * 7) * fact.getImporteTotal() / 100);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			total = (subTotal + interesGlobal);
		}
	}
}

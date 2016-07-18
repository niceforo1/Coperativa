package bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.ConceptoFacturacionDAO;
import dao.ConexionDAO;
import dao.ConexionesSaldosDAO;
import dao.ConfiguracionFacturaDAO;
import dao.EstadoPeriodoDAO;
import dao.EstadoSocioDAO;
import dao.FacturaDAO;
import dao.GeneradorFacturaADAO;
import dao.GeneradorFacturaBDAO;
import dao.LecturaDAO;
import dao.PeriodoCanonDAO;
import dao.PeriodoFacturacionDAO;
import dao.PeriodoLecturaDAO;
import dao.PeriodosSaldosDAO;
import dao.SocioDAO;
import dao.impl.ConceptoFacturacionDAOImplement;
import dao.impl.ConexionDAOImplement;
import dao.impl.ConexionesSaldosDAOImplement;
import dao.impl.ConfiguracionFacturaDAOImplement;
import dao.impl.EstadoPeriodoDAOImplement;
import dao.impl.EstadoSocioDAOImplement;
import dao.impl.FacturaDAOImplement;
import dao.impl.GeneradorFacturaADAOImplement;
import dao.impl.GeneradorFacturaBDAOImplement;
import dao.impl.LecturaDAOImplement;
import dao.impl.PeriodoCanonDAOImplement;
import dao.impl.PeriodoFacturacionDAOImplement;
import dao.impl.PeriodoLecturaDAOImplement;
import dao.impl.PeriodosSaldosDAOImplement;
import dao.impl.SocioDAOImplement;
import model.Conexion;
import model.ConexionesSaldos;
import model.ConfiguracionFactura;
import model.Factura;
import model.Lectura;
import model.PeriodoCesp;
import model.PeriodoFacturacion;
import model.PeriodoLectura;
import model.PeriodosSaldos;
import model.Socio;
import model.SociosTransacciones;
import model.generadores.GenFacturaA;
import model.generadores.GenFacturaB;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import reportes.CJasperReports;

@ManagedBean(name = "periodoFacturacionBean")
@ViewScoped
public class PeriodoFacturacionBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(PeriodoFacturacionBean.class);

	final static String NORMAL = "NORMAL";
	final static String CANON = "CANON";
	private PeriodoFacturacion periodoFacturacion;
	private List<PeriodoFacturacion> lstPeriodoFacturacion;
	private List<PeriodoFacturacion> lstPerFactNoHist;
	private PeriodoFacturacion periodoFacturacionEnProceso;
	private Long peridoIdFactEleg;
	private List<Factura> facturasPeriodo;
	private int factMax;
	private int factMin;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean login;

	@ManagedProperty(value = "#{reportBean}")
	private ReportBean reportes;

	public PeriodoFacturacionBean() {
		inicializar();
	}

	public ReportBean getReportes() {
		return reportes;
	}

	public void setReportes(ReportBean reportes) {
		this.reportes = reportes;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public PeriodoFacturacion getPeriodoFacturacion() {
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		try {
			lstPeriodoFacturacion = periodoFacturacionDAO.listaPeriodoFacturacion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return periodoFacturacion;
	}

	public void setPeriodoFacturacion(PeriodoFacturacion periodoFacturacion) {
		this.periodoFacturacion = periodoFacturacion;
	}

	public Long getPeridoIdFactEleg() {
		return peridoIdFactEleg;
	}

	public void setPeridoIdFactEleg(Long peridoIdFactEleg) {
		this.peridoIdFactEleg = peridoIdFactEleg;
	}

	public List<Factura> getFacturasPeriodo() {
		return facturasPeriodo;
	}

	public void setFacturasPeriodo(List<Factura> facturasPeriodo) {
		this.facturasPeriodo = facturasPeriodo;
	}

	public int getFactMax() {
		return factMax;
	}

	public void setFactMax(int factMax) {
		this.factMax = factMax;
	}

	public int getFactMin() {
		return factMin;
	}

	public int getTotalFac() {
		return facturasPeriodo.size();
	}

	public void setFactMin(int factMin) {
		this.factMin = factMin;
	}

	public List<PeriodoFacturacion> getLstPerFactNoHist() {
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		try {
			lstPerFactNoHist = periodoFacturacionDAO.lstPeriodosFacturacionNoHist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstPerFactNoHist;
	}

	public void setLstPerFactNoHist(List<PeriodoFacturacion> lstPerFactNoHist) {
		this.lstPerFactNoHist = lstPerFactNoHist;
	}

	public void consultarFacturas() {
		FacturaDAO facturaDAO = new FacturaDAOImplement();
		try {
			facturasPeriodo = facturaDAO.listaFacturaPeriodoFact(peridoIdFactEleg);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al obtener Lista Facturas por Facturacion: " + e.getMessage()));
			LOG.error("Error al obtener Lista Facturas por Facturacion: " + e.getMessage());
		}
	}

	public void generarFactMasiva() {
		try {
			if ((factMin == 0 || factMax == 0) || (factMax > facturasPeriodo.size()) || (factMin <= 0)) {
				throw new Exception("Por favor seleccione verifique el rango a imprimir");
			}
			if ((factMax - (factMin - 1)) == facturasPeriodo.size()) {
				for (Factura fact : facturasPeriodo) {
					reportes.genFactura(fact.getConexion(), fact.getPeriodoFacturacion());
				}
			} else {
				for (Factura fact : facturasPeriodo.subList((factMin - 1), (factMax - 1))) {
					reportes.genFactura(fact.getConexion(), fact.getPeriodoFacturacion());
				}
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			LOG.error("Error al obtener Lista Periodo Facturacion: " + e.getMessage());
		}
	}

	public List<PeriodoFacturacion> getLstPeriodoFacturacion() {
		try {
			PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
			lstPeriodoFacturacion = periodoFacturacionDAO.listaPeriodoFacturacion();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			LOG.error("Error al obtener Lista Periodo Facturacion: " + e.getMessage());
		}
		return lstPeriodoFacturacion;

	}

	public void setLstPeriodoFacturacion(List<PeriodoFacturacion> lstPeriodoFacturacion) {
		this.lstPeriodoFacturacion = lstPeriodoFacturacion;
	}

	public PeriodoFacturacion getPeriodoFacturacionEnProceso() {
		try {
			PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
			periodoFacturacionEnProceso = periodoFacturacionDAO.buscarPeriodoFacturacionAbierto();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			LOG.error("Error al Periodo Facturacion En Proceso: " + e.getMessage());
		}
		return periodoFacturacionEnProceso;
	}

	public void setPeriodoFacturacionEnProceso(PeriodoFacturacion periodoFacturacionEnProceso) {
		this.periodoFacturacionEnProceso = periodoFacturacionEnProceso;
	}

	public boolean periodoAbiertoCons() {

		if (periodoFacturacionEnProceso != null) {
			return true;
		} else {
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
				if ((per.getAnio() == periodoFacturacion.getAnio()) && (per.getMes() == periodoFacturacion.getMes())) {
					existe = true;
				}
			}
			if (existe == false) {
				periodoFacturacion.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo("EN PROCESO"));
				periodoFacturacionDAO.insertarPeriodoFacturacion(periodoFacturacion);

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Correctamente", "El Período se agregó correctamente."));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error", "Error al procesar: Período ya cargado. "));
			}
			inicializar();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
			LOG.error("Error al Insertar Periodo Facturacion: " + e.getMessage());

		}
	}

	public void cambiarEstadoPeriodo(String estado, PeriodoFacturacion periodo) {
		EstadoPeriodoDAO estadoPeriodoDAO = new EstadoPeriodoDAOImplement();
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		String msgExito = "El período se modifico correctamente";
		try {
			periodo.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo(estado));
			if (estado.equals("FACTURADO")) {
				facturar();
				msgExito = "El período se Facturó correctamente sin errores.";
			}

			periodoFacturacionDAO.modificarPeriodoFacturacion(periodo);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Correctamente", msgExito));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
			LOG.error("Error al Cambiar Estado Periodo: " + e.getMessage());

		}
	}

	private void facturar() throws Exception {
		PeriodoLecturaDAO periodoLecturaDAO = new PeriodoLecturaDAOImplement();
		LecturaDAO lecturaDAO = new LecturaDAOImplement();
		ConceptoFacturacionDAO conceptoFacturacionDAO = new ConceptoFacturacionDAOImplement();
		PeriodoFacturacionDAO periodoFacturacionDAO = new PeriodoFacturacionDAOImplement();
		ConfiguracionFacturaDAO daoConfiguracionFactura = new ConfiguracionFacturaDAOImplement();
		FacturaDAO daoFactura = new FacturaDAOImplement();
		PeriodoCanonDAO periodoCanonDAO = new PeriodoCanonDAOImplement();
		ConexionesSaldosDAO conexionesSaldosDAO = new ConexionesSaldosDAOImplement();
		PeriodosSaldosDAO periodosSaldosDAO = new PeriodosSaldosDAOImplement();
		try {
			ConfiguracionFactura configFactura = daoConfiguracionFactura.obtenerConfiguracionFactura().get(0);
			for (Lectura lec : lecturaDAO.buscarLecturasPorPeriodo(periodoLecturaDAO.buscarPeriodoLecturaCerrado())) {
				Factura fact = new Factura();
				long totalConsumido;
				if (lec.getLecturaActual() < lec.getLecturaAnterior()) {
					totalConsumido = ((Integer.parseInt("9999") - lec.getLecturaAnterior()) + lec.getLecturaActual());
				} else {
					totalConsumido = lec.getLecturaActual() - lec.getLecturaAnterior();
				}
				Double importeTramos = 0D;
				if (lec.getConexion().getCategoriaConexion().getDescripcion().equals(NORMAL)) {
					if (totalConsumido > 50) {
						fact.setTramo6(totalConsumido - 50);
						fact.setTramo5(10L);
						fact.setTramo4(10L);
						fact.setTramo3(10L);
						fact.setTramo2(10L);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1() * 10F) + (configFactura.getTramo2() * 10F)
								+ (configFactura.getTramo3() * 10F) + (configFactura.getTramo4() * 10F)
								+ (configFactura.getTramo5() * 10F) + (configFactura.getTramo6() * fact.getTramo6());
					} else if (totalConsumido > 40) {
						fact.setTramo5(totalConsumido - 40);
						fact.setTramo4(10L);
						fact.setTramo3(10L);
						fact.setTramo2(10L);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1() * 10F) + (configFactura.getTramo2() * 10F)
								+ (configFactura.getTramo3() * 10F) + (configFactura.getTramo4() * 10F)
								+ (configFactura.getTramo5() * fact.getTramo5());
					} else if (totalConsumido > 30) {
						fact.setTramo4(totalConsumido - 30);
						fact.setTramo3(10L);
						fact.setTramo2(10L);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1() * 10F) + (configFactura.getTramo2() * 10F)
								+ (configFactura.getTramo3() * 10F) + (configFactura.getTramo4() * fact.getTramo4());
					} else if (totalConsumido > 20) {
						fact.setTramo3(totalConsumido - 20);
						fact.setTramo2(10L);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1() * 10F) + (configFactura.getTramo2() * 10F)
								+ (configFactura.getTramo3() * fact.getTramo3());
					} else if (totalConsumido > 10) {
						fact.setTramo2(totalConsumido - 10);
						fact.setTramo1(10L);
						importeTramos = (configFactura.getTramo1() * 10F)
								+ (configFactura.getTramo2() * fact.getTramo2());
					} else {
						fact.setTramo1(totalConsumido);
						importeTramos = (configFactura.getTramo1() * fact.getTramo1());
					}
					fact.setCargoFijo(lec.getConexion().getTipoSuministro().getImporte());
					// se deja el calculo de 10%, solo se obtiene el monto fijo
					// desde la configuracion
					if (lec.getConexion().getZonaConexion().getId() != 8) {
						if (lec.getConexion().getId() != 1262 && lec.getConexion().getId() != 2063
								&& lec.getConexion().getId() != 1261) {
							fact.setCapitalSocial(
									(0.1F * (fact.getCargoFijo() + importeTramos)) + configFactura.getCapitalSocial());
						}else{
							fact.setCapitalSocial(0D);
						}
					}else{
						fact.setCapitalSocial(0D);
					}
					fact.setConceptoFacturacion(conceptoFacturacionDAO.buscarConceptoFacturacionId(1L));
					fact.setConexion(lec.getConexion());
					// se divide en 100 el porcentaje obtenido de la
					// configuracion
					fact.setErsep((configFactura.getErsep() / 100) * (fact.getCargoFijo() + importeTramos));
					// FIJO
					fact.setImpresionesOtros(configFactura.getImpresionesOtros());
					/*
					 * fact.setImpresionesOtros(configFactura.
					 * getImpresionesOtros()+
					 * (configFactura.getImpresionesOtros()*
					 * (lec.getConexion().getSocio().getCondicionIva().
					 * getPorcentaje())/100));
					 */
					// viene de configuracion factura
					fact.setInteresesSegVenc(3D);

					fact.setPeriodoFacturacion(periodoFacturacionDAO.buscarPeriodoFacturacionAbierto());
					// FIJO
					fact.setRecuperoInversion(configFactura.getRecuperoInversion());
					/*
					 * fact.setRecuperoInversion(configFactura.
					 * getRecuperoInversion()+
					 * (configFactura.getRecuperoInversion()*lec.getConexion().
					 * getSocio().getCondicionIva().getPorcentaje())/100);
					 */

					if (fact.getConexion().getSocio().getCondicionIva().getId().equals(4)) {
						fact.setTipoFactura("A");
						GeneradorFacturaADAO facturaADAO = new GeneradorFacturaADAOImplement();
						fact.setNumeroFactura(facturaADAO.insertarFacturaA(new GenFacturaA()).toString());
					} else {
						GeneradorFacturaBDAO facturaBDAO = new GeneradorFacturaBDAOImplement();
						fact.setNumeroFactura(facturaBDAO.insertarFacturaB(new GenFacturaB()).toString());
						fact.setTipoFactura("B");
					}

					fact.setFechaVencimiento(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
					fact.setIva((lec.getConexion().getSocio().getCondicionIva().getPorcentaje()
							* (fact.getCargoFijo() + importeTramos) / 100)
							+ (lec.getConexion().getSocio().getCondicionIva().getIvaOtrosConceptos()
									* (fact.getImpresionesOtros() + fact.getRecuperoInversion()) / 100));
					fact.setImporteTotal(fact.getCargoFijo() + importeTramos + fact.getCapitalSocial() + fact.getErsep()
							+ fact.getRecuperoInversion() + fact.getImpresionesOtros() + fact.getIva());

				}
				try {
					daoFactura.insertarFactura(fact);
				} catch (Exception ex) {
					LOG.error("Error al grabar factura: " + ex.getMessage());
					throw new Exception("Error al grabar factura: " + ex.getMessage());
				}
				ConexionesSaldos conSaldo = null;
				try {
					conSaldo = conexionesSaldosDAO.buscarConexionesSaldosConexion(fact.getConexion().getId());
				} catch (Exception ex) {
					LOG.error("Error al obtener Saldo Conexion: " + ex.getMessage());
					throw new Exception("Error al obtener Saldo Conexion: " + ex.getMessage());
				}
				try {
					if (conSaldo != null) {
						conSaldo.setSaldoTotal(conSaldo.getSaldoTotal() - fact.getImporteTotal());
						conSaldo.setUltimoVencRegistrado(
								fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
						conexionesSaldosDAO.modificarConexionesSaldos(conSaldo);
					} else {
						conSaldo = new ConexionesSaldos();
						conSaldo.setConexion(fact.getConexion());
						conSaldo.setUltimoVencRegistrado(
								fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
						conSaldo.setSaldoTotal(0F - fact.getImporteTotal());
						conexionesSaldosDAO.insertarConexionesSaldos(conSaldo);
					}
				} catch (Exception ex) {
					LOG.error("Error modificar/grabar Saldo Conexion: " + ex.getMessage());
					throw new Exception("Error modificar/grabar Saldo Conexion: " + ex.getMessage());
				}
				try {
					PeriodosSaldos perSaldo = new PeriodosSaldos();
					perSaldo.setConexion(fact.getConexion());
					perSaldo.setMes(fact.getPeriodoFacturacion().getMes());
					perSaldo.setAnio(fact.getPeriodoFacturacion().getAnio());
					perSaldo.setFechaVencimiento(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
					perSaldo.setConsumo(totalConsumido);
					perSaldo.setSaldo(0F - fact.getImporteTotal());
					periodosSaldosDAO.insertarPeriodosSaldos(perSaldo);
				} catch (Exception ex) {
					LOG.error("Error modificar/grabar Saldo Periodo Conexion: " + ex.getMessage());
					throw new Exception("Error modificar/grabar Saldo Periodo Conexion: " + ex.getMessage());
				}
			}
			if (periodoCanonDAO
					.buscarPeriodosCanonMes((int) periodoLecturaDAO.buscarPeriodoLecturaCerrado().getMes()) != null) {
				ConexionDAO conexionDAO = new ConexionDAOImplement();
				for (Conexion conexion : conexionDAO.buscarConexionesCanon()) {
					Factura fact = new Factura();
					fact.setTramo6(0L);
					fact.setTramo5(0L);
					fact.setTramo4(0L);
					fact.setTramo3(0L);
					fact.setTramo2(0L);
					fact.setTramo1(0L);
					fact.setCargoFijo(conexion.getTipoSuministro().getImporte());
					// se deja el calculo de 10%, solo se obtiene el monto fijo
					// desde la configuracion
					if (conexion.getZonaConexion().getId() != 8) {
						if (conexion.getId() != 1262 && conexion.getId() != 2063
								&& conexion.getId() != 1261) {
							fact.setCapitalSocial(
									(0.1F * (fact.getCargoFijo())) + configFactura.getCapitalSocial());
						}else{
							fact.setCapitalSocial(0D);
						}
					}else{
						fact.setCapitalSocial(0D);
					}
					
					fact.setConceptoFacturacion(conceptoFacturacionDAO.buscarConceptoFacturacionId(1L));
					fact.setConexion(conexion);
					fact.setErsep((configFactura.getErsep() / 100) * (fact.getCargoFijo())); // se
																								// divide
																								// en
																								// 100
																								// el
																								// porcentaje
																								// obtenido
																								// de
																								// la
																								// configuracion
					fact.setImpresionesOtros(configFactura.getImpresionesOtros());// FIJO
					fact.setRecuperoInversion(configFactura.getRecuperoInversion());// FIJO
					fact.setInteresesSegVenc(3D);
					fact.setIva((conexion.getSocio().getCondicionIva().getPorcentaje()
							* (fact.getCargoFijo() ) / 100)+ (conexion.getSocio().getCondicionIva().getIvaOtrosConceptos()
									* (fact.getImpresionesOtros() + fact.getRecuperoInversion()) / 100));
					fact.setPeriodoFacturacion(periodoFacturacionDAO.buscarPeriodoFacturacionAbierto());
					
					if (fact.getConexion().getSocio().getCondicionIva().getId().equals(4)) {
						fact.setTipoFactura("A");
						GeneradorFacturaADAO facturaADAO = new GeneradorFacturaADAOImplement();
						fact.setNumeroFactura(facturaADAO.insertarFacturaA(new GenFacturaA()).toString());
					} else {
						GeneradorFacturaBDAO facturaBDAO = new GeneradorFacturaBDAOImplement();
						fact.setNumeroFactura(facturaBDAO.insertarFacturaB(new GenFacturaB()).toString());
						fact.setTipoFactura("B");
					}

					fact.setImporteTotal(fact.getCargoFijo() + fact.getCapitalSocial() + fact.getErsep()
							+ fact.getRecuperoInversion() + fact.getImpresionesOtros() + fact.getIva());
					System.out.println(fact.toString());

					try {
						daoFactura.insertarFactura(fact);
					} catch (Exception ex) {
						LOG.error("Error al grabar factura Canon: " + ex.getMessage());
						throw new Exception("Error al grabar factura Canon: " + ex.getMessage());
					}
					ConexionesSaldos conSaldo = null;
					try {
						conSaldo = conexionesSaldosDAO.buscarConexionesSaldosConexion(fact.getConexion().getId());
					} catch (Exception ex) {
						LOG.error("Error al Obtener Saldo Conexion Canon: " + ex.getMessage());
						throw new Exception("Error al Obtener Saldo Conexion Canon: " + ex.getMessage());
					}
					try {
						if (conSaldo != null) {
							conSaldo.setSaldoTotal(conSaldo.getSaldoTotal() - fact.getImporteTotal());
							conSaldo.setUltimoVencRegistrado(
									fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
							conexionesSaldosDAO.modificarConexionesSaldos(conSaldo);
						} else {
							conSaldo = new ConexionesSaldos();
							conSaldo.setConexion(fact.getConexion());
							conSaldo.setUltimoVencRegistrado(
									fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
							conSaldo.setSaldoTotal(0F - fact.getImporteTotal());
							conexionesSaldosDAO.insertarConexionesSaldos(conSaldo);
						}
					} catch (Exception ex) {
						LOG.error("Error al modificar/grabar Saldo Conexion Canon: " + ex.getMessage());
						throw new Exception("Error al modificar/grabar Saldo Conexion Canon: " + ex.getMessage());
					}
					try {
						PeriodosSaldos perSaldo = new PeriodosSaldos();
						perSaldo.setConexion(fact.getConexion());
						perSaldo.setMes(fact.getPeriodoFacturacion().getMes());
						perSaldo.setAnio(fact.getPeriodoFacturacion().getAnio());
						perSaldo.setFechaVencimiento(fact.getPeriodoFacturacion().getFechaPrimerVencimientoFactura());
						perSaldo.setConsumo(0);
						perSaldo.setSaldo(0F - fact.getImporteTotal());
						periodosSaldosDAO.insertarPeriodosSaldos(perSaldo);
					} catch (Exception ex) {
						LOG.error("Error al modificar/grabar Periodos Saldo Conexion Canon: " + ex.getMessage());
						throw new Exception(
								"Error al modificar/grabar Periodos Saldo Conexion Canon: " + ex.getMessage());
					}
				}
			}
			EstadoPeriodoDAO estadoPeriodoDAO = new EstadoPeriodoDAOImplement();
			// PeriodoLecturaDAO periodoLecturaDAO = new
			// PeriodoLecturaDAOImplement();
			try {
				PeriodoLectura periodoLectura = periodoLecturaDAO.buscarPeriodoLecturaCerrado();
				periodoLectura.setEstadoPeriodo(estadoPeriodoDAO.buscarEstadoPeriodo("FACTURADO"));
				// se cambia ultima fecha mod
				periodoLectura.setFechaUltimaMod(Calendar.getInstance().getTime());

				periodoLecturaDAO.modificarPeriodoLectura(periodoLectura);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Correctamente", "El período se modifico correctamente."));
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar: " + e.getMessage()));
				LOG.error("Error al Cambiar Estado Periodo: " + e.getMessage());
			}
		} catch (Exception e) {
			LOG.error("Error General Facturacion " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	private void inicializar() {
		getPeriodoFacturacionEnProceso();
		periodoFacturacion = new PeriodoFacturacion();
		periodoFacturacion.setPeriodoCesp(new PeriodoCesp());
		peridoIdFactEleg = 0L;
		lstPeriodoFacturacion = null;
		factMax = 0;
		factMin = 0;
	}

}

package bean;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;

import dao.PeriodoLecturaDAO;
import dao.impl.PeriodoLecturaDAOImplement;
import model.Conexion;
import model.PeriodoFacturacion;
import model.PeriodoLectura;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRResourcesUtil;
import reportes.CJasperReports;

@ManagedBean(name = "reportBean")
@ViewScoped
public class ReportBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(RegimenPropiedadBean.class);

	private JasperReport report;
	private String estadoSocio;
	private String zona;
	private String estConexion;
	private String formaPago;
	private String categoriaConex;
	private String periodoLectu;
	private String anioConsZona;
	private Integer anioConsDesde;
	private Integer anioConsHasta;

	public ReportBean() {
		inicializar();
	}

	public String getAnioConsZona() {
		return anioConsZona;
	}

	public void setAnioConsZona(String anioConsZona) {
		this.anioConsZona = anioConsZona;
	}

	public String getEstadoSocio() {
		return estadoSocio;
	}

	public void setEstadoSocio(String estadoSocio) {
		this.estadoSocio = estadoSocio;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getEstConexion() {
		return estConexion;
	}

	public void setEstConexion(String estConexion) {
		this.estConexion = estConexion;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getCategoriaConex() {
		return categoriaConex;
	}

	public void setCategoriaConex(String categoriaConex) {
		this.categoriaConex = categoriaConex;
	}

	public String getPeriodoLectu() {
		return periodoLectu;
	}

	public void setPeriodoLectu(String periodoLectu) {
		this.periodoLectu = periodoLectu;
	}

	public Integer getAnioConsDesde() {
		return anioConsDesde;
	}

	public void setAnioConsDesde(Integer anioConsDesde) {
		this.anioConsDesde = anioConsDesde;
	}

	public Integer getAnioConsHasta() {
		return anioConsHasta;
	}

	public void setAnioConsHasta(Integer anioConsHasta) {
		this.anioConsHasta = anioConsHasta;
	}

	public void verPlanillaLectura() throws SQLException {
		Map<String, Object> myMap = new HashMap<String, Object>();
		String where = "";
		if (periodoLectu != null && !periodoLectu.equals("")) {
			String id = periodoLectu.split(",")[0];
			String mes = periodoLectu.split(",")[1];
			String anio = periodoLectu.split(",")[2];

			myMap.put("idPer", id);
			myMap.put("periodoFecha", mes + "/" + anio);

			FacesContext faceContext = FacesContext.getCurrentInstance();
			ServletContext servletContext = (ServletContext) faceContext.getExternalContext().getContext();
			String path = "/resources/reportes/PlanillaLecturas.jasper";
			String pathh = servletContext.getRealPath(path);
			HttpServletResponse response = (HttpServletResponse) faceContext.getExternalContext().getResponse();
			response.addHeader("Content-disposition",
					"attachment;filename=planillaLectura_" + mes + "/" + anio + ".pdf");
			response.setContentType("application/pdf");
			// JasperPrint impresion = JasperFillManager.fillReport(pathh,
			// myMap,connect());
			JasperPrint impresion;
			try {
				impresion = JasperFillManager.fillReport(pathh, myMap, connect());
				JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());

			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			faceContext.getApplication().getStateManager().saveView(faceContext);
			faceContext.responseComplete();

		}
		/*
		 * PeriodoLectura perLect = null; try { PeriodoLecturaDAO lecturaDAO =
		 * new PeriodoLecturaDAOImplement(); perLect =
		 * lecturaDAO.buscarPeriodoLecturaAbierto(); } catch (Exception e) {
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
		 * "Obtener Periodo Lectura Abierto: " + e.getMessage()));
		 * 
		 * }
		 * 
		 * String path = "/resources/reportes/PlanillaLecturas.jasper"; String
		 * pathh =
		 * FacesContext.getCurrentInstance().getExternalContext().getRealPath(
		 * path);
		 * 
		 * try { report = (JasperReport) JRLoader.loadObjectFromFile(pathh); }
		 * catch (JRException e) { e.printStackTrace(); }
		 * 
		 * HashMap myMap = new HashMap<String, Object>(); myMap.put("idPer",
		 * (perLect.getId() - 1)); myMap.put("periodoFecha", perLect.getMes() +
		 * "/" + perLect.getAnio());
		 * 
		 * // myMap.put("idEstado", estado);
		 * 
		 * CJasperReports.createReport(connect(), report, myMap);
		 * CJasperReports.showViewer();
		 */
	}

	public void verSociosConexiones() throws SQLException {
		Map<String, Object> myMap = new HashMap<String, Object>();
		String where = "";
		if (estConexion != null && !estConexion.equals("")) {
			where = " and c.ID_ESTADO_CONEXION=" + Integer.parseInt(estConexion.split(",")[0]);
		}
		if (formaPago != null && !formaPago.equals("")) {
			where = where + " and c.ID_FORMA_PAGO=" + Integer.parseInt(formaPago.split(",")[0]);
		}
		if (categoriaConex != null && !categoriaConex.equals("")) {
			where = where + " and c.ID_CATEGORIA_CONEXION=" + Integer.parseInt(categoriaConex.split(",")[0]);
		}
		myMap.put("whereClause", where);

		FacesContext faceContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) faceContext.getExternalContext().getContext();
		String path = "/resources/reportes/Socios/Socios_Conexiones.jasper";
		String pathh = servletContext.getRealPath(path);
		HttpServletResponse response = (HttpServletResponse) faceContext.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=reporteSocioConexiones.pdf");
		response.setContentType("application/pdf");
		// JasperPrint impresion = JasperFillManager.fillReport(pathh,
		// myMap,connect());
		JasperPrint impresion;
		try {
			impresion = JasperFillManager.fillReport(pathh, myMap, connect());
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		faceContext.getApplication().getStateManager().saveView(faceContext);
		faceContext.responseComplete();

		/*
		 * String path = "/resources/reportes/Socios/Socios_Conexiones.jasper";
		 * String pathh =
		 * FacesContext.getCurrentInstance().getExternalContext().getRealPath(
		 * path);
		 * 
		 * try { report = (JasperReport) JRLoader.loadObjectFromFile(pathh); }
		 * catch (JRException e) { e.printStackTrace(); } HashMap myMap = new
		 * HashMap<String, Object>(); String where = ""; if (estConexion != null
		 * && !estConexion.equals("")) { where = " and c.ID_ESTADO_CONEXION=" +
		 * Integer.parseInt(estConexion.split(",")[0]); } if (formaPago != null
		 * && !formaPago.equals("")) { LOG.info("GABINO: PASO 8"); where = where
		 * + " and c.ID_FORMA_PAGO=" +
		 * Integer.parseInt(formaPago.split(",")[0]); } if (categoriaConex !=
		 * null && !categoriaConex.equals("")) { LOG.info("GABINO: PASO 9");
		 * where = where + " and c.ID_CATEGORIA_CONEXION=" +
		 * Integer.parseInt(categoriaConex.split(",")[0]); }
		 * myMap.put("whereClause", where);
		 * CJasperReports.createReport(connect(), report, myMap);
		 * CJasperReports.showViewer();
		 */
	}

	public void verSociosEstado() throws SQLException {
		Map<String, Object> myMap = new HashMap<String, Object>();
		String where = "";
		if (estadoSocio != null && !estadoSocio.equals("")) {
			where = " and s.ID_ESTADO_SOCIO= " + Integer.parseInt(estadoSocio.split(",")[0]);
		}
		if (zona != null && !zona.equals("")) {
			where = where + " and c.ID_NRO_ZONA= " + Integer.parseInt(zona.split(",")[0]);
		}

		myMap.put("whereClause", where);

		FacesContext faceContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) faceContext.getExternalContext().getContext();
		String path = "/resources/reportes/Socios/SOCIO.jasper";
		String pathh = servletContext.getRealPath(path);
		HttpServletResponse response = (HttpServletResponse) faceContext.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=reporteSocioPorEstado.pdf");
		response.setContentType("application/pdf");
		// JasperPrint impresion = JasperFillManager.fillReport(pathh,
		// myMap,connect());
		JasperPrint impresion;
		try {
			impresion = JasperFillManager.fillReport(pathh, myMap, connect());
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		faceContext.getApplication().getStateManager().saveView(faceContext);
		faceContext.responseComplete();
		/*
		 * String path = "/resources/reportes/Socios/SociosEstadoZona.jasper";
		 * String pathh =
		 * FacesContext.getCurrentInstance().getExternalContext().getRealPath(
		 * path);
		 * 
		 * try { report = (JasperReport) JRLoader.loadObjectFromFile(pathh); }
		 * catch (JRException e) { e.printStackTrace(); }
		 * 
		 * HashMap myMap = new HashMap<String, Object>(); String where = ""; if
		 * (estadoSocio != null && !estadoSocio.equals("")) {
		 * myMap.put("estado", estadoSocio.split(",")[1]); where =
		 * " and s.ID_ESTADO_SOCIO= " +
		 * Integer.parseInt(estadoSocio.split(",")[0]); } if (zona != null &&
		 * !zona.equals("")) { myMap.put("zona", zona.split(",")[1]); where =
		 * where + " and c.ID_NRO_ZONA= " +
		 * Integer.parseInt(zona.split(",")[0]); } myMap.put("whereClause",
		 * where);
		 * 
		 * CJasperReports.createReport(connect(), report, myMap);
		 * CJasperReports.showViewer();
		 */
	}

	public void verConsumoZonaAnual() throws SQLException {
		Map<String, Object> myMap = new HashMap<String, Object>();

		if (anioConsZona.trim() == null || anioConsZona.trim().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Por favor ingrese el a�o requerido."));
			LOG.error("Error al evaluar A�: " + "Por favor ingrese el a�o requerido.");
			return;
		}
		myMap.put("id_anio", Integer.parseInt(anioConsZona));
		myMap.put("REPORT_CONNECTION", connect());

		FacesContext faceContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) faceContext.getExternalContext().getContext();
		String path = "/resources/reportes/CONSUMOS_ANUAL/CONSUMOS_ANUAL.jasper";
		String pathh = servletContext.getRealPath(path);
		HttpServletResponse response = (HttpServletResponse) faceContext.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=ConsumosAnualZona_" + anioConsZona + ".pdf");
		response.setContentType("application/pdf");
		// JasperPrint impresion = JasperFillManager.fillReport(pathh,
		// myMap,connect());
		JasperPrint impresion;
		try {
			impresion = JasperFillManager.fillReport(pathh, myMap, connect());
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		faceContext.getApplication().getStateManager().saveView(faceContext);
		faceContext.responseComplete();

		/*
		 * String path = "/resources/reportes/Factura/factura.jasper"; String
		 * pathh =
		 * FacesContext.getCurrentInstance().getExternalContext().getRealPath(
		 * path);
		 * 
		 * try { report = (JasperReport) JRLoader.loadObjectFromFile(pathh); }
		 * catch (JRException e) { e.printStackTrace(); }
		 * 
		 * HashMap myMap = new HashMap<String, Object>();
		 * 
		 * if (conexion.getId() != null) { myMap.put("idConexion",
		 * conexion.getId()); } if (periodo != null) {
		 * myMap.put("idPeriodoFact", periodo.getId()); }
		 * 
		 * CJasperReports.createReport(connect(), report, myMap);
		 * CJasperReports.showViewer();
		 */
	}

	public void verFactura(Conexion conexion, PeriodoFacturacion periodo) throws SQLException {
		Map<String, Object> myMap = new HashMap<String, Object>();

		if (conexion.getId() != null) {
			myMap.put("idConexion", conexion.getId());
		}
		if (periodo != null) {
			myMap.put("idPeriodoFact", periodo.getId());
		}

		FacesContext faceContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) faceContext.getExternalContext().getContext();
		String path = "/resources/reportes/Factura/FACTURA.jasper";
		String pathh = servletContext.getRealPath(path);
		HttpServletResponse response = (HttpServletResponse) faceContext.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=factura_" + conexion.getId() + "_"
				+ periodo.getMes() + periodo.getAnio() + ".pdf");
		response.setContentType("application/pdf");
		// JasperPrint impresion = JasperFillManager.fillReport(pathh,
		// myMap,connect());
		JasperPrint impresion;
		try {
			impresion = JasperFillManager.fillReport(pathh, myMap, connect());
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		faceContext.getApplication().getStateManager().saveView(faceContext);
		faceContext.responseComplete();

		/*
		 * String path = "/resources/reportes/Factura/factura.jasper"; String
		 * pathh =
		 * FacesContext.getCurrentInstance().getExternalContext().getRealPath(
		 * path);
		 * 
		 * try { report = (JasperReport) JRLoader.loadObjectFromFile(pathh); }
		 * catch (JRException e) { e.printStackTrace(); }
		 * 
		 * HashMap myMap = new HashMap<String, Object>();
		 * 
		 * if (conexion.getId() != null) { myMap.put("idConexion",
		 * conexion.getId()); } if (periodo != null) {
		 * myMap.put("idPeriodoFact", periodo.getId()); }
		 * 
		 * CJasperReports.createReport(connect(), report, myMap);
		 * CJasperReports.showViewer();
		 */
	}

	public void verConexionesMora() throws SQLException {
		Map<String, Object> myMap = new HashMap<String, Object>();

		FacesContext faceContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) faceContext.getExternalContext().getContext();
		String path = "/resources/reportes/CONEXIONES_EN_MORA/CONEXIONES_EN_MORA.jasper";
		String pathh = servletContext.getRealPath(path);
		HttpServletResponse response = (HttpServletResponse) faceContext.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=ConexionesEnMora.pdf");
		response.setContentType("application/pdf");
		// JasperPrint impresion = JasperFillManager.fillReport(pathh,
		// myMap,connect());
		JasperPrint impresion;
		try {
			impresion = JasperFillManager.fillReport(pathh, myMap, connect());
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		faceContext.getApplication().getStateManager().saveView(faceContext);
		faceContext.responseComplete();

		/*
		 * String path = "/resources/reportes/Factura/factura.jasper"; String
		 * pathh =
		 * FacesContext.getCurrentInstance().getExternalContext().getRealPath(
		 * path);
		 * 
		 * try { report = (JasperReport) JRLoader.loadObjectFromFile(pathh); }
		 * catch (JRException e) { e.printStackTrace(); }
		 * 
		 * HashMap myMap = new HashMap<String, Object>();
		 * 
		 * if (conexion.getId() != null) { myMap.put("idConexion",
		 * conexion.getId()); } if (periodo != null) {
		 * myMap.put("idPeriodoFact", periodo.getId()); }
		 * 
		 * CJasperReports.createReport(connect(), report, myMap);
		 * CJasperReports.showViewer();
		 */
	}

	public void verConsumoConexion(Conexion conexion) throws SQLException {
		Map<String, Object> myMap = new HashMap<String, Object>();

		myMap.put("id_conexion", conexion.getId().intValue());
		myMap.put("desde", anioConsDesde);
		myMap.put("hasta", anioConsHasta);
		
		
		FacesContext faceContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) faceContext.getExternalContext().getContext();
		String path = "/resources/reportes/CONSUMOS_POR_CONEXION/CONSUMOS_POR_CONEXION.jasper";
		String pathh = servletContext.getRealPath(path);
		HttpServletResponse response = (HttpServletResponse) faceContext.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=ConsumoConexion"+conexion.getId()+".pdf");
		response.setContentType("application/pdf");
		// JasperPrint impresion = JasperFillManager.fillReport(pathh,
		// myMap,connect());

		JasperPrint impresion;
		try {
			impresion = JasperFillManager.fillReport(pathh, myMap, connect());
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		faceContext.getApplication().getStateManager().saveView(faceContext);
		faceContext.responseComplete();

		/*
		 * String path = "/resources/reportes/Factura/factura.jasper"; String
		 * pathh =
		 * FacesContext.getCurrentInstance().getExternalContext().getRealPath(
		 * path);
		 * 
		 * try { report = (JasperReport) JRLoader.loadObjectFromFile(pathh); }
		 * catch (JRException e) { e.printStackTrace(); }
		 * 
		 * HashMap myMap = new HashMap<String, Object>();
		 * 
		 * if (conexion.getId() != null) { myMap.put("idConexion",
		 * conexion.getId()); } if (periodo != null) {
		 * myMap.put("idPeriodoFact", periodo.getId()); }
		 * 
		 * CJasperReports.createReport(connect(), report, myMap);
		 * CJasperReports.showViewer();
		 */
	}

	public void genFactura(Conexion conexion, PeriodoFacturacion periodo) throws SQLException {

		// String pathh =
		// FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);

		Map<String, Object> myMap = new HashMap<String, Object>();
		String where = "";
		if (conexion.getId() != null) {
			myMap.put("idConexion", conexion.getId());
		}
		if (periodo != null) {
			myMap.put("idPeriodoFact", periodo.getId());
		}

		FacesContext faceContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) faceContext.getExternalContext().getContext();
		String path = "/resources/reportes/Factura/factura.jasper";
		String pathh = servletContext.getRealPath(path);
		/*
		 * HttpServletResponse response = (HttpServletResponse)
		 * faceContext.getExternalContext().getResponse();
		 * response.addHeader("Content-disposition",
		 * "attachment;filename=reporteSocio.pdf");
		 * response.setContentType("application/pdf");
		 */
		// JasperPrint impresion = JasperFillManager.fillReport(pathh,
		// myMap,connect());

		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			// se carga el reporte
			// URL in=this.getClass().getResource( "reporte.jasper" );
			jasperReport = (JasperReport) JRLoader.loadObjectFromFile(pathh);
			// se procesa el archivo jasper
			jasperPrint = JasperFillManager.fillReport(jasperReport, myMap, connect());
			// impresion de reporte
			// TRUE: muestra la ventana de dialogo "preferencias de impresion"
			JasperPrintManager.printReport(jasperPrint, false);
		} catch (JRException ex) {
			System.err.println("Error iReport: " + ex.getMessage());
		}
	}

	private Connection connect() throws SQLException {
		Configuration configuration = new Configuration();
		configuration.configure("/persistencia/hibernate.cfg.xml");
		String servidor = configuration.getProperty("connection.url");

		String user = configuration.getProperty("connection.username");
		String pass = configuration.getProperty("connection.password");
		String driver = configuration.getProperty("connection.driver_class");
		Connection cnn = null;
		cnn = DriverManager.getConnection(servidor, user, pass);
		try {
			Class.forName(driver);
			cnn = DriverManager.getConnection(servidor, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cnn;
	}

	private void inicializar() {
		estadoSocio = null;
		zona = null;
		estConexion = null;
		formaPago = null;
		categoriaConex = null;
		periodoLectu = null;
		anioConsZona = null;
		anioConsDesde = 0;
		anioConsHasta = 0;
	}

}

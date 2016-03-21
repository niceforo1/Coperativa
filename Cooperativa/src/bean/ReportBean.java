package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;

import dao.PeriodoLecturaDAO;
import dao.impl.PeriodoLecturaDAOImplement;
import model.Conexion;
import model.PeriodoFacturacion;
import model.PeriodoLectura;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
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

	public ReportBean() {
		inicializar();
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

	public void verPlanillaLectura() throws SQLException {
		PeriodoLectura perLect = null;
		try {
			PeriodoLecturaDAO lecturaDAO = new PeriodoLecturaDAOImplement();
			perLect = lecturaDAO.buscarPeriodoLecturaAbierto();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Obtener Periodo Lectura Abierto: " + e.getMessage()));

		}

		String path = "/resources/reportes/PlanillaLecturas.jasper";
		String pathh = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);

		try {
			report = (JasperReport) JRLoader.loadObjectFromFile(pathh);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HashMap myMap = new HashMap<String, Object>();
		myMap.put("idPer", (perLect.getId() - 1));
		myMap.put("periodoFecha", perLect.getMes() + "/" + perLect.getAnio());

		// myMap.put("idEstado", estado);

		CJasperReports.createReport(connect(), report, myMap);
		CJasperReports.showViewer();
	}

	public void verSociosConexiones() throws SQLException {
		LOG.info("GABINO: PASO 1");
		String path = "/resources/reportes/Socios/Socios_Conexiones.jasper";
		String pathh = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
		LOG.info("GABINO: PASO 2");
		try {
			LOG.info("GABINO: PASO 3");
			report = (JasperReport) JRLoader.loadObjectFromFile(pathh);
			LOG.info("GABINO: PASO 4");
		} catch (JRException e) {
			e.printStackTrace();
		}
		LOG.info("GABINO: PASO 5");
		HashMap myMap = new HashMap<String, Object>();
		String where = "";
		LOG.info("GABINO: PASO 6");
		if (estConexion != null && !estConexion.equals("")) {
			LOG.info("GABINO: PASO 7");
			where = " and c.ID_ESTADO_CONEXION=" + Integer.parseInt(estConexion.split(",")[0]);
		}
		LOG.info("GABINO: PASO 8.1");
		if (formaPago != null && !formaPago.equals("")) {
			LOG.info("GABINO: PASO 8");
			where = where + " and c.ID_FORMA_PAGO=" + Integer.parseInt(formaPago.split(",")[0]);
		}
		LOG.info("GABINO: PASO 9.1");
		if (categoriaConex != null && !categoriaConex.equals("")) {
			LOG.info("GABINO: PASO 9");
			where = where + " and c.ID_CATEGORIA_CONEXION=" + Integer.parseInt(categoriaConex.split(",")[0]);
		}
		LOG.info("GABINO: PASO 10");
		myMap.put("whereClause", where);
		LOG.info("GABINO: PASO 11");
		CJasperReports.createReport(connect(), report, myMap);
		LOG.info("GABINO: PASO 12");
		CJasperReports.showViewer();
		LOG.info("GABINO: PASO 13");
	}

	public void verSociosEstado() throws SQLException {
		String path = "/resources/reportes/Socios/SociosEstadoZona.jasper";
		String pathh = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);

		try {
			report = (JasperReport) JRLoader.loadObjectFromFile(pathh);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HashMap myMap = new HashMap<String, Object>();
		String where = "";
		if (estadoSocio != null && !estadoSocio.equals("")) {
			myMap.put("estado", estadoSocio.split(",")[1]);
			where = " and s.ID_ESTADO_SOCIO= " + Integer.parseInt(estadoSocio.split(",")[0]);
		}
		if (zona != null && !zona.equals("")) {
			myMap.put("zona", zona.split(",")[1]);
			where = where + " and c.ID_NRO_ZONA= " + Integer.parseInt(zona.split(",")[0]);
		}
		myMap.put("whereClause", where);

		CJasperReports.createReport(connect(), report, myMap);
		CJasperReports.showViewer();
	}

	public void verFactura(Conexion conexion, PeriodoFacturacion periodo) throws SQLException {
		String path = "/resources/reportes/Factura/factura.jasper";
		String pathh = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);

		try {
			report = (JasperReport) JRLoader.loadObjectFromFile(pathh);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HashMap myMap = new HashMap<String, Object>();

		if (conexion.getId() != null ) {
			myMap.put("idConexion", conexion.getId());
		}
		if (periodo != null ) {
			myMap.put("idPeriodoFact", periodo.getId());
		}

		CJasperReports.createReport(connect(), report, myMap);
		CJasperReports.showViewer();
		
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
	}

}

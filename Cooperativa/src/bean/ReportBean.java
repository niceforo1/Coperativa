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

import org.hibernate.cfg.Configuration;

import dao.PeriodoLecturaDAO;
import dao.impl.PeriodoLecturaDAOImplement;
import model.PeriodoLectura;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.FileResolver;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRResourcesUtil;
import reportes.CJasperReports;

@ManagedBean(name="reportBean")
@ViewScoped
public class ReportBean implements Serializable{
	private JasperReport report;

	
	public ReportBean() {
	}
	
	public void verPlanillaLectura() throws SQLException{
		PeriodoLectura perLect = null;
		try{
			PeriodoLecturaDAO lecturaDAO = new PeriodoLecturaDAOImplement();
			perLect= lecturaDAO.buscarPeriodoLecturaAbierto();	
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Obtener Periodo Lectura Abierto: "+e.getMessage()));

		}
		 
		
		String path = "/resources/reportes/PlanillaLecturas.jasper";
		String pathh = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
		
		try {
			report = (JasperReport) JRLoader.loadObjectFromFile(pathh);
		} catch (JRException e) {
			e.printStackTrace();
		}

		HashMap myMap = new HashMap<String,Object>();
		myMap.put("idPer", (perLect.getId()-1));
		myMap.put("periodoFecha", perLect.getMes()+"/"+perLect.getAnio());
		
		//myMap.put("idEstado", estado);
		
		CJasperReports.createReport(connect(),report,myMap);
		CJasperReports.showViewer();
	}

	private Connection connect() throws SQLException{
		Configuration configuration = new Configuration();
		configuration.configure("/persistencia/hibernate.cfg.xml");
		String servidor= configuration.getProperty("connection.url");
		
		String user=configuration.getProperty("connection.username");
		String pass=configuration.getProperty("connection.password");
		String driver=configuration.getProperty("connection.driver_class");
		Connection cnn = null;	
		cnn=DriverManager.getConnection(servidor,user,pass);
		try {
			Class.forName(driver);
			cnn = DriverManager.getConnection(servidor,user,pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cnn;
	}
}

package reportes;

import java.sql.Connection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JRException;

public class CJasperReports {

	// private static JasperReport report;
	private static JasperPrint reportFilled;

	private static JasperViewer viewer;

	public static void createReport(Connection cnn, JasperReport report, HashMap<String, Object> hash) {
		try {
			reportFilled = new JasperPrint();
			reportFilled = JasperFillManager.fillReport(report, hash, cnn);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public static void showViewer() {
		viewer = new JasperViewer(reportFilled);
		// viewer.setVisible(true);
		viewer.viewReport(reportFilled, false);
	}
}

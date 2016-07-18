package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;

import org.apache.log4j.Logger;

import dao.CuentaCorrienteDAO;
import dao.impl.CuentaCorrienteDAOImplement;
import model.CuentaCorriente;

@ManagedBean(name = "cuentaCorrienteBean")
@ViewScoped
public class CuentaCorrienteBean {
	private static final Logger LOG = Logger.getLogger(CuentaCorrienteBean.class);

	private List<CuentaCorriente> listaCuentaCorriente;
	private Date fechaDesde;
	private Date fechaHasta;
	public Double total;

	@ManagedProperty(value = "#{lecturaBean}")
	private LecturaBean lectura;

	public CuentaCorrienteBean() {
		total= 0D;
	}

	public List<CuentaCorriente> getListaCuentaCorriente() {
		return listaCuentaCorriente;
	}

	public void setListaCuentaCorriente(List<CuentaCorriente> listaCuentaCorriente) {
		this.listaCuentaCorriente = listaCuentaCorriente;
	}

	public LecturaBean getLectura() {
		return lectura;
	}

	public void setLectura(LecturaBean lectura) {
		this.lectura = lectura;
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

	public Double getTotal() {
		return (Math.rint(total* 100) / 100);
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void consultar() {
		total = 0D;
		Long conexionID = lectura.getConexionID();
		CuentaCorrienteDAO cuentaCorrienteDAO = new CuentaCorrienteDAOImplement();
		listaCuentaCorriente = new ArrayList<CuentaCorriente>();
		List lista = null;
		try {
			lista = cuentaCorrienteDAO.obtenerCuentaCorriente(conexionID, fechaDesde, fechaHasta);
			int i = 0;
			while (i < lista.size()) {
				CuentaCorriente cc = new CuentaCorriente();
				Object[] pp = (Object[]) lista.get(i);
				BigDecimal temp = (BigDecimal) pp[0];
				cc.setIdConexion(temp.longValue());
				cc.setImporteTotal((Double) pp[1]);
				total += cc.getImporteTotal();
				temp = (BigDecimal) pp[2];
				cc.setMes(temp.longValue());
				temp = (BigDecimal) pp[3];
				cc.setAnio(temp.longValue());
				cc.setTipo((String) pp[4]);
				temp = (BigDecimal) pp[5];
				cc.setNumero(temp.longValue());
				i++;
				listaCuentaCorriente.add(cc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void retornarConexion(){
		lectura.retornarConexion();
		LOG.info("PASO");
		Iterator iterator = FacesContext.getCurrentInstance().getMessages();
		while (iterator.hasNext()) {
		    // do your checks
			iterator.next();
		    iterator.remove();
		}
	}

}

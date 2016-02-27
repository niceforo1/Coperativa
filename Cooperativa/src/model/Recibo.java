package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "RECIBOS")
public class Recibo implements Serializable {

	@Id
	@Column(name = "ID_RECIBO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "FECHA_EMISION_RECIBO")
	private Date fechaEmision;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONEXION")
	private Conexion conexion;

	@Column(name = "OBSERVACIONES", length = 5000)
	private String observaciones;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_FORMA_PAGO")
	private FormaPago formaPago;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID")
	private Usuario usuario;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ReciboItem> lstReciboItems;
	
	

	public Recibo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ReciboItem> getLstReciboItems() {
		return lstReciboItems;
	}

	public void setLstReciboItems(List<ReciboItem> lstReciboItems) {
		this.lstReciboItems = lstReciboItems;
	}

}

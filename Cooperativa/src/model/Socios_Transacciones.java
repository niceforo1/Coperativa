package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIOS_TRANSACCIONES")
public class Socios_Transacciones implements Serializable  {
	
	@Id
	@Column(name = "ID_TRANSACCION")
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SOCIO")
	//@SequenceGenerator(name = "SEQ_SOCIO", sequenceName = "SEQ_SOCIO")
	private long transaccion;
	
	@Column(name = "FECHA", nullable = false)
	private Date fecha;
	
	@Column(name = "TIPO_TRANSACCION", nullable = false)
	private String tipoTransaccion;
	
	@Column(name = "OBSERVACIONES", length=5000)
	private String observaciones;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID", nullable = false)
	private Usuario usuario;
	
	public Socios_Transacciones() {
	}

	public long getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(long transaccion) {
		this.transaccion = transaccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}

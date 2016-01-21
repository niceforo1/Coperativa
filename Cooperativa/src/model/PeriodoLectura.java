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
@Table(name = "PERIODO_LECTURAS")
public class PeriodoLectura implements Serializable {
	@Id
	@Column(name = "PERIODO_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "MES", nullable = false)
	private long mes;

	@Column(name = "ANIO", nullable = false)
	private long anio;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ESTADO_PERIODO_ID")
	private EstadoPeriodo estadoPeriodo;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID_ALTA")
	private Usuario usuarioAltaPeriodo;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID_CIERRE")
	private Usuario usuarioCierrePeriodo;

	@Column(name = "FECHA_CIERRE_LECTURA")
	private Date fechaCierreLectura;

	@Column(name = "FECHA_ULTIMA_MOD")
	private Date fechaUltimaMod;

	public PeriodoLectura() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getMes() {
		return mes;
	}

	public void setMes(long mes) {
		this.mes = mes;
	}

	public long getAnio() {
		return anio;
	}

	public void setAnio(long anio) {
		this.anio = anio;
	}

	public EstadoPeriodo getEstadoPeriodo() {
		return estadoPeriodo;
	}

	public void setEstadoPeriodo(EstadoPeriodo estadoPeriodo) {
		this.estadoPeriodo = estadoPeriodo;
	}

	public Usuario getUsuarioAltaPeriodo() {
		return usuarioAltaPeriodo;
	}

	public void setUsuarioAltaPeriodo(Usuario usuarioAltaPeriodo) {
		this.usuarioAltaPeriodo = usuarioAltaPeriodo;
	}

	public Usuario getUsuarioCierrePeriodo() {
		return usuarioCierrePeriodo;
	}

	public void setUsuarioCierrePeriodo(Usuario usuarioCierrePeriodo) {
		this.usuarioCierrePeriodo = usuarioCierrePeriodo;
	}

	public Date getFechaCierreLectura() {
		return fechaCierreLectura;
	}

	public void setFechaCierreLectura(Date fechaCierreLectura) {
		this.fechaCierreLectura = fechaCierreLectura;
	}

	public Date getFechaUltimaMod() {
		return fechaUltimaMod;
	}

	public void setFechaUltimaMod(Date fechaUltimaMod) {
		this.fechaUltimaMod = fechaUltimaMod;
	}

	@Override
	public String toString() {
		return "PeriodoLectura [id=" + id + ", mes=" + mes + ", anio=" + anio + ", estadoPeriodo=" + estadoPeriodo
				+ ", usuarioAltaPeriodo=" + usuarioAltaPeriodo + ", usuarioCierrePeriodo=" + usuarioCierrePeriodo
				+ ", fechaCierreLectura=" + fechaCierreLectura + ", fechaUltimaMod=" + fechaUltimaMod + "]";
	}

}

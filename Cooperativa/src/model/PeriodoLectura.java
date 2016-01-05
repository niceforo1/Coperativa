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
	private Long mes;

	@Column(name = "ANIO", nullable = false)
	private Long anio;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ESTADO_PERIODO_ID")
	private EstadoPeriodo estadoPeriodo;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID")
	private Usuario usuario;

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

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public EstadoPeriodo getEstadoPeriodo() {
		return estadoPeriodo;
	}

	public void setEstadoPeriodo(EstadoPeriodo estadoPeriodo) {
		this.estadoPeriodo = estadoPeriodo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaUltimaMod() {
		return fechaUltimaMod;
	}

	public void setFechaUltimaMod(Date fechaUltimaMod) {
		this.fechaUltimaMod = fechaUltimaMod;
	}

}

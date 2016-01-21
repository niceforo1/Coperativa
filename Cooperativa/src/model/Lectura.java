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
@Table(name = "LECTURAS")
public class Lectura implements Serializable, Comparable<Lectura> {

	@Id
	@Column(name = "ID_LECTURA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PERIODO_ID")
	private PeriodoLectura periodoLectura;

	@Column(name = "LECTURERO")
	private String lecturero;

	@Column(name = "FECHA_GENERACION")
	private Date fechaGeneracion;

	@Column(name = "FECHA_REGISTRO_LECTURA")
	private Date fechaRegistroLectura;

	@Column(name = "LECTURA_ANTERIOR")
	private long lecturaAnterior;

	@Column(name = "LECTURA_ACTUAL")
	private long lecturaActual;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USR_ID")
	private Usuario usuario;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_CONEXION")
	private Conexion conexion;

	public Lectura() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLecturero() {
		return lecturero;
	}

	public void setLecturero(String lecturero) {
		this.lecturero = lecturero;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFechaRegistroLectura() {
		return fechaRegistroLectura;
	}

	public void setFechaRegistroLectura(Date fechaRegistroLectura) {
		this.fechaRegistroLectura = fechaRegistroLectura;
	}

	public long getLecturaAnterior() {
		return lecturaAnterior;
	}

	public void setLecturaAnterior(long lecturaAnterior) {
		this.lecturaAnterior = lecturaAnterior;
	}

	public long getLecturaActual() {
		return lecturaActual;
	}

	public void setLecturaActual(long lecturaActual) {
		this.lecturaActual = lecturaActual;
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

	public PeriodoLectura getPeriodoLectura() {
		return periodoLectura;
	}

	public void setPeriodoLectura(PeriodoLectura periodoLectura) {
		this.periodoLectura = periodoLectura;
	}

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	@Override
	public String toString() {
		return "Lectura [id=" + id + ", periodoLectura=" + periodoLectura + ", lecturero=" + lecturero
				+ ", fechaGeneracion=" + fechaGeneracion + ", fechaRegistroLectura=" + fechaRegistroLectura
				+ ", lecturaAnterior=" + lecturaAnterior + ", lecturaActual=" + lecturaActual + ", observaciones="
				+ observaciones + ", usuario=" + usuario + "]";
	}

	@Override
	public int compareTo(Lectura o) {
		if (id > o.id) {
			return -1;
		}
		if (id < o.id) {
			return 1;
		}
		return 0;
	}
}

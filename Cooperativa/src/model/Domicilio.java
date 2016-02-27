package model;

import java.io.Serializable;

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
@Table(name = "DOMICILIOS")
public class Domicilio implements Serializable {

	@Id
	@Column(name = "ID_DOMICILIO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_DOMICILIO", nullable = false)
	private TipoDomicilio tipoDomicilio;

	@Column(name = "CALLE")
	private String calle;

	@Column(name = "NUMERO")
	private long numero;

	@Column(name = "DPTO_OF")
	private String departamento;

	@Column(name = "BARRIO")
	private String barrio;

	@Column(name = "LOCALIDAD")
	private String localidad;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_PROVINCIA")
	private Provincia provincia;

	@Column(name = "CODIGO_POST")
	private String codPostal;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_PAIS", nullable = false)
	private Pais pais;

	@Column(name = "TE_FIJO")
	private String telFijo;

	public Domicilio() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDomicilio getTipoDomicilio() {
		return tipoDomicilio;
	}

	public void setTipoDomicilio(TipoDomicilio tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getTelFijo() {
		return telFijo;
	}

	public void setTelFijo(String telFijo) {
		this.telFijo = telFijo;
	}

	@Override
	public String toString() {
		return "Domicilio [id=" + id + ", tipoDomicilio=" + tipoDomicilio + ", calle=" + calle + ", numero=" + numero
				+ ", departamento=" + departamento + ", barrio=" + barrio + ", localidad=" + localidad + ", provincia="
				+ provincia + ", codPostal=" + codPostal + ", pais=" + pais + ", telFijo=" + telFijo + "]";
	}

}// end Domicilio
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DOMICILIO")
public class Domicilio implements Serializable{
	
	@Id
    @Column(name = "ID_DOMICILIO")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DOMICILIO")
    @SequenceGenerator(name = "SEQ_DOMICILIO", sequenceName = "SEQ_DOMICILIO")
    private long id;
    
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_SOCIO", nullable = false)
	private Socio socio;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_DOMICILIO", nullable = false)
	private TipoDomicilio tipoDomicilio;
	
	@Column(name = "CALLE", nullable = false)
    private String calle;
    
	@Column(name = "NUMERO", nullable = false)
	private long numero;
	
    @Column(name = "DPTO/OF")
    private String departamento;
    
    @Column(name = "BARRIO", nullable = false)
    private String barrio;
    
    @Column(name = "LOCALIDAD", nullable = false)
    private String localidad;
    
    @OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_PROVINCIA", nullable = false)
	private Provincia provincia;
    
    @Column(name = "CODIGO_POST", nullable = false)
    private String codPostal;
    
    @OneToOne(cascade = CascadeType.PERSIST)
   	@JoinColumn(name = "ID_PAIS", nullable = false)
   	private Pais pais;

    @Column(name = "TE_FIJO", nullable = false)
    private long telFijo;
    
	public Domicilio(){

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
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

	public long getTelFijo() {
		return telFijo;
	}

	public void setTelFijo(long telFijo) {
		this.telFijo = telFijo;
	}

}//end Domicilio
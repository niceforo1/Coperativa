package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INFO_TNARANJA")
public class TarjetaNaranja implements Serializable{

	@Id
	@Column(name = "ID_T_NARANJA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "TITULAR")
	private String titular;
	
	@Column(name = "NRO_TARJETA")
	private long nroTarjeta;
	
	@Column(name = "VENCIMIENTO")
	private String vencimiento;
	
	@Column(name = "OBSERVACIONES")
	private String obsercaciones;
	
	public TarjetaNaranja() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public long getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(long nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public String getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getObsercaciones() {
		return obsercaciones;
	}

	public void setObsercaciones(String obsercaciones) {
		this.obsercaciones = obsercaciones;
	}
	
}

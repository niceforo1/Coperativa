package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

public class CuentaCorriente implements Serializable {

	@Column(name = "ID_CONEXION")
	private Long idConexion;

	@Column(name = "IMPORTE_TOTAL")
	private Double importeTotal;

	@Column(name = "MES")
	private Long mes;

	@Column(name = "ANIO")
	private Long anio;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "NRO_COMPROBANTE")
	private Long numero;

	public CuentaCorriente() {

	}

	public Long getIdConexion() {
		return idConexion;
	}

	public void setIdConexion(Long idConexion) {
		this.idConexion = idConexion;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

}

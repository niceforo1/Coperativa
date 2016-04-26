package model;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONCEPTO_FACTURACION")
public class ConceptoFacturacion implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "ALICUOTA_IVA", nullable = false, precision = 15, scale = 2)
	private Double alicuotaIva;
	
	@Column(name = "MONTO_PRECIO", nullable = false, precision = 15, scale = 2)
	private Double montoPrecio;

	public ConceptoFacturacion() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getAlicuotaIva() {
		return (Math.rint(alicuotaIva*100)/100);
	}

	public void setAlicuotaIva(Double alicuotaIva) {
		this.alicuotaIva = (Math.rint(alicuotaIva*100)/100);
	}

	public Double getMontoPrecio() {
		return (Math.rint(montoPrecio*100)/100);
	}

	public void setMontoPrecio(Double montoPrecio) {
		this.montoPrecio = (Math.rint(montoPrecio*100)/100);
	}

}

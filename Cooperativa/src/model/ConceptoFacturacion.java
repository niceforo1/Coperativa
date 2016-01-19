package model;

import java.io.Serializable;

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

	@Column(name = "ALICUOTA_IVA", nullable = false)
	private Float alicuotaIva;

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

	public Float getAlicuotaIva() {
		return alicuotaIva;
	}

	public void setAlicuotaIva(Float alicuotaIva) {
		this.alicuotaIva = alicuotaIva;
	}

}

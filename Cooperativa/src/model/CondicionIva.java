package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONDICION_IVA")
public class CondicionIva implements Serializable {

	@Id
	@Column(name = "ID_CONDICION_IVA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ABREVIATURA", unique = true, nullable = false)
	private String codigo;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "PORCENTAJE", nullable = false, precision = 15, scale = 2)
	private Double porcentaje;

	@Column(name = "IVA_OTROS_CONCEPTOS", nullable = true, precision = 15, scale = 2)
	private Double ivaOtrosConceptos;

	public CondicionIva() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPorcentaje() {
		return (Math.rint(porcentaje*100)/100);
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = (Math.rint(porcentaje*100)/100);
	}

	public Double getIvaOtrosConceptos() {
		return (Math.rint(ivaOtrosConceptos*100)/100);
	}

	public void setIvaOtrosConceptos(Double ivaOtrosConceptos) {
		this.ivaOtrosConceptos = (Math.rint(ivaOtrosConceptos*100)/100);
	}

	@Override
	public String toString() {
		return "ConceptoIva [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", observaciones="
				+ "]";
	}
}// end ConceptoIva
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
public class CondicionIva implements Serializable{
	
	@Id
    @Column(name = "ID_CONDICION_IVA")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CON_IVA")
    @SequenceGenerator(name = "SEQ_CON_IVA", sequenceName = "SEQ_CON_IVA")
    private int id;

    @Column(name = "ABREVIATURA", unique = true, nullable = false)
    private String codigo;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "PORCENTAJE", nullable = false)
    private int porcentaje;

	public CondicionIva() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	@Override
	public String toString() {
		return "ConceptoIva [id=" + id + ", codigo=" + codigo
				+ ", descripcion=" + descripcion + ", observaciones="
				+ "]";
	}
}// end ConceptoIva
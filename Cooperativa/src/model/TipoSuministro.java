package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_SUMINISTRO")
public class TipoSuministro implements Serializable {

	@Id
	@Column(name = "ID_TIPO_SUMINISTRO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "IMPORTE", precision = 15, scale = 2)
	private Double importe;

	public TipoSuministro() {
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

	public Double getImporte() {
		return (Math.rint(importe*100)/100);
	}

	public void setImporte(Double importe) {
		this.importe = (Math.rint(importe*100)/100);
	}

}

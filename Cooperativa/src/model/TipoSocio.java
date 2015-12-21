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
@Table(name = "TIPO_SOCIO")
public class TipoSocio implements Serializable {

	@Id
	@Column(name = "ID_TIPO_SOCIO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	public TipoSocio() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public String toString() {
		return "TipoSocio [codigo="  + ", descripcion=" + descripcion
				+ ", observaciones=" + "]";
	}
}
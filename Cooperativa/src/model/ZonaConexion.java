package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ZONAS_CONEXIONES")
public class ZonaConexion implements Serializable {

	@Id
    @Column(name = "ID_ZONA")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    
	public ZonaConexion() {
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
}

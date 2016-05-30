package model.generadores;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "GENERADOR_FACTURA_B")
public class GenFacturaB implements Serializable {

	@Id
	@Column(name = "ID_GEN_FACTURA_B")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	public GenFacturaB() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
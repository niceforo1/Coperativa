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
@Table(name = "GENERADOR_FACTURA_A")
public class GenFacturaA implements Serializable {

	@Id
	@Column(name = "ID_GEN_FACTURA_A")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	public GenFacturaA() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
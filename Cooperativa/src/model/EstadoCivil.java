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
@Table(name="ESTADO_CIVIL")
public class EstadoCivil implements Serializable {

	@Id
    @Column(name = "ID_ESTADO_CIVIL")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EST_CIV")
    @SequenceGenerator(name = "SEQ_EST_CIV", sequenceName = "SEQ_EST_CIV")
    private long id;
    
    
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

	public EstadoCivil(){

	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

}//end EstadoCivil
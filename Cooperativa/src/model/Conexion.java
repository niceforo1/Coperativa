package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONDICION_IVA")
public class Conexion implements Serializable {

	@Id
    @Column(name = "ID_CONEXION")
    private int id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_ESTADO_CONEXION", nullable = false)
	private EstadoConexion estadoConexion;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_NRO_ZONA", nullable = false)
	private ZonaConexion zonaConexion;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_DOMICILIO_SERV", nullable = false)
	private Domicilio domicilioServicio;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_DOMICILIO_FACT")
	private Domicilio domicilioFacturacion;
	
	@Column(name = "FECHA_ALTA", nullable = false)
	private Date fechaAlta;
	
	@Column(name = "TERRENO")
	private long terreno;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_TIPO_TERRENO", nullable = false)
	private TipoTerreno tipoTerreno;
	
	@Column(name = "PILETA")
	private long pileta;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ID_REGIMEN_PROPIEDAD", nullable = false)
	private RegimenPropiedad regimenPropiedad;
	
	public Conexion() {
	}

}

package bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import utils.UtilidadesVarias;
import dao.UsuarioDAO;
import dao.UsuarioTiposDAO;
import dao.impl.UsuarioDAOImplement;
import dao.impl.UsuarioTiposDAOImplement;
import model.Usuario;
import model.UsuarioTipo;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private String passRep;
	private List<UsuarioTipo> lstTipoUsu;
	private Long tipoUsuId;

	public UsuarioBean() {
		incializar();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPassRep() {
		return passRep;
	}

	public void setPassRep(String passRep) {
		this.passRep = UtilidadesVarias.getStringMessageDigest(passRep);
	}

	private void incializar() {
		usuario = new Usuario();
		tipoUsuId = null;
		passRep = null;
	}

	public Long getTipoUsuId() {
		return tipoUsuId;
	}

	public void setTipoUsuId(Long tipoUsuId) {
		this.tipoUsuId = tipoUsuId;
	}

	public List<UsuarioTipo> getLstTipoUsu() {
		UsuarioTiposDAO usuarioTiposDAO = new UsuarioTiposDAOImplement();
		try {
			lstTipoUsu = usuarioTiposDAO.listaUsuariosTipo();			
		} catch (Exception e) {
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error", "Error al buscar Usuarios Tipos: "
											+ e.getMessage()));
		}
		return lstTipoUsu;
	}

	public void setLstTipoUsu(List<UsuarioTipo> lstTipoUsu) {
		this.lstTipoUsu = lstTipoUsu;
	}

	public void insertarUsuario() {
		try {			
			UsuarioDAO usuarioDAO = new UsuarioDAOImplement();
			UsuarioTiposDAO usuarioTiposDAO = new UsuarioTiposDAOImplement();
			usuario.setUsuarioTipo(usuarioTiposDAO.buscarUsuarioTipoId(tipoUsuId));
			usuarioDAO.insertarUsuario(usuario);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correctamente", "Se cargó correctamente."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Error al procesar: " + e.getMessage()));
		}
	}
}

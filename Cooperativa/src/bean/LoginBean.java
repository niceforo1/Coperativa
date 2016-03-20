package bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import model.Usuario;
import utils.UtilidadesVarias;
import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImplement;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	private static final Logger LOG = Logger.getLogger(LoginBean.class); 

	private static final long serialVersionUID = 7765876811740798583L;
	private Usuario usuario;
	private String username;
	private String password;
	private String diaPrimerVenc = "10";
	private String diaSegVenc = "20";
	

	// private boolean tipoUsuario = false;

	private boolean loggedIn;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	public String doLogin() {
		UsuarioDAO usuarioDAO = new UsuarioDAOImplement();
		try {
			LOG.info("Se ingreso con el usuario "+username);
			this.usuario = usuarioDAO.buscarUsuario(username,
					UtilidadesVarias.getStringMessageDigest(password));
			if (usuario != null) {
				loggedIn = true;
				return navigationBean.redirectToWelcome();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage("Error al ingresar!","Usuario y/o Contraseña Incorrectas");
		LOG.error("Se ingreso con el usuario "+username +" Usuario y/o Contraseña Incorrectas.");

		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return navigationBean.toLogin();
	}

	public String doLogout() {
		loggedIn = false;

		FacesMessage msg = new FacesMessage("Salida satisfactoria!");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return navigationBean.toLogin();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String formatearFechaMes(Date fecha, String formato) {
		return fecha == null ? "" : new SimpleDateFormat(formato).format(fecha);
	}

	public boolean tipoUsuario() {
		boolean bool = false;
		return bool;
	}

	public String getDiaPrimerVenc() {
		return diaPrimerVenc;
	}

	public void setDiaPrimerVenc(String diaPrimerVenc) {
		this.diaPrimerVenc = diaPrimerVenc;
	}

	public String getDiaSegVenc() {
		return diaSegVenc;
	}

	public void setDiaSegVenc(String diaSegVenc) {
		this.diaSegVenc = diaSegVenc;
	}
	
	public void validateSeisDigitos(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
      if (((String)arg2).length()<6) {
         throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe tener al menos 6 caracteres"));
      }
	}

}

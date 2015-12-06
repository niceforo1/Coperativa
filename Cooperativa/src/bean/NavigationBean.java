package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1520318172495977648L;

	public String redirectToLogin() {
		return "/login.xhtml?faces-redirect=true";
	}

	public String toLogin() {
		return "/login.xhtml";
	}
	
	public String redirectToWelcome() {
		return "/index.xhtml?faces-redirect=true";
	}
		
	public String toWelcome() {
		return "/index.xhtml";
	}
	
}

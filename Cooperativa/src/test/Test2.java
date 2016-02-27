package test;

import java.util.Date;

import dao.ConexionDAO;
import dao.ConexionesSaldosDAO;
import dao.PaisDAO;
import dao.PeriodoCespDAO;
import dao.impl.ConexionDAOImplement;
import dao.impl.ConexionesSaldosDAOImplement;
import dao.impl.PaisDAOImplement;
import dao.impl.PeriodoCespDAOImplement;
import model.ConexionesSaldos;
import model.Pais;

public class Test2 {

	public static void main(String[] args) {
		Pais pais = new Pais();
		PaisDAO dao = new PaisDAOImplement();
		pais.setDescripcion("PARAGUAY");
		try {
			pais.setId(dao.insertarPais(pais));
			System.out.println("EL ID DEL PAIS ES: "+ pais.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


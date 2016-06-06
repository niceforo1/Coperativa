package test;

import java.util.List;

import dao.CuentaCorrienteDAO;
import dao.impl.CuentaCorrienteDAOImplement;
import model.CuentaCorriente;

public class Test2 {

	public static void main(String[] args) {
		/*Pais pais = new Pais();
		PaisDAO dao = new PaisDAOImplement();
		pais.setDescripcion("PARAGUAY");
		try {
			pais.setId(dao.insertarPais(pais));
			System.out.println("EL ID DEL PAIS ES: "+ pais.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*
		CuentaCorrienteDAO cuentaCorrienteDAO = new CuentaCorrienteDAOImplement();
		List lista = null;
		try {
			lista=cuentaCorrienteDAO.obtenerCuentaCorriente();
			int i=0;
			while(i< lista.size()){
				CuentaCorriente cc = new CuentaCorriente(); 
				Object[] pp =(Object[])lista.get(0);								
				cc.setIdConexion((Long)pp[0]);
				cc.setImporteTotal((Double)pp[1]);
				cc.setMes((Long)pp[2]);
				cc.setAnio((Long)pp[3]);
				cc.setTipo((String)pp[4]);
				i++;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(lista.toString());*/
	}

}


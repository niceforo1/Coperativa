package dao;

import java.util.List;

import model.ConexionesSaldos;

public interface ConexionesSaldosDAO {
	public List<ConexionesSaldos> listaConexionesSaldos() throws Exception;
	public void insertarConexionesSaldos(ConexionesSaldos conexionesSaldo) throws Exception;
	public void modificarConexionesSaldos(ConexionesSaldos conexionesSaldo) throws Exception;
	public void eliminarConexionesSaldos(ConexionesSaldos conexionesSaldo) throws Exception;
	public ConexionesSaldos buscarConexionesSaldosId(Long id) throws Exception;
	public ConexionesSaldos buscarConexionesSaldosConexion(Long id) throws Exception;
}


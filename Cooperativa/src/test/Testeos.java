package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

//import utils.UtilidadesVarias;
//import model.ComprobanteCab;
//import model.ComprobanteDetalle;
//import model.EstadoFacturacion;
//import model.EstadoItemFacturar;
//import model.Facturacion;
//import model.ItemFacturar;
//import model.Socio;
//import model.TipoEdadSexo;
//import model.Usuario;
//import model.Valorizacion;
//import dao.ComprobanteDAO;
//import dao.EstadoComprobanteDAO;
//import dao.EstadoFacturacionDAO;
//import dao.EstadoItemFacturarDAO;
//import dao.FacturacionDAO;
//import dao.ItemFacturarDAO;
//import dao.SocioDAO;
//import dao.UsuarioDAO;
//import dao.ValorizacionDAO;
//import dao.impl.ComprobanteDAOImplement;
//import dao.impl.EstadoComprobanteDAOImplement;
//import dao.impl.EstadoFacturacionDAOImplement;
//import dao.impl.EstadoItemFacturarDAOImplement;
//import dao.impl.FacturacionDAOImplement;
//import dao.impl.ItemFacturarDAOImplement;
//import dao.impl.SocioDAOImplement;
//import dao.impl.UsuarioDAOImplement;
//import dao.impl.ValorizacionDAOImplement;

//public class Testeos {
//
//	// INICIO PROCESO FACTURACION//
//	public static void main(String[] args) {
////		EstadoFacturacionDAO estadoFacturacionDAO = new EstadoFacturacionDAOImplement();
////		EstadoFacturacion est = new EstadoFacturacion();
////		EstadoFacturacion est1 = new EstadoFacturacion();
////		
////		EstadoComprobanteDAO daoEstComp = new EstadoComprobanteDAOImplement();
////		
////		est.setCodigo("GEN");
////		est.setDescripcion("GENERADO");
////		
////		est1.setCodigo("ANU");
////		est1.setDescripcion("ANULADO");
////		estadoFacturacionDAO.insertarEstadoFacturacion(est);
////		estadoFacturacionDAO.insertarEstadoFacturacion(est1);
//		
////		EstadoItemFacturar estIF = new EstadoItemFacturar();
////		EstadoItemFacturar estIF1 = new EstadoItemFacturar();
////		
////		estIF.setCodigo("PEN");
////		estIF.setDescripcion("PENDIENTE");
////		
////		estIF1.setCodigo("GEN");
////		estIF1.setDescripcion("GENERADO");
////		
////		EstadoItemFacturarDAO daoestIF = new EstadoItemFacturarDAOImplement();
////		
////		try {
////			daoestIF.insertarEstadoItemFacturar(estIF);
////			daoestIF.insertarEstadoItemFacturar(estIF1);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		
////		
//////		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");	    
//////	    try {
//////			Date periodoFact = sdf.parse("11/2015");
//////			//Calendar cc = Calendar.getInstance();
//////			for(int i= 0; i<3;i++){
//////				Calendar cc = Calendar.getInstance();
//////				cc.setTime(periodoFact);
//////				cc.add(Calendar.MONTH, i);
//////				System.out.println(cc.getTime());
//////			}
////			
////			/*
////			cc.setTime(periodoFact);
////			cc.add(Calendar.MONTH, 1);
////			System.out.println(cc.getTime());
////			cc.add(Calendar.MONTH, 1);
////			System.out.println(cc.getTime());
////			cc.add(Calendar.MONTH, 1);
////			System.out.println(cc.getTime());
////			*/
//////		} catch (ParseException e) {
//////			// TODO Auto-generated catch block
//////			e.printStackTrace();
//////		}
////	}
////	/*public static void main(String[] args) {
////		Facturacion facturacion = new Facturacion();
////		EstadoFacturacionDAO estadoFacturacionDAO = new EstadoFacturacionDAOImplement();
////		FacturacionDAO facturacionDAO = new FacturacionDAOImplement();
////		EstadoComprobanteDAO daoEstComp = new EstadoComprobanteDAOImplement();
////		UsuarioDAO usuarioDAO = new UsuarioDAOImplement();
////		List<ComprobanteCab> lstCompCab = new ArrayList<ComprobanteCab>();
////		Date dd = Calendar.getInstance().getTime();
////		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
////		String estComp = "GEN";
////		try {
////			Date periodoFact = sdf.parse("11/2015");
////			System.out.println("SE FACTURO??" + facturacionDAO.buscarFacturacionPeriodo(periodoFact));
////			if (facturacionDAO.buscarFacturacionPeriodo(periodoFact)) {
////				facturacion.setEstadoFact(estadoFacturacionDAO.buscarEstadoFacturacion("GEN"));
////				facturacion.setFechaCarga(dd);
////				facturacion.setPeriodoFacturado(periodoFact);
////				facturacion.setUsuarioCarga(usuarioDAO.buscarUsuario("gabino",
////						UtilidadesVarias.getStringMessageDigest("123456")));
////				SocioDAO socioDAO = new SocioDAOImplement();
////				for (Socio soc : socioDAO.listaSociosTitulares()) {
////					System.out.println("SOCIO Nº: " + soc.getNumero()
////							+ " DNI: "
////					// + soc.getPersona().getDni() + " PLAN: "
////					// + soc.getPlan().getCodigo() + " - "
////					// + soc.getPlan().getDescripcion()
////							);
////					List<ComprobanteDetalle> list = new ArrayList<ComprobanteDetalle>();
////					ComprobanteCab comp = new ComprobanteCab();
////					// comp.setContabPeriodo(7L);
////					// comp.setContabEjercicio(7L);
////					comp.setFechaAlta(dd);
////					comp.setFechaAnulacion(null);
////					// comp.setFechaContable(dd);
////					comp.setFechaEmision(dd);
////					// comp.setFechaIva(null);
////					// comp.setFechaPresentacion(dd);
////					// comp.setNumeroImpresion(0001);
////					comp.setPrimerVenc(dd);
////					comp.setSegundoVenc(dd);
////					// comp.setPv(25);
////					comp.setSocio(soc);
////					comp.setTipoComprobante(soc.getCliente()
////							.getTipoComprobante());
////					comp.setEstComprobante(daoEstComp.buscarEstadoComp(estComp));
////					list.addAll(crearDet(soc));
////					for (Socio soc2 : socioDAO.listaSociosPorTitular(soc
////							.getNumero())) {
////						System.out.println("\tSOCIO Nº: " + soc2.getNumero()
////								+ " DNI: " + soc2.getPersona().getDni()
////								+ " PLAN: " + soc2.getPlan().getCodigo()
////								+ " - " + soc2.getPlan().getDescripcion());
////						list.addAll(crearDet(soc2));
////					}
////					comp.setLstCompDet(list);
////
////					ItemFacturarDAO itDAO = new ItemFacturarDAOImplement();
////					for (ItemFacturar item : itDAO.listaItemFacturarSocio(soc
////							.getNumero())) {
////						// filtrar por periodo facturacion correspondiente
////						ComprobanteDetalle cd = new ComprobanteDetalle();
////						cd.setCantidad(1);
////						cd.setConcepto(item.getConcepto());
////						cd.setImporteCIva(item.getImporteCuotas());
////						cd.setImporteSIva(item.getImporteCuotas());
////						cd.setPrecioUnitario(item.getImporteCuotas());
////						cd.setSaldo(item.getImporteCuotas());
////						System.out.println(" ITEM FACTURAR CONCEPTO: "
////								+ item.getConcepto());
////						System.out.println(" ITEM FACTURAR IMPORTE "
////								+ item.getImporteCuotas());
////						list.add(cd);
////					}
////					float total = 0;
////
////					for (ComprobanteDetalle cd : list) {
////						total += cd.getImporteCIva();
////					}
////					System.out.println("TOTAL: " + total);
////					System.out.println("COMPROBANTE: " + comp.toString());
////					ComprobanteDAO comprobanteDAO = new ComprobanteDAOImplement();
////					// comprobanteDAO.insertarComprobanteCab(comp);
////					lstCompCab.add(comp);
////				}
////				facturacion.setLstComprobantes(lstCompCab);
////				facturacionDAO.insertarFacturacion(facturacion);
////
////			}else{
////				
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}*/
////
////	protected static List<ComprobanteDetalle> crearDet(Socio soc) {
////		ValorizacionDAO vdao = new ValorizacionDAOImplement();
////		List<ComprobanteDetalle> list = new ArrayList<ComprobanteDetalle>();
////		try {
////			for (Valorizacion val : vdao.listaValorizacionesPlan(soc.getPlan())) {
////				ComprobanteDetalle cd = new ComprobanteDetalle();
////				cd.setConcepto(val.getServicio().getConcepto());
////				cd.setCantidad(1);
////				System.out.print(val.getServicio().getCodigo() + " -> ");
////				if (val.getLstTipoCantCapitas().size() > 0) {
////					System.out.print("ESTA VALORIZADO POR CANT CAPITAS");
////				} else if (val.getLstTipoEdadSexo().size() > 0) {
////					System.out.print("ESTA VALORIZADO POR TIPO EDAD SEXO");
////					int anio = UtilidadesVarias.calcularEdad(soc.getPersona()
////							.getFechaNacimiento());
////					for (TipoEdadSexo tipo : val.getLstTipoEdadSexo()) {
////						if ((tipo.getEdadDesde() <= anio)
////								&& (tipo.getEdadHasta() >= anio)
////								&& (tipo.getSexo().getId() == soc.getPersona()
////										.getSexo().getId())) {
////							System.out.println("AÑOS: " + anio + " IMPORTE "
////									+ tipo.getImporte());
////							cd.setPrecioUnitario(tipo.getImporte());
////							cd.setImporteCIva(tipo.getImporte());
////							cd.setImporteSIva(tipo.getImporte());
////						}
////					}
////				} else if (val.getLstTipoValImporte().size() > 0) {
////					System.out.print("ESTA VALORIZADO POR TIPO VAL IMPORTE");
////					cd.setPrecioUnitario(val.getLstTipoValImporte().get(0)
////							.getImporte());
////					cd.setImporteCIva(val.getLstTipoValImporte().get(0)
////							.getImporte());
////					cd.setImporteSIva((val.getLstTipoValImporte().get(0)
////							.getImporte()));
////					System.out.println("CON UN MONTO ASOCIADO DE: "
////							+ val.getLstTipoValImporte().get(0).getImporte());
////				}
////				list.add(cd);
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		return list;
////	}
////}
////// FIN PROCESO FACTURACION//
////

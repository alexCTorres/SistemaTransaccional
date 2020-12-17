package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.TransferenciasExternasDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TransferenciasExternas;

@Stateless
public class GestionTransferenciasExternasON  {


	@Inject
	private TransferenciasExternasDAO daoTransExternas;
	
	public boolean registrarTransferenciasExternas(TransferenciasExternas transExternas) throws Exception {
		try {
			daoTransExternas.insertJPA(transExternas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
	
	public boolean actualizarTransferenciasExternas(TransferenciasExternas transExternas) throws Exception {
		try {
			daoTransExternas.updateJPA(transExternas);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
	
	public TransferenciasExternas buscarTransferenciasExternas(int id) throws Exception {
		TransferenciasExternas tranExternas = new TransferenciasExternas();
		try {
			tranExternas = daoTransExternas.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return tranExternas;
	}
	
	public boolean eliminarTransferenciasExternas(int id) throws Exception {
		try {
			TransferenciasExternas te = daoTransExternas.readJPA(id);
			if(te==null) {
				System.out.println("Tranferencia externa no encontrada");
			}else {
				daoTransExternas.deleteJPA(te.getId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error al registrar");
		}
		return true;
	}
}

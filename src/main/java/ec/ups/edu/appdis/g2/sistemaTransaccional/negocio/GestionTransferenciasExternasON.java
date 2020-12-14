package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.appdis.g2.sistemaTransaccional.dao.TransferenciasExternasDAO;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TransferenciasExternas;

@Stateless
public class GestionTransferenciasExternasON {


	@Inject
	private TransferenciasExternasDAO daoTransExternas;
	
	public boolean registrarTransferenciasExternas(TransferenciasExternas tranExternas) {
		return true;
	}
	
	public boolean actualizarTransferenciasExternas(TransferenciasExternas tranExternas) {
		return true;
	}
	
	public TransferenciasExternas buscarTransferenciasExternas(int id) {
		TransferenciasExternas tranExternas = new TransferenciasExternas();
		try {
			tranExternas = daoTransExternas.readJPA(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tranExternas;
	}
	
	public boolean eliminarTransferenciasExternas(int id) {
		return true;
	}
}

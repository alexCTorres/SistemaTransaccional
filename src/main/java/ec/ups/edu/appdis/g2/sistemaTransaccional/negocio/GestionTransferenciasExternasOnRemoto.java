package ec.ups.edu.appdis.g2.sistemaTransaccional.negocio;

import javax.ejb.Remote;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.TransferenciasExternas;

@Remote
public interface GestionTransferenciasExternasOnRemoto {

	public boolean registrarTransferenciasExternas(TransferenciasExternas transExternas) throws Exception;

	public boolean actualizarTransferenciasExternas(TransferenciasExternas transExternas) throws Exception;

	public TransferenciasExternas buscarTransferenciasExternas(int id) throws Exception;

	public boolean eliminarTransferenciasExternas(int id) throws Exception;

}

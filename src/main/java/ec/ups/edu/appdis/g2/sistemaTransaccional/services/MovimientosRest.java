package ec.ups.edu.appdis.g2.sistemaTransaccional.services;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Cuenta;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Movimientos;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Persona;
import ec.ups.edu.appdis.g2.sistemaTransaccional.modelo.Usuario;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionCuentaON;
import ec.ups.edu.appdis.g2.sistemaTransaccional.negocio.GestionMovimientosON;

@Path("/movimientos")
public class MovimientosRest {

	@Inject
	private GestionMovimientosON movimientosON;
	
	@Inject
	private GestionCuentaON cuentaON;
	
	@POST
	@Path("/realizarMovimiento")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta realizarMovimiento(Movimientos m, @QueryParam("nomCuentas") String numeroCuenta) {
		System.out.println("Entra a realizar Movimiento");
		Respuesta r = new Respuesta();
		Cuenta c = new Cuenta();
		Cuenta cSaliebte = new Cuenta();
		try {
			m.setTipo("TRANSFERENCIA");
			m.setMonto(m.getMonto());
			m.setCuentaSale(m.getCuentaSale());
			m.setFecha(new Date());
			c = cuentaON.buscarCuenta(numeroCuenta);
			m.setCuenta(c);
			movimientosON.registrarMoviemientos(m);
			cSaliebte = cuentaON.buscarCuenta(m.getCuentaSale());
			c.setSaldo(c.getSaldo()-m.getMonto());
			cSaliebte.setSaldo(c.getSaldo() + m.getMonto());
			cuentaON.actualizarCuenta(c);
			cuentaON.actualizarCuenta(cSaliebte);
			System.out.println("movimiento realizado");
			 r.setCodigo(1);
			 r.setMensaje("Movimiento realizado");
			 return r;
		} catch (Exception e) {
			e.printStackTrace();
			 r.setCodigo(1);
			 r.setMensaje("Error al realizar movimiento");
			System.out.println("Error al realizar movimiento");
			return r;
		}
	}
}

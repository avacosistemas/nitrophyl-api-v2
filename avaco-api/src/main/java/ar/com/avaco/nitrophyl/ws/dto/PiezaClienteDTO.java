package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOAuditableEntity;

public class PiezaClienteDTO extends DTOAuditableEntity<Long> {

	private Long id;

	private Long idCliente;

	private Long idPieza;

	private String nombreCliente;

	private String nombrePiezaPersonalizado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Long idPieza) {
		this.idPieza = idPieza;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombrePiezaPersonalizado() {
		return nombrePiezaPersonalizado;
	}

	public void setNombrePiezaPersonalizado(String nombrePiezaPersonalizado) {
		this.nombrePiezaPersonalizado = nombrePiezaPersonalizado;
	}

}

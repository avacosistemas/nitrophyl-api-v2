package ar.com.avaco.nitrophyl.ws.dto;

public class CotizacionFilterDTO extends SortPageDTO {

	private Long idCliente;

	private Long idPieza;

	private Boolean soloVigentes;

	public Boolean getSoloVigentes() {
		return soloVigentes;
	}

	public void setSoloVigentes(Boolean soloVigentes) {
		this.soloVigentes = soloVigentes;
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

}

package ar.com.avaco.nitrophyl.ws.dto;

public class ClienteFilterDTO extends SortPageDTO {

	private String busquedaRapida;

	private Boolean activo;

	public String getBusquedaRapida() {
		return busquedaRapida;
	}

	public void setBusquedaRapida(String busquedaRapida) {
		this.busquedaRapida = busquedaRapida;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}

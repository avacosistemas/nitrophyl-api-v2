package ar.com.avaco.nitrophyl.domain.entities.pieza;

import javax.persistence.Embeddable;

@Embeddable
public class Terminacion {

	private String refilado;

	private String identificacion;

	private String embalaje;

	private byte[] imagenTerminada;

	public String getRefilado() {
		return refilado;
	}

	public void setRefilado(String refilado) {
		this.refilado = refilado;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getEmbalaje() {
		return embalaje;
	}

	public void setEmbalaje(String embalaje) {
		this.embalaje = embalaje;
	}

	public byte[] getImagenTerminada() {
		return imagenTerminada;
	}

	public void setImagenTerminada(byte[] imagenTerminada) {
		this.imagenTerminada = imagenTerminada;
	}

}

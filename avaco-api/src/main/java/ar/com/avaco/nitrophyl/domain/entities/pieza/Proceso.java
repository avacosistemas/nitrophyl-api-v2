package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

public class Proceso {

	private Pieza pieza;

	private Moldeo moldeo;

	private String desmoldante;

	private String postCura;

	private String refilado;

	private String identificacion;

	private String embalaje;

	private byte[] imagenTerminada;

	private List<Esquema> esquema;

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public Moldeo getMoldeo() {
		return moldeo;
	}

	public void setMoldeo(Moldeo moldeo) {
		this.moldeo = moldeo;
	}

	public String getDesmoldante() {
		return desmoldante;
	}

	public void setDesmoldante(String desmoldante) {
		this.desmoldante = desmoldante;
	}

	public String getPostCura() {
		return postCura;
	}

	public void setPostCura(String postCura) {
		this.postCura = postCura;
	}

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

	public List<Esquema> getEsquema() {
		return esquema;
	}

	public void setEsquema(List<Esquema> esquema) {
		this.esquema = esquema;
	}
}

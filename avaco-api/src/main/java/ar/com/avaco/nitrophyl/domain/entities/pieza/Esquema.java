package ar.com.avaco.nitrophyl.domain.entities.pieza;

import java.util.List;

public class Esquema {

	private String titulo;
	private byte[] imagen;
	private List<PasoEsquema> pasos;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public List<PasoEsquema> getPasos() {
		return pasos;
	}

	public void setPasos(List<PasoEsquema> pasos) {
		this.pasos = pasos;
	}
}

package ar.com.avaco.nitrophyl.ws.dto;

public class ArchivoDTO {

	private byte[] archivo;

	private String nombre;

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

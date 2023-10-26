package ar.com.avaco.nitrophyl.ws.dto;

public class ConfiguracionPruebaParametroDTO {

	private Long id;
	private String nombre;
	private Double minimo;
	private Double maximo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}

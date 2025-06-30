package ar.com.avaco.nitrophyl.ws.dto;

public class PiezaFilterDTO extends SortPageDTO {

	private String nombre;

	private Long idFormula;

	private Long idMaterial;

	private Boolean soloVigentes;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public Long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}

	public Boolean getSoloVigentes() {
		return soloVigentes;
	}

	public void setSoloVigentes(Boolean soloVigentes) {
		this.soloVigentes = soloVigentes;
	}

}

package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeFoto;
import ar.com.avaco.utils.DateUtils;

public class MoldeFotoListadoDTO {

	private Long id;
	private Long idMolde;
	private String nombreArchivo;
	private Integer version;
	private String fecha;
	private String descripcion;

	public MoldeFotoListadoDTO() {
	}

	public MoldeFotoListadoDTO(MoldeFoto mf) {
		super();
		this.id = mf.getId();
		this.idMolde = mf.getIdMolde();
		this.nombreArchivo = mf.getNombreArchivo();
		this.version = mf.getVersion();
		this.fecha = DateUtils.toString(mf.getFecha(), DateUtils.PATTERN_FULL_24_HS);
		this.descripcion = mf.getDescripcion();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

package ar.com.avaco.nitrophyl.ws.dto;

import java.util.List;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PageDTO<DTO extends DTOEntity<?>> {

	private List<DTO> page;

	private long totalReg;

	public List<DTO> getPage() {
		return page;
	}

	public void setPage(List<DTO> page) {
		this.page = page;
	}

	public long getTotalReg() {
		return totalReg;
	}

	public void setTotalReg(long totalReg) {
		this.totalReg = totalReg;
	}

}

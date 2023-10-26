package ar.com.avaco.nitrophyl.ws.service.filter;

import ar.com.avaco.arc.core.domain.filter.AbstractFilter;
import ar.com.avaco.nitrophyl.ws.dto.SortPageDTO;

public class NitroAbstractFilter extends AbstractFilter {

	public NitroAbstractFilter() {
	}
	
	public NitroAbstractFilter(SortPageDTO dto) {
		super.setAsc(dto.getAsc());
		super.setFirst(dto.getFirst());
		super.setIdx(dto.getIdx());
		super.setRows(dto.getRows());
	}
	
}

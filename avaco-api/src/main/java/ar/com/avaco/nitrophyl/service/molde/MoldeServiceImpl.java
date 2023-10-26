package ar.com.avaco.nitrophyl.service.molde;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.repository.molde.MoldeRepository;

@Service("moldeService")
public class MoldeServiceImpl extends NJBaseService<Long, Molde, MoldeRepository> implements MoldeService {

	@Resource(name = "moldeRepository")
	void setMoldeRepository(MoldeRepository moldeRepository) {
		this.repository = moldeRepository;
	}

}

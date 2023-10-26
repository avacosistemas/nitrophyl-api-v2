package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeRegistro;
import ar.com.avaco.nitrophyl.domain.entities.moldes.TipoRegistroMolde;
import ar.com.avaco.nitrophyl.repository.molde.MoldeRegistroRepository;
import ar.com.avaco.utils.DateUtils;

@Transactional
@Service("moldeRegistroService")
public class MoldeRegistroServiceImpl extends NJBaseService<Long, MoldeRegistro, MoldeRegistroRepository>
		implements MoldeRegistroService {

	private Logger logger = Logger.getLogger(getClass());		

	@Resource(name = "moldeRegistroRepository")
	void setClienteRepository(MoldeRegistroRepository moldeRegistroRepository) {
		this.repository = moldeRegistroRepository;
	}

	@Override
	public List<MoldeRegistro> listByMoldeId(Long idMolde) {
		return this.repository.findAllByIdMoldeOrderByFechaDesc(idMolde);
	}

	@Override
	public MoldeRegistro getUltimoRegistro(Long idMolde) {
		return this.repository.findFirstByIdMoldeOrderByFechaDesc(idMolde);
	}

	@Override
	public MoldeRegistro registrarIngreso(String comentarios, Long idMolde) {
		MoldeRegistro mr = new MoldeRegistro();
		mr.setComentarios(comentarios);
		mr.setFecha(DateUtils.getFechaYHoraActual());
		mr.setIdMolde(idMolde);
		mr.setTipoRegistro(TipoRegistroMolde.INGRESO);
		return this.repository.save(mr);
	}

	@Override
	public MoldeRegistro registrarEgreso(String comentarios, Long idMolde) {
		MoldeRegistro mr = new MoldeRegistro();
		mr.setComentarios(comentarios);
		mr.setFecha(DateUtils.getFechaYHoraActual());
		mr.setIdMolde(idMolde);
		mr.setTipoRegistro(TipoRegistroMolde.EGRESO);
		return this.repository.save(mr);
	}


}

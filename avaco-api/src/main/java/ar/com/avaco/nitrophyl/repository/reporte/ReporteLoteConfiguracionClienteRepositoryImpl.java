package ar.com.avaco.nitrophyl.repository.reporte;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.domain.entities.reporte.ReporteLoteConfiguracionCliente;

@Repository("reporteLoteConfiguracionClienteRepository")
public class ReporteLoteConfiguracionClienteRepositoryImpl extends NJBaseRepository<Long, ReporteLoteConfiguracionCliente>
		implements ReporteLoteConfiguracionClienteRepositoryCustom {

	public ReporteLoteConfiguracionClienteRepositoryImpl(EntityManager entityManager) {
		super(ReporteLoteConfiguracionCliente.class, entityManager);
	}

	@Override
	public List<ReporteLoteConfiguracionCliente> findConfiguracionesByClienteFormula(Formula formula, Cliente cliente) {
		Criteria criteria = getCurrentSession().createCriteria(ReporteLoteConfiguracionCliente.class);
		criteria.add(Restrictions.eq("formula", formula));
		criteria.add(Restrictions.eqOrIsNull("cliente", cliente));
		return criteria.list();
	}

	
	
}

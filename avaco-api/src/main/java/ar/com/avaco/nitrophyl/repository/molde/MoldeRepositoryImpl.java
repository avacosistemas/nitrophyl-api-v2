package ar.com.avaco.nitrophyl.repository.molde;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;

@Repository("moldeRepository")
public class MoldeRepositoryImpl extends NJBaseRepository<Long, Molde> implements MoldeRepositoryCustom {

	public MoldeRepositoryImpl(EntityManager entityManager) {
		super(Molde.class, entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MoldeListadoDTO> listGrilla(MoldeFilterDTO filtro) {
		String query = "select * from ( SELECT cast(m.id_molde as integer) as id, m.codigo, m.estado, m.nombre, m.ubicacion, "
				+ "cast(SUM(CASE WHEN md.tipodimension = 'ALTO' THEN md.valordimension ELSE NULL END) as integer) AS ALTO, "
				+ "cast(SUM(CASE WHEN md.tipodimension = 'ANCHO' THEN md.valordimension ELSE NULL END) as integer) AS ANCHO, "
				+ "cast(SUM(CASE WHEN md.tipodimension = 'DIAMETRO' THEN md.valordimension ELSE NULL END) as integer)AS DIAMETRO, "
				+ "cast(SUM(CASE WHEN md.tipodimension = 'PROFUNDIDAD' THEN md.valordimension ELSE NULL END) as integer) AS PROFUNDIDAD "
				+ "FROM moldes m left join moldedimension md on m.id_molde = md.id_molde "
				+ "GROUP BY m.id_molde, m.codigo, m.estado, m.ubicacion) molde where 1 = 1 ";

		if (filtro.getAltomin() != null)
			query += "and molde.alto >= " + filtro.getAltomin();
		if (filtro.getAltomax() != null)
			query += "and molde.alto <= " + filtro.getAltomax();

		if (filtro.getAnchomin() != null)
			query += "and molde.ancho >= " + filtro.getAnchomin();
		if (filtro.getAnchomax() != null)
			query += "and molde.ancho <= " + filtro.getAnchomax();

		if (filtro.getProfumin() != null)
			query += "and molde.profundidad >= " + filtro.getProfumin();
		if (filtro.getProfumax() != null)
			query += "and molde.profundidad <= " + filtro.getProfumax();

		if (filtro.getDiametromin() != null)
			query += "and molde.diametro >= " + filtro.getDiametromin();
		if (filtro.getDiametromax() != null)
			query += "and molde.diametro <= " + filtro.getDiametromax();

		if (StringUtils.isNotEmpty(filtro.getCodigo()))
			query += "and molde.codigo like '%" + filtro.getCodigo() + "%' ";

		if (StringUtils.isNotEmpty(filtro.getEstado()))
			query += "and molde.estado = '" + filtro.getEstado() + "' ";

		if (StringUtils.isNotEmpty(filtro.getNombre()))
			query += "and molde.nombre = '" + filtro.getNombre() + "' ";

		if (StringUtils.isNotEmpty(filtro.getIdx()))
			query += "order by molde." + filtro.getIdx();

		query += "limit " + filtro.getRows();
		query += "offset " + (filtro.getFirst() - 1);

		SQLQuery createSQLQuery = getCurrentSession().createSQLQuery(query)
				.setResultSetMapping("MoldeListadoDTOMapper");

		List<MoldeListadoDTO> list = createSQLQuery.list();

		return list;
	}

}

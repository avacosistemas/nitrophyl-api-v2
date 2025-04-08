package ar.com.avaco.nitrophyl.repository.lote;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnsayoLotePorMaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaFilterDTO;
import ar.com.avaco.utils.DateUtils;

@Repository("loteRepository")
public class LoteRepositoryImpl extends NJBaseRepository<Long, Lote> implements LoteRepositoryCustom {

	public LoteRepositoryImpl(EntityManager entityManager) {
		super(Lote.class, entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<RegistroEnsayoLotePorMaquinaDTO> getEnsayosLotePorMaquina(ReporteEnsayoLotePorMaquinaFilterDTO filtro) {

		String lotesquery = "select distinct(l.id_lote) from ensayo_resultado er "
				+ " left join ensayo e on e.id_ensayo = er.id_ensayo left join conf_prueba cp on "
				+ "	cp.id_conf_prueba = e.id_conf_prueba left join conf_prueba_param cpp on "
				+ "	cpp.id_conf_prueba_param = er.id_conf_prueba_param left join lote l on "
				+ "	l.id_lote = e.id_lote where cp.id_maquina = :idMaquina ";
				if (StringUtils.isNotBlank(filtro.getEstadoEnsayo())) {
					lotesquery+= " and e.estado = '" + filtro.getEstadoEnsayo() + "' ";
				}
				lotesquery += " group by l.id_lote ";

		String lotesFiltradosQuery = " select cast(lo.id_lote as integer) as id_lote, cast(COUNT(*) OVER () as integer) AS total_registros from lote lo left join formula f on "
				+ " f.id_formula = lo.id_formula where 1 = 1 ";

		if (filtro.getFechaDesde() != null) {
			lotesFiltradosQuery += " and lo.fecha >= '" + DateUtils.toString(filtro.getFechaDesde(), DateUtils.yyyyMMdd)
					+ "'";
		}

		if (filtro.getFechaHasta() != null) {
			lotesFiltradosQuery += " and lo.fecha <= '" + DateUtils.toString(filtro.getFechaHasta(), DateUtils.yyyyMMdd)
					+ "'";
		}

		if (StringUtils.isNotBlank(filtro.getNroLote())) {
			lotesFiltradosQuery += " and lo.nro_lote like '%" + filtro.getNroLote() + "%' ";
		}

		if (filtro.getIdFormula() != null) {
			lotesFiltradosQuery += " and lo.id_formula = " + filtro.getIdFormula();
		}

		lotesFiltradosQuery += " and lo.id_lote in (" + lotesquery + ")";

		if (StringUtils.isNotBlank(filtro.getIdx())) {
			lotesFiltradosQuery += " order by " + filtro.getIdx();
			if (filtro.getAsc() != null && filtro.getAsc().booleanValue()) {
				lotesFiltradosQuery += " asc ";
			} else {
				lotesFiltradosQuery += " desc ";
			}
		} else {
			lotesFiltradosQuery += " order by lo.fecha desc ";
		}

		lotesFiltradosQuery += " limit " + filtro.getRows();

		lotesFiltradosQuery += " offset " + (filtro.getFirst() - 1);

		SQLQuery createSQLQueryLotes = getCurrentSession().createSQLQuery(lotesFiltradosQuery);
		createSQLQueryLotes.setLong("idMaquina", filtro.getIdMaquina());
		createSQLQueryLotes.addScalar("id_lote", StandardBasicTypes.INTEGER);
		createSQLQueryLotes.addScalar("total_registros", StandardBasicTypes.INTEGER);
		
		List<Object[]> idsCantidad = createSQLQueryLotes.list();

		Integer rows = (Integer) idsCantidad.get(0)[1];
		
		List<RegistroEnsayoLotePorMaquinaDTO> list = new ArrayList<RegistroEnsayoLotePorMaquinaDTO>();

		List<Integer> ids = idsCantidad.stream()
			    .map(fila -> (Integer) fila[0])
			    .collect(Collectors.toList());
		
		if (ids != null && !ids.isEmpty()) {

			String query = "select 	CAST(row_number() over() as integer) as row"
					+ " , CAST(:rows as integer) AS rows, CAST(lo.id_lote as Integer) as idLote "
					+ " , lo.nro_lote as nroLote, lo.fecha as fecha, lo.observaciones as observaciones "
					+ " , f.id_formula as idFormula, f.nombre as nombreFormula "
					+ " , cpp.id_maquina_prueba as idMaquinaPrueba, er.redondeo, er.resultado "
					+ " , e.estado as estadoEnsayo from ensayo_resultado er "
					+ " left join ensayo e on e.id_ensayo = er.id_ensayo "
					+ " left join conf_prueba cp on cp.id_conf_prueba = e.id_conf_prueba "
					+ " left join conf_prueba_param cpp ON cpp.id_conf_prueba_param = er.id_conf_prueba_param "
					+ " left join lote lo on lo.id_lote = e.id_lote "
					+ " left join formula_rev_param frp on frp.id_rev_param = lo.id_revision_parametros "
					+ " left join formula f on f.id_formula = lo.id_formula where lo.id_lote in (:lotesIds) "
					+ " and cpp.id_maquina_prueba in (select mp.id_maquina_prueba from maquina_prueba mp where mp.id_maquina = :idMaquina)";

			SQLQuery createSQLQuery = getCurrentSession().createSQLQuery(query)
					.setResultSetMapping("RegistroEnsayoLotePorMaquinaDTOMapper");

			createSQLQuery.setParameterList("lotesIds", ids);
			createSQLQuery.setLong("idMaquina", filtro.getIdMaquina());
			createSQLQuery.setString("rows", rows.toString());

			list = createSQLQuery.list();

		}

		return list;
	}

}

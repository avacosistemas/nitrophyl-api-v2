package ar.com.avaco.nitrophyl.repository.lote;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.lote.Lote;
import ar.com.avaco.nitrophyl.ws.dto.RegistroEnsayoLotePorMaquinaDTO;
import ar.com.avaco.nitrophyl.ws.dto.ReporteEnsayoLotePorMaquinaFilterDTO;
import ar.com.avaco.nitrophyl.ws.service.filter.LoteFilter;
import ar.com.avaco.utils.DateUtils;

@Repository("loteRepository")
public class LoteRepositoryImpl extends NJBaseRepository<Long, Lote> implements LoteRepositoryCustom {

	public LoteRepositoryImpl(EntityManager entityManager) {
		super(Lote.class, entityManager);
	}

	@Override
	public List<RegistroEnsayoLotePorMaquinaDTO> getEnsayosLotePorMaquina(ReporteEnsayoLotePorMaquinaFilterDTO filtro) {

		String lotesquery = "select distinct(l.id_lote) from ensayo_resultado er "
				+ " left join ensayo e on e.id_ensayo = er.id_ensayo left join conf_prueba cp on "
				+ "	cp.id_conf_prueba = e.id_conf_prueba left join conf_prueba_param cpp on "
				+ "	cpp.id_conf_prueba_param = er.id_conf_prueba_param left join lote l on "
				+ "	l.id_lote = e.id_lote where cp.id_maquina = :idMaquina group by l.id_lote ";

		String lotesFiltradosQuery = " select lo.id_lote from lote lo left join formula f on "
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

		if (StringUtils.isNotBlank(filtro.getEstadoLote())) {
			lotesFiltradosQuery += " and lo.estado = '" + filtro.getEstadoLote() + "' ";
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

		List<Long> ids = createSQLQueryLotes.list();

		LoteFilter lf = new LoteFilter();

		if (filtro.getFechaDesde() != null) {
			lf.setFechaDesde(filtro.getFechaDesde());
		}

		if (filtro.getFechaHasta() != null) {
			lf.setFechaHasta(filtro.getFechaHasta());
		}

		if (StringUtils.isNotBlank(filtro.getNroLote())) {
			lf.setNroLote(filtro.getNroLote());
		}

		if (StringUtils.isNotBlank(filtro.getEstadoLote())) {
			lf.setEstado(filtro.getEstadoLote());
		}

		if (filtro.getIdFormula() != null) {
			lf.setIdFormula(filtro.getIdFormula());
		}

		lf.setRows(filtro.getRows());
		lf.setFirst(filtro.getFirst());

		Integer listCount = this.listCount(lf);

		String query = "select 	CAST(row_number() over() as integer) as row, CAST(:rows as integer) AS rows, CAST(lo.id_lote as Integer) as idLote "
				+ " , lo.nro_lote as nroLote " + " , lo.fecha as fecha " + " , lo.observaciones as observaciones "
				+ " , f.id_formula as idFormula " + " , f.nombre as nombreFormula "
				+ " , cpp.id_maquina_prueba as idMaquinaPrueba " + " , er.redondeo" + " , er.resultado " + " , case "
				+ "		when er.redondeo <> er.resultado then 'APROBADO_OBSERVADO'"
				+ "		when cpp.valor_minimo is null and cpp.valor_maximo is null	then 'APROBADO'"
				+ "		when cpp.valor_minimo is not null and cpp.valor_maximo is not null and cpp.valor_minimo <= er.redondeo and valor_maximo >= er.redondeo then 'APROBADO'"
				+ "		when cpp.valor_minimo is null and cpp.valor_maximo is not null and cpp.valor_maximo >= er.redondeo then 'APROBADO'"
				+ "		when cpp.valor_minimo is not null and cpp.valor_maximo is null and cpp.valor_minimo <= er.redondeo then 'APROBADO' else 'RECHAZADO'"
				+ "	end estadoEnsayo, lo.estado as estadoLote from ensayo_resultado er "
				+ " left join ensayo e on e.id_ensayo = er.id_ensayo "
				+ " left join conf_prueba cp on cp.id_conf_prueba = e.id_conf_prueba "
				+ " left join conf_prueba_param cpp ON cpp.id_conf_prueba_param = er.id_conf_prueba_param "
				+ " left join lote lo on lo.id_lote = e.id_lote "
				+ " left join formula_rev_param frp on frp.id_rev_param = lo.id_revision_parametros "
				+ " left join formula f on f.id_formula = lo.id_formula " + " where lo.id_lote in (:lotesIds) ";

//		if (StringUtils.isNotBlank(filtro.getIdx())) {
//			query += " order by " + filtro.getIdx();
//			if (filtro.getAsc() != null && filtro.getAsc().booleanValue()) {
//				query += " asc ";
//			} else {
//				query += " desc ";
//			}
//		} else {
//			query += " order by lo.fecha desc ";
//		}

		SQLQuery createSQLQuery = getCurrentSession().createSQLQuery(query)
				.setResultSetMapping("RegistroEnsayoLotePorMaquinaDTOMapper");

		createSQLQuery.setParameterList("lotesIds", ids);
		createSQLQuery.setString("rows", listCount.toString());

		@SuppressWarnings("unchecked")
		List<RegistroEnsayoLotePorMaquinaDTO> list = createSQLQuery.list();

		return list;
	}

}

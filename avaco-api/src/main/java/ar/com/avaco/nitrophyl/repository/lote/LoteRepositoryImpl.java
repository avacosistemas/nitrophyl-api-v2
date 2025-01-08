package ar.com.avaco.nitrophyl.repository.lote;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
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
	public List<RegistroEnsayoLotePorMaquinaDTO> getEnsayosLotePorMaquina(ReporteEnsayoLotePorMaquinaFilterDTO filtro) {

		String query = "select 	CAST(row_number() over() as integer) as row, CAST(count(*) OVER() as integer) AS rows, CAST(l.id_lote as Integer) as idLote "
				+ " , l.nro_lote as nroLote " + " , l.fecha as fecha " + " , l.observaciones as observaciones "
				+ " , f.id_formula as idFormula " + " , f.nombre as nombreFormula "
				+ " , cpp.id_maquina_prueba as idMaquinaPrueba " + " , er.redondeo" + " , er.resultado " + " , case "
				+ "		when er.redondeo <> er.resultado then 'APROBADO_OBSERVADO'"
				+ "		when cpp.valor_minimo is null and cpp.valor_maximo is null	then 'APROBADO'"
				+ "		when cpp.valor_minimo is not null and cpp.valor_maximo is not null and cpp.valor_minimo <= er.redondeo and valor_maximo >= er.redondeo then 'APROBADO'"
				+ "		when cpp.valor_minimo is null and cpp.valor_maximo is not null and cpp.valor_maximo >= er.redondeo then 'APROBADO'"
				+ "		when cpp.valor_minimo is not null and cpp.valor_maximo is null and cpp.valor_minimo <= er.redondeo then 'APROBADO' else 'RECHAZADO'"
				+ "	end estadoEnsayo, l.estado as estadoLote from ensayo_resultado er "
				+ " left join ensayo e on e.id_ensayo = er.id_ensayo "
				+ " left join conf_prueba cp on cp.id_conf_prueba = e.id_conf_prueba "
				+ " left join conf_prueba_param cpp ON cpp.id_conf_prueba_param = er.id_conf_prueba_param "
				+ " left join lote l on l.id_lote = e.id_lote "
				+ " left join formula_rev_param frp on frp.id_rev_param = l.id_revision_parametros "
				+ " left join formula f on f.id_formula = l.id_formula " + " where cp.id_maquina = {idMaquina} ";

		if (StringUtils.isNotBlank(filtro.getFechaDesde())) {
			String desde = DateUtils.toString(DateUtils.toDate(filtro.getFechaDesde(), DateUtils.dd_MM_yyyy),
					DateUtils.yyyyMMdd);
			query += " and l.fecha >= '" + desde + "'";
		}

		if (StringUtils.isNotBlank(filtro.getFechaHasta())) {
			String hasta = DateUtils.toString(DateUtils.toDate(filtro.getFechaHasta(), DateUtils.dd_MM_yyyy),
					DateUtils.yyyyMMdd);
			query += " and l.fecha <= '" + hasta + "'";
		}

		if (StringUtils.isNotBlank(filtro.getNroLote())) {
			query += " and l.nro_lote like '%" + filtro.getNroLote() + "%' ";
		}
		
		if (StringUtils.isNotBlank(filtro.getEstadoLote())) {
			query += " and l.estado = '" + filtro.getEstadoLote() +"' ";
		}

		if (filtro.getIdFormula() != null) {
			query += " and l.id_formula = " + filtro.getIdFormula();
		}

		if (StringUtils.isNotBlank(filtro.getIdx())) {
			query += " order by " + filtro.getIdx();
			if (filtro.getAsc() != null && filtro.getAsc().booleanValue()) {
				query += " asc ";
			} else {
				query += " desc ";
			}
		} else {
			query += " order by l.fecha desc ";
		}

		query += " limit " + filtro.getRows();

		query += " offset " + ((filtro.getFirst() * filtro.getRows()) - filtro.getRows());

		query = query.replace("{idMaquina}", filtro.getIdMaquina().toString());

		List list = getCurrentSession().createSQLQuery(query)
				.setResultSetMapping("RegistroEnsayoLotePorMaquinaDTOMapper").list();

		return list;
	}

}

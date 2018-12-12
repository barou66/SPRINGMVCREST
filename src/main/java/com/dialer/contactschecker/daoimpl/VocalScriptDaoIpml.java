package com.dialer.contactschecker.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.dialer.contactschecker.dao.VocalScriptDao;
import com.dialer.contactschecker.model.VocalScript;
import com.dialer.contactschecker.util.CampaignUtils;

@Repository
public class VocalScriptDaoIpml implements VocalScriptDao {

	private static Logger logger = LoggerFactory.getLogger(VocalScriptDaoIpml.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public VocalScriptDaoIpml(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public VocalScript add(VocalScript vocalScript) {
		try {
			String id = UUID.randomUUID().toString();
			// Check if Dial plan number is already used and find new one
			String sql = "select count(*) from mvocalscripts_vsc where VSC_DIALPLANNUMBER="
					+ vocalScript.getVscDialPlanNumber();
			int planNumber = 0;
			while (jdbcTemplate.queryForObject(sql.toString(), Integer.class) != 0) {
				planNumber = (int) Math.floor((Math.random() * 1999) + 1000);
				sql = "select count(*) from mvocalscripts_vsc where VSC_DIALPLANNUMBER=" + planNumber;
			}
			sql = "insert into mvocalscripts_vsc(VSC_ID,VSC_DIALPLANNUMBER,VSC_NAME,VSC_TYPE,VSC_SCRIPT) values(?,?,?,?,?)";
			int result = jdbcTemplate.update(sql,
					new Object[] { id, (0 != planNumber) ? planNumber : vocalScript.getVscDialPlanNumber(),
							vocalScript.getVscName(), vocalScript.getVscType(), vocalScript.getVscScript() });

			if (result > 0) {
				jdbcTemplate.execute("commit");
				return vocalScript;
			} else {
				jdbcTemplate.execute("rollback");
				return null;
			}
		} catch (Exception e) {
			jdbcTemplate.execute("rollback");
			logger.error("JDBC Error:", e);
			return null;
		}
	}

	@Override
	public VocalScript update(VocalScript vocalScript) {
		try {
			String sql = "update mvocalscripts_vsc set VSC_DIALPLANNUMBER=?,VSC_NAME=?,VSC_TYPE=?,VSC_SCRIPT=? where VSC_ID=?";
			int result = jdbcTemplate.update(sql,
					new Object[] { vocalScript.getVscDialPlanNumber(), vocalScript.getVscName(),
							vocalScript.getVscType(), vocalScript.getVscScript(), vocalScript.getVscId() });

			if (result > 0) {
				jdbcTemplate.execute("commit");
				return vocalScript;
			} else {
				jdbcTemplate.execute("rollback");
				return vocalScript;
			}
		} catch (Exception e) {
			jdbcTemplate.execute("rollback");
			logger.error("JDBC Error:", e);
			return null;
		}
	}

	@Override
	public VocalScript delete(VocalScript vocalScript, String recordingpath) {
		String sql;
		String tablename = "";
		int result;

		try {
			sql = "select CMP_TABLE_NAME from mcampaigns_cmp where CMP_VSCID='" + vocalScript.getVscId() + "'";
			SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
			while (srs.next()) {
				tablename = srs.getString("CMP_TABLE_NAME");
				sql = "drop table " + tablename;
				jdbcTemplate.execute(sql);
			}
		} catch (Exception e) {
			logger.warn("Table '" + tablename + "' to drop can't be found");
		}

		try {
			sql = "delete from mdistribution_cmp_pvd where ID_CMP in (select CMP_ID from mcampaigns_cmp where CMP_VSCID=?)";
			result = jdbcTemplate.update(sql, new Object[] { vocalScript.getVscId() });
			if (result >= 0) {
				sql = "delete from mcampaigns_cmp where CMP_VSCID=?";
				result = jdbcTemplate.update(sql, new Object[] { vocalScript.getVscId() });
				if (result >= 0) {
					sql = "delete from mvocalscripts_vsc where VSC_ID=?";
					result = jdbcTemplate.update(sql, new Object[] { vocalScript.getVscId() });

					if (result > 0 && CampaignUtils.deleteFolder(recordingpath)) {
						jdbcTemplate.execute("commit");
						return vocalScript;
					} else {
						jdbcTemplate.execute("rollback");
						return null;
					}
				} else {
					jdbcTemplate.execute("rollback");
					return null;
				}
			} else {
				jdbcTemplate.execute("rollback");
				return null;
			}
		} catch (Exception e) {
			jdbcTemplate.execute("rollback");
			logger.error("JDBC Error:", e);
			return null;
		}
	}

	@Override
	public List<VocalScript> getAll() {
		String sql = "select VSC_ID,VSC_DIALPLANNUMBER,VSC_NAME,VSC_TYPE,VSC_SCRIPT from mvocalscripts_vsc order by VSC_NAME";

		SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
		List<VocalScript> vocalscriptList = new ArrayList<VocalScript>();
		while (srs.next()) {
			VocalScript vocalscript = new VocalScript();
			vocalscript.setVscId(srs.getString("VSC_ID"));
			vocalscript.setVscDialPlanNumber(srs.getInt("VSC_DIALPLANNUMBER"));
			vocalscript.setVscName(srs.getString("VSC_NAME"));
			vocalscript.setVscType(srs.getInt("VSC_TYPE"));
			vocalscript.setVscScript(srs.getString("VSC_SCRIPT"));
			vocalscriptList.add(vocalscript);
		}
		return vocalscriptList;
	}

	@Override
	public VocalScript getById(String vocalScriptId) {
		String sql = "select VSC_ID,VSC_DIALPLANNUMBER,VSC_NAME,VSC_TYPE,VSC_SCRIPT from mvocalscripts_vsc where VSC_ID='"
				+ vocalScriptId + "'";

		SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
		VocalScript vocalscript = new VocalScript();
		while (srs.next()) {
			vocalscript.setVscId(srs.getString("VSC_ID"));
			vocalscript.setVscDialPlanNumber(srs.getInt("VSC_DIALPLANNUMBER"));
			vocalscript.setVscName(srs.getString("VSC_NAME"));
			vocalscript.setVscType(srs.getInt("VSC_TYPE"));
			vocalscript.setVscScript(srs.getString("VSC_SCRIPT"));
		}
		return vocalscript;
	}

}

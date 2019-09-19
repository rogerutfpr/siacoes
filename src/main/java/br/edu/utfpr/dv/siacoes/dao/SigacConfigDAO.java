package br.edu.utfpr.dv.siacoes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.siacoes.model.SigacConfig;

public class SigacConfigDAO extends TemplateDAO<SigacConfig> {

	@Override
	protected String getStringSqlFindDepartment() {
		return "SELECT * FROM sigacconfig WHERE idDepartment = ?";
	}

	@Override
	protected String getStringSqlSave() {
		return "INSERT INTO sigacconfig(minimumScore, maxfilesize, idDepartment) VALUES(?, ?, ?)";
	}

	@Override
	protected String getStringSqlUpdate() {
		return "UPDATE sigacconfig SET minimumScore=?, maxfilesize=? WHERE idDepartment=?";
	}

	@Override
	protected void ormSave(PreparedStatement statement, SigacConfig config) throws SQLException {
		statement.setDouble(1, config.getMinimumScore());
		statement.setInt(2, config.getMaxFileSize());
		statement.setInt(3, config.getDepartment().getIdDepartment());
	}

	@Override
	protected void setSigConfig(ResultSet rs, SigacConfig sigConfig) throws SQLException {
		sigConfig.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		sigConfig.setMinimumScore(rs.getDouble("minimumScore"));
		sigConfig.setMaxFileSize(rs.getInt("maxfilesize"));
	}

}

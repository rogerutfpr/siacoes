package br.edu.utfpr.dv.siacoes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.siacoes.model.SigesConfig;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.SupervisorFilter;

public class SigesConfigDAO extends TemplateDAO<SigesConfig>{

	@Override
	protected String getStringSqlFindDepartment() {
		return "SELECT * FROM sigesconfig WHERE idDepartment = ?";
	}

	@Override
	protected void ormSave(PreparedStatement statement, SigesConfig config) throws SQLException {
		statement.setDouble(1, config.getMinimumScore());
		statement.setDouble(2, config.getSupervisorPonderosity());
		statement.setDouble(3, config.getCompanySupervisorPonderosity());
		statement.setInt(4, config.isShowGradesToStudent() ? 1 : 0);
		statement.setInt(5, config.getSupervisorFilter().getValue());
		statement.setInt(6, config.isSupervisorFillJuryForm() ? 1 : 0);
		statement.setInt(7, config.getMaxFileSize());
		statement.setInt(8, config.getJuryTime());
		statement.setInt(9, config.getDepartment().getIdDepartment());
	}

	@Override
	protected String getStringSqlSave() {
		return "INSERT INTO sigesconfig(minimumScore, supervisorPonderosity, companySupervisorPonderosity, showgradestostudent, supervisorfilter, supervisorFillJuryForm, maxfilesize, jurytime, idDepartment) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getStringSqlUpdate() {
		return "UPDATE sigesconfig SET minimumScore=?, supervisorPonderosity=?, companySupervisorPonderosity=?, showgradestostudent=?, supervisorfilter=?, supervisorFillJuryForm=?, maxfilesize=?, jurytime=? WHERE idDepartment=?";
	}

	@Override
	protected void setSigConfig(ResultSet rs, SigesConfig sigConfig) throws SQLException {
		sigConfig.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		sigConfig.setMinimumScore(rs.getDouble("minimumScore"));
		sigConfig.setSupervisorPonderosity(rs.getDouble("supervisorPonderosity"));
		sigConfig.setCompanySupervisorPonderosity(rs.getDouble("companySupervisorPonderosity"));
		sigConfig.setShowGradesToStudent(rs.getInt("showgradestostudent") == 1);
		sigConfig.setSupervisorFilter(SupervisorFilter.valueOf(rs.getInt("supervisorfilter")));
		sigConfig.setSupervisorFillJuryForm(rs.getInt("supervisorFillJuryForm") == 1);
		sigConfig.setMaxFileSize(rs.getInt("maxfilesize"));
		sigConfig.setJuryTime(rs.getInt("jurytime"));
	}
}

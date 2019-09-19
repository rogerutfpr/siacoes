package br.edu.utfpr.dv.siacoes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.siacoes.model.SigetConfig;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.AttendanceFrequency;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.SupervisorFilter;

public class SigetConfigDAO extends TemplateDAO<SigetConfig>{

	@Override
	protected String getStringSqlFindDepartment() {
		return "SELECT * FROM sigetconfig WHERE idDepartment = ?";
	}

	@Override
	protected void ormSave(PreparedStatement statement, SigetConfig config) throws SQLException {
		statement.setDouble(1, config.getMinimumScore());
		statement.setInt(2, (config.isRegisterProposal() ? 1 : 0));
		statement.setInt(3, (config.isShowGradesToStudent() ? 1 : 0));
		statement.setInt(4, config.getSupervisorFilter().getValue());
		statement.setInt(5, config.getCosupervisorFilter().getValue());
		statement.setInt(6, config.getSupervisorIndication());
		statement.setInt(7, config.getMaxTutoredStage1());
		statement.setInt(8, config.getMaxTutoredStage2());
		statement.setInt(9, (config.isRequestFinalDocumentStage1() ? 1 : 0));
		statement.setString(10, config.getRepositoryLink());
		statement.setInt(11, (config.isSupervisorJuryRequest() ? 1 : 0));
		statement.setInt(12, (config.isSupervisorAgreement() ? 1 : 0));
		statement.setInt(13, (config.isSupervisorJuryAgreement() ? 1 : 0));
		statement.setInt(14, (config.isValidateAttendances() ? 1 : 0));
		statement.setInt(15, config.getAttendanceFrequency().getValue());
		statement.setInt(16, config.getMaxFileSize());
		statement.setInt(17, config.getMinimumJuryMembers());
		statement.setInt(18, config.getMinimumJurySubstitutes());
		statement.setInt(19, config.getJuryTimeStage1());
		statement.setInt(20, config.getJuryTimeStage2());
		statement.setInt(21, (config.isSupervisorAssignsGrades() ? 1 : 0));
		statement.setInt(22, config.getDepartment().getIdDepartment());
	}

	@Override
	protected String getStringSqlSave() {
		return "INSERT INTO sigetconfig(minimumScore, registerProposal, showgradestostudent, supervisorfilter, cosupervisorfilter, supervisorIndication, maxTutoredStage1, maxTutoredStage2, requestFinalDocumentStage1, repositoryLink, supervisorJuryRequest, supervisorAgreement, supervisorJuryAgreement, validateAttendances, attendanceFrequency, maxfilesize, minimumJuryMembers, minimumJurySubstitutes, jurytimestage1, jurytimestage2, supervisorAssignsGrades, idDepartment) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getStringSqlUpdate() {
		return "UPDATE sigetconfig SET minimumScore=?, registerProposal=?, showgradestostudent=?, supervisorfilter=?, cosupervisorfilter=?, supervisorIndication=?, maxTutoredStage1=?, maxTutoredStage2=?, requestFinalDocumentStage1=?, repositoryLink=?, supervisorJuryRequest=?, supervisorAgreement=?, supervisorJuryAgreement=?, validateAttendances=?, attendanceFrequency=?, maxfilesize=?, minimumJuryMembers=?, minimumJurySubstitutes=?, jurytimestage1=?, jurytimestage2=?, supervisorAssignsGrades=? WHERE idDepartment=?";
	}

	@Override
	protected void setSigConfig(ResultSet rs, SigetConfig sigConfig) throws SQLException {
		sigConfig.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		sigConfig.setMinimumScore(rs.getDouble("minimumScore"));
		sigConfig.setRegisterProposal(rs.getInt("registerProposal") == 1);
		sigConfig.setShowGradesToStudent(rs.getInt("showgradestostudent") == 1);
		sigConfig.setSupervisorFilter(SupervisorFilter.valueOf(rs.getInt("supervisorFilter")));
		sigConfig.setCosupervisorFilter(SupervisorFilter.valueOf(rs.getInt("cosupervisorFilter")));
		sigConfig.setSupervisorIndication(rs.getInt("supervisorIndication"));
		sigConfig.setMaxTutoredStage1(rs.getInt("maxTutoredStage1"));
		sigConfig.setMaxTutoredStage2(rs.getInt("maxTutoredStage2"));
		sigConfig.setRequestFinalDocumentStage1(rs.getInt("requestFinalDocumentStage1") == 1);
		sigConfig.setRepositoryLink(rs.getString("repositoryLink"));
		sigConfig.setSupervisorJuryRequest(rs.getInt("supervisorJuryRequest") == 1);
		sigConfig.setSupervisorAgreement(rs.getInt("supervisorAgreement") == 1);
		sigConfig.setSupervisorJuryAgreement(rs.getInt("supervisorJuryAgreement") == 1);
		sigConfig.setValidateAttendances(rs.getInt("validateAttendances") == 1);
		sigConfig.setAttendanceFrequency(AttendanceFrequency.valueOf(rs.getInt("attendanceFrequency")));
		sigConfig.setMaxFileSize(rs.getInt("maxfilesize"));
		sigConfig.setMinimumJuryMembers(rs.getInt("minimumJuryMembers"));
		sigConfig.setMinimumJurySubstitutes(rs.getInt("minimumJurySubstitutes"));
		sigConfig.setJuryTimeStage1(rs.getInt("jurytimestage1"));
		sigConfig.setJuryTimeStage2(rs.getInt("jurytimestage2"));
		sigConfig.setSupervisorAssignsGrades(rs.getInt("supervisorAssignsGrades") == 1);
	}
}

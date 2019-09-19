package br.edu.utfpr.dv.siacoes.dao;

import br.edu.utfpr.dv.siacoes.log.UpdateEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class TemplateDAO<T> {

    protected abstract String getStringSqlFindDepartment();
    protected abstract void ormSave(PreparedStatement stmt, T config) throws SQLException;
    protected abstract String getStringSqlSave();
    protected abstract String getStringSqlUpdate();
    protected abstract void setSigConfig(ResultSet rs, T sigConfig) throws SQLException;

    public final T findByDepartment(int idDpto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = getStringSqlFindDepartment();

        try {
            conn = ConnectionDAO.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idDpto);
            rs = stmt.executeQuery();

            if(rs.next())
                return this.loadObject(rs);
            else
                return null;
        } finally {
            if ((rs != null) && !rs.isClosed()) {
                rs.close();
            }
            if ((stmt != null) && !stmt.isClosed()) {
                stmt.close();
            }
            if ((conn != null) && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public final void save(int idUsr, T sigConfig, boolean update) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionDAO.getInstance().getConnection();

            String strSave = getStringSqlSave();
            String strUpdate = getStringSqlUpdate();
            if(update) {
                statement = conn.prepareStatement(strUpdate); //Insert
            } else {
                statement = conn.prepareStatement(strSave); //Save
            }

            ormSave(statement, sigConfig);

            statement.execute();

            new UpdateEvent(conn).registerUpdate(idUsr, sigConfig);

        } finally {
            if((statement != null) && !statement.isClosed())
                statement.close();
            if((conn != null) && !conn.isClosed())
                conn.close();
        }
    }

    public final T loadObject(ResultSet rs)
            throws SQLException {
        T sigConfig = (T) new Object();
        setSigConfig(rs, sigConfig);

        return sigConfig;
    }
}
package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateJdbcTemplate {
    public void updateQuery(String query, List<Integer> parameters) throws SQLException {
        PreparedStatement pstmt = null;
        Connection con = DaoConnector.getConnection();
        pstmt = con.prepareStatement(query);
        setParameter(pstmt,parameters);
        pstmt.executeUpdate();
    }

    public void setParameter(PreparedStatement pstmt, List<Integer> parameters) throws SQLException {
        for (int i = 1; i <= parameters.size(); i++) {
            pstmt.setInt(i, parameters.get(i - 1));
        }
    }
}

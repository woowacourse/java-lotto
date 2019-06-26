package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class SelectJdbcTemplate<T> {
    public T executeQuery(String query, List<Integer> parameters) throws SQLException {
        Connection con = DaoConnector.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        setParameter(pstmt, parameters);
        ResultSet rs = pstmt.executeQuery();
        return getResult(rs);
    }

    public void setParameter(PreparedStatement pstmt, List<Integer> parameters) throws SQLException {
        for (int i = 1; i <= parameters.size(); i++) {
            pstmt.setInt(i, parameters.get(i - 1));
        }
    }

    public abstract T getResult(ResultSet resultSet) throws SQLException;
}

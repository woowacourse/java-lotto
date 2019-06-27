package lotto.domain.dao;

import lotto.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DaoTemplate {
    default void insertTemplate(String query) {
        try (Connection connection = DBUtils.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            setPreparedStatement(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void setPreparedStatement(PreparedStatement pstmt) throws SQLException;
}

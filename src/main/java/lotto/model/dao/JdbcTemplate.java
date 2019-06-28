package lotto.model.dao;

import lotto.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
        public void executeUpdate(String sql, PreparedStatementSetter pss){
                try(Connection connection = DatabaseUtil.getConnection();
                    PreparedStatement pstmt = connection.prepareStatement(sql)){
                        pss.setParameters(pstmt);

                        pstmt.executeUpdate();
                }catch(SQLException e){
                        System.out.println(e.getMessage());
                }
        }

        public Object executeQuery(String sql, PreparedStatementSetter pss, RowMapper rm){
                try(Connection connection = DatabaseUtil.getConnection();
                    PreparedStatement pstmt = createSelectPstmt(connection, sql, pss);
                    ResultSet rs = pstmt.executeQuery()){
                        return rm.mapRow(rs);
                }catch(SQLException e){
                        System.out.println(e.getMessage());
                }
                return null;
        }

        private PreparedStatement createSelectPstmt(Connection connection, String sql, PreparedStatementSetter pss) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pss.setParameters(pstmt);
                return pstmt;
        }
}

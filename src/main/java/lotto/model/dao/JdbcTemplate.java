package lotto.model.dao;

import lotto.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JdbcTemplate {

        public void executeUpdate(String sql){
                try(Connection connection = DatabaseUtil.getConnection();
                    PreparedStatement pstmt = connection.prepareStatement(sql)){
                        setParameters(pstmt);

                        pstmt.executeUpdate();
                }catch(SQLException e){
                        System.out.println(e.getMessage());
                }
        }

        public abstract void setParameters(PreparedStatement pstmt) throws SQLException;
}

package lotto.dao;

import lotto.dto.StatisticsDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsDAO extends DAO {
    public StatisticsDTO selectStatistics(String round) throws SQLException {
        String query = "SELECT * FROM Statistics WHERE round = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet re = pstmt.executeQuery();
        re.next();

        return new StatisticsDTO(re.getString("round"),
                re.getString("result"),
                re.getString("return_of_rate")
        );
    }

    public void insertStatistics(StatisticsDTO statisticsDTO) throws SQLException {
        String query = "INSERT INTO statistics (round, result, return_of_rate) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, statisticsDTO.getRound());
        pstmt.setString(2, statisticsDTO.getResult());
        pstmt.setString(3, statisticsDTO.getReturnOfRate());
        pstmt.executeUpdate();
    }
}

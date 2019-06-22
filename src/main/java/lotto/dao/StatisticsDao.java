package lotto.dao;

import lotto.dto.StatisticsDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsDao extends Dao {
    public StatisticsDto selectStatistics(String round) throws SQLException {
        String query = "SELECT round, " +
                "first_count, " +
                "second_count, " +
                "third_count, " +
                "fourth_count, " +
                "fifth_count, " +
                "miss_count, " +
                "return_of_rate " +
                "FROM statistics " +
                "WHERE round = ?";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet re = pstmt.executeQuery();
        re.next();

        return new StatisticsDto(re.getString("round"),
                re.getString("first_count"),
                re.getString("second_count"),
                re.getString("third_count"),
                re.getString("fourth_count"),
                re.getString("fifth_count"),
                re.getString("miss_count"),
                re.getString("return_of_rate")
        );
    }

    public void insertStatistics(StatisticsDto statisticsDTO) throws SQLException {
        String query = "INSERT INTO statistics (round, first_count, second_count, third_count, fourth_count, fifth_count, miss_count, return_of_rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, statisticsDTO.getRound());
        pstmt.setString(2, statisticsDTO.getFirstCount());
        pstmt.setString(3, statisticsDTO.getSecondCount());
        pstmt.setString(4, statisticsDTO.getThirdCount());
        pstmt.setString(5, statisticsDTO.getFourthCount());
        pstmt.setString(6, statisticsDTO.getFifthCount());
        pstmt.setString(7, statisticsDTO.getMissCount());
        pstmt.setString(8, statisticsDTO.getReturnOfRate());
        pstmt.executeUpdate();
    }
}

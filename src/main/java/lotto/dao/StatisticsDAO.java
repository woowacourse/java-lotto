package lotto.dao;

import lotto.dto.StatisticsDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDAO extends DAO {
    public List<StatisticsDTO> selectStatistics(String round) throws SQLException {
        String query = "SELECT * FROM Statistics WHERE round = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, round);
        ResultSet re = pstmt.executeQuery();

        List<StatisticsDTO> statisticsDTOs = new ArrayList<>();
        while (!re.next()) {
            StatisticsDTO statisticsDTO = new StatisticsDTO();
            statisticsDTO.setRound(re.getString("round"));
            statisticsDTO.setResult(re.getString("result"));
            statisticsDTO.setReturnOfRate(re.getString("return_of_rate"));
            statisticsDTOs.add(statisticsDTO);
        }
        return statisticsDTOs;

    }

    public void insertStatistics(StatisticsDTO statisticsDTO) throws SQLException {
        String query = "INSERT INTO statistics (round, result, return_of_rate) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, statisticsDTO.getRound());
        pstmt.setString(2, statisticsDTO.getResult());
        pstmt.setString(3, statisticsDTO.getReturnOfRate());
    }
}

package lotto.service;

import lotto.dao.StatisticsDAO;
import lotto.dto.StatisticsDTO;

import java.sql.SQLException;
import java.util.List;

public class StatisticsService {
    private StatisticsDAO statisticsDAO;

    public StatisticsService() {
        this.statisticsDAO = new StatisticsDAO();
    }

    public List<StatisticsDTO> searchRoundStatistics(String round) throws SQLException {
        return statisticsDAO.selectStatistics(round);
    }

    public void insertStatistics(StatisticsDTO statisticsDTO) throws SQLException {
        statisticsDAO.insertStatistics(statisticsDTO);
    }
}

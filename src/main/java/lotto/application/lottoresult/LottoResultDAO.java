package lotto.application.lottoresult;

import lotto.application.LottoDAO;
import lotto.domain.lottoresult.dto.LottoStatisticsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoResultDAO {
    private static LottoResultDAO lottoResultDAO = null;

    private LottoResultDAO() {
    }

    public static LottoResultDAO getInstance() {
        if (lottoResultDAO == null) {
            lottoResultDAO = new LottoResultDAO();
        }
        return lottoResultDAO;
    }

    public void createNextRound() {
        Connection connection = LottoDAO.getConnection();
        try {
            String query = "INSERT INTO lotto_result(round) values(?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
    }

    public int getLatestRoundNum() {
        Connection connection = LottoDAO.getConnection();
        try {
            String query = "SELECT round FROM lotto_result ORDER BY round DESC LIMIT 1";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return 0;
            return rs.getInt("round");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
        return 0;
    }

    public void deleteRound(int round) {
        Connection connection = LottoDAO.getConnection();
        try {
            String query = "delete from lotto_result where round = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, round);
            pstmt.executeUpdate();

            String queryForIncrement = "ALTER TABLE lotto_result AUTO_INCREMENT = ?";
            pstmt = connection.prepareStatement(queryForIncrement);
            pstmt.setInt(1, round);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
    }

    public void saveLottoStatistics(int round, LottoStatisticsDTO lottoStatisticsDTO) {
        Connection connection = LottoDAO.getConnection();
        try {
            String query = "update lotto_result set"
                    + " counts_of_first_rank = ?, counts_of_second_rank = ?, counts_of_third_rank = ?,"
                    + " counts_of_fourth_rank = ?, counts_of_fifth_rank = ?, profit_ratio = ?"
                    + " WHERE round = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, lottoStatisticsDTO.getCountsOfFirstRank());
            pstmt.setInt(2, lottoStatisticsDTO.getCountsOfSecondRank());
            pstmt.setInt(3, lottoStatisticsDTO.getCountsOfThirdRank());
            pstmt.setInt(4, lottoStatisticsDTO.getCountsOfFourthRank());
            pstmt.setInt(5, lottoStatisticsDTO.getCountsOfFifthRank());
            pstmt.setDouble(6, lottoStatisticsDTO.getProfitRatio());
            pstmt.setInt(7, round);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
    }

    public LottoStatisticsDTO fetchLottoStatisticsDto(int round) {
        Connection connection = LottoDAO.getConnection();
        LottoStatisticsDTO lottoStatisticsDTO = new LottoStatisticsDTO();
        try {
            String query = "SELECT * FROM lotto_result WHERE round = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, round);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) return lottoStatisticsDTO;
            lottoStatisticsDTO.setCountsOfFirstRank(rs.getInt("counts_of_first_rank"));
            lottoStatisticsDTO.setCountsOfSecondRank(rs.getInt("counts_of_second_rank"));
            lottoStatisticsDTO.setCountsOfThirdRank(rs.getInt("counts_of_third_rank"));
            lottoStatisticsDTO.setCountsOfFourthRank(rs.getInt("counts_of_fourth_rank"));
            lottoStatisticsDTO.setCountsOfFifthRank(rs.getInt("counts_of_fifth_rank"));
            lottoStatisticsDTO.setProfitRatio(rs.getDouble("profit_ratio"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            LottoDAO.closeConnection(connection);
        }
        return lottoStatisticsDTO;
    }
}


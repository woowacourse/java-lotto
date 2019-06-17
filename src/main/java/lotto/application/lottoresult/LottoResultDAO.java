package lotto.application.lottoresult;

import lotto.application.LottoJDBCDriverConnector;
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
        String query = "INSERT INTO lotto_result(round) values(?)";

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, 0);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement createPreparedStatement(Connection con, int userId) throws SQLException {
        String sql = "SELECT id, username FROM users WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        return ps;
    }

    public int getLatestRoundNum() {
        String query = "SELECT round FROM lotto_result ORDER BY round DESC LIMIT 1";

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.next()) return 0;
            return rs.getInt("round");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteRound(int round) {
        String query = "delete from lotto_result where round = ?";
        String queryForIncrement = "ALTER TABLE lotto_result AUTO_INCREMENT = ?";

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             PreparedStatement pstmtForIncrement = connection.prepareStatement(queryForIncrement)) {

            pstmt.setInt(1, round);
            pstmt.executeUpdate();

            pstmtForIncrement.setInt(1, round);
            pstmtForIncrement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveLottoStatistics(int round, LottoStatisticsDTO lottoStatisticsDTO) {
        String query = "update lotto_result set"
                + " counts_of_first_rank = ?, counts_of_second_rank = ?, counts_of_third_rank = ?,"
                + " counts_of_fourth_rank = ?, counts_of_fifth_rank = ?, profit_ratio = ?"
                + " WHERE round = ?";

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
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
        }
    }

    public LottoStatisticsDTO fetchLottoStatisticsDto(int round) {
        String query = "SELECT * FROM lotto_result WHERE round = ?";
        LottoStatisticsDTO lottoStatisticsDTO = new LottoStatisticsDTO();

        try (Connection connection = LottoJDBCDriverConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            pstmt.setInt(1, round);

            if (!rs.next()) return lottoStatisticsDTO;
            lottoStatisticsDTO.setCountsOfFirstRank(rs.getInt("counts_of_first_rank"));
            lottoStatisticsDTO.setCountsOfSecondRank(rs.getInt("counts_of_second_rank"));
            lottoStatisticsDTO.setCountsOfThirdRank(rs.getInt("counts_of_third_rank"));
            lottoStatisticsDTO.setCountsOfFourthRank(rs.getInt("counts_of_fourth_rank"));
            lottoStatisticsDTO.setCountsOfFifthRank(rs.getInt("counts_of_fifth_rank"));
            lottoStatisticsDTO.setProfitRatio(rs.getDouble("profit_ratio"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lottoStatisticsDTO;
    }
}


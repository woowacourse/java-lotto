package lotto.domain.dao;

import lotto.domain.dto.ResultDTO;
import lotto.domain.utils.ConnectionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDAO {

    public void setResult(final ResultDTO resultDTO) throws SQLException  {
        Connection con = ConnectionGenerator.getConnection();
        String query = "INSERT INTO result " +
                "(round, first_prize, second_prize, third_prize, forth_prize, fifth_prize, profit_rate, money) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);

        pstmt.setInt(1, resultDTO.getRound());
        pstmt.setInt(2, resultDTO.getFirstPrize());
        pstmt.setInt(3, resultDTO.getSecondPrize());
        pstmt.setInt(4, resultDTO.getThirdPrize());
        pstmt.setInt(5, resultDTO.getForthPrize());
        pstmt.setInt(6, resultDTO.getFifthPrize());
        pstmt.setDouble(7, resultDTO.getProfitRate());
        pstmt.setInt(8, resultDTO.getMoney());

        pstmt.executeUpdate();
        ConnectionGenerator.closeConnection(con);
    }

    public ResultDTO getResult(final int newRound) throws SQLException {
        Connection con = ConnectionGenerator.getConnection();
        String query = "SELECT * FROM result WHERE round = ?";
        PreparedStatement pstmt = ConnectionGenerator.getConnection().prepareStatement(query);
        pstmt.setInt(1, newRound);
        ResultSet rs = pstmt.executeQuery();

        ResultDTO resultDTO = new ResultDTO();

        if (!rs.next()) {
            return null;
        }

        resultDTO.setRound(rs.getInt("round"));
        resultDTO.setFirstPrize(rs.getInt("first_prize"));
        resultDTO.setSecondPrize(rs.getInt("second_prize"));
        resultDTO.setThirdPrize(rs.getInt("third_prize"));
        resultDTO.setForthPrize(rs.getInt("forth_prize"));
        resultDTO.setFifthPrize(rs.getInt("fifth_prize"));
        resultDTO.setProfitRate(rs.getDouble("profit_rate"));
        resultDTO.setMoney(rs.getInt("money"));
        return resultDTO;
    }

    public void deleteResult(final int round) throws SQLException {
        String query = "DELETE FROM result WHERE round = ?";
        PreparedStatement pstmt = ConnectionGenerator.getConnection().prepareStatement(query);
        pstmt.setInt(1, round);
        pstmt.executeUpdate();
    }
}

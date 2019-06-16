package lotto.dao;

import lotto.dao.exception.LottoWinningDAOException;

import lotto.dto.ResultLottoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LottoWinningDAO {
    private static LottoWinningDAO winningDAO;
    private Connection con;

    private LottoWinningDAO() {
        con = LottoDAOConnector.getConnection();
    }

    public static LottoWinningDAO getInstance() {
        return winningDAO == null ? new LottoWinningDAO() : winningDAO;
    }

    public int addResult(ResultLottoDTO resultLotto) {
        try {
            String query = "INSERT INTO result (winning_lotto,bonus_ball,result_statistic,result_prize,result_rate)"
                    + " VALUE(?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, resultLotto.getWinningLotto());
            pstmt.setInt(2, resultLotto.getBonusBall());
            pstmt.setString(3, resultLotto.getRank().toString());
            pstmt.setInt(4, resultLotto.getPrize());
            pstmt.setInt(5, resultLotto.getIncomeRate());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new LottoWinningDAOException(e);
        }
    }

    public List<String> findByResultRound(int round) {
        try {
            String query = "SELECT * FROM result WHERE rId = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);

            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) throw new LottoWinningDAOException("데이터 없음");
            return Arrays.asList(rs.getString("winning_lotto"), rs.getString("bonus_ball")
                    , rs.getString("result_statistic"), rs.getString("result_prize")
                    , rs.getString("result_rate"));
        } catch (SQLException e) {
            throw new LottoWinningDAOException(e);
        }
    }

}
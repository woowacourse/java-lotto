package lotto.dao;

import lotto.dto.ResultDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.dao.DatabaseConnection.closeConnection;
import static lotto.dao.DatabaseConnection.getConnection;

public class ResultDao {

    private static final List<String> PRIZE_NAMES = Arrays.asList("first", "second", "third", "fourth", "fifth", "none");
    private static ResultDao resultDao;

    private ResultDao() {
    }

    public static ResultDao getInstance() {
        if (resultDao == null) {
            resultDao = new ResultDao();
        }
        return resultDao;
    }

    public ResultDto findResultByRound(final int round) throws SQLException {
        String sql = "SELECT * FROM result WHERE id = ?";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        ResultDto resultDto = conbineResultDto(rs);
        closeConnection(con);
        return resultDto;
    }

    private ResultDto conbineResultDto(ResultSet rs) throws SQLException {
        ResultDto resultDto = new ResultDto();
        Map<String, Integer> prize = new HashMap<>();
        rs.next();

        for (String prizeName : PRIZE_NAMES) {
            prize.put(prizeName, rs.getInt(prizeName));
        }

        resultDto.setPrize(prize);
        resultDto.setWinningMoney(rs.getInt("winning_money"));
        return resultDto;
    }


    public void addResult(int round, ResultDto resultDto) throws SQLException {
        String sql = "INSERT INTO result VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, round);
        pstmt.setInt(2, resultDto.getWinningMoney());
        Map<String, Integer> prize = resultDto.getPrize();
        for (int i = 0; i < 6; i++) {
            String prizeName = PRIZE_NAMES.get(i);
            pstmt.setInt(i + 3, prize.get(prizeName));
        }

        pstmt.executeUpdate();
        closeConnection(con);
    }

    public void removeResult(int round) throws SQLException {
        String sql = "DELETE FROM result WHERE id = ?";
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, round);

        pstmt.executeUpdate();
        closeConnection(con);
    }
}

package lotto.domain.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lotto.domain.DAO.DBUtil.getConnection;

public class ResultDAO {
    public static void addResult(double winningMoney, String winningResult, double winningRate) throws SQLException {
        String query = "INSERT INTO result(winningMoney, winningResult, winningRate, lottoRound) VALUES (?, ?, ?, ?)";
        Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setDouble(1, winningMoney);
        pstmt.setString(2, winningResult);
        pstmt.setDouble(3, winningRate);
        pstmt.setInt(4, getCurrentLottoRound() + 1);
        pstmt.executeUpdate();
        DBUtil.closeConnection(con);
    }

    public static int getCurrentLottoRound() throws SQLException {
        String query = "SELECT MAX(lottoRound) FROM result";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return 0;
        return rs.getInt("MAX(lottoRound)");
    }

    public static void deleteResultByLottoRound(int lottoRound) throws SQLException {
        String query = "DELETE FROM result where lottoRound=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, lottoRound);
        pstmt.executeUpdate();
    }

    public static void selectWholeResultByCurrentRound(Map<String, Object> model, int inquiredRound) throws SQLException {
        Connection con = DBUtil.getConnection();
        String query = "SELECT * FROM result where lottoRound=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, inquiredRound);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return;
        model.put("winningMoney", rs.getDouble("winningMoney"));
        model.put("rate", rs.getDouble("winningRate"));
        String result = rs.getString("winningResult")
                .replace("[", "")
                .replace("]", "");
        List<String> winningResult = Stream.of(result.split(",")).collect(Collectors.toList());
        model.put("firstGrade", winningResult.get(0));
        model.put("secondGrade", winningResult.get(1));
        model.put("thirdGrade", winningResult.get(2));
        model.put("forthGrade", winningResult.get(3));
        model.put("fifthGrade", winningResult.get(4));
        DBUtil.closeConnection(con);
    }
}

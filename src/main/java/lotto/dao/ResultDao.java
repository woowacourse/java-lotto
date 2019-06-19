package lotto.dao;

import lotto.dbconnction.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDao {
    public static void addResult(List<String> results, int lottoRound) {
        try {
            String query = "INSERT INTO results (result, round) VALUES (?,?)";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);

            for (String result : results) {
                pstm.setString(1, result);
                pstm.setInt(2, lottoRound);
                pstm.addBatch();
                pstm.clearParameters();
            }

            pstm.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> offerResults(int lottoRound) {
        String query = "SELECT result FROM results WHERE round = ?";
        List<String> numbers = new ArrayList<>();

        try {
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);

            pstm.setInt(1, lottoRound);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                numbers.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return numbers;
    }


}

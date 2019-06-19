package lotto.dao;

import lotto.dbconnction.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LottoStatusDao {
    public static void addResultInfo(int lottoRound, double sum, String returnRate) {
        try {
            String query = "INSERT INTO resultInfo VALUES(?,?,?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);

            statement.setInt(1, lottoRound);
            statement.setInt(2, (int) sum);
            statement.setString(3, returnRate);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int offerPrize(int lottoRound) {
        try {
            String query = "SELECT prize FROM resultInfo WHERE round = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setInt(1, lottoRound);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) return 0;
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static double offerReturnRate(int lottoRound) {
        try {
            String query = "SELECT returnRate FROM resultInfo WHERE round = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setInt(1, lottoRound);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) return 0;
            return rs.getDouble(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}

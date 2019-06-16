package lotto.dao;

import lotto.dbconnction.DBConnection;
import lotto.domain.Lotto;
import lotto.domain.UserLotto;
import lotto.domain.WinningLotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDao {
    public static void addLotto(UserLotto userLotto, int round) {
        try {
            String query = "INSERT INTO userLotto (lottoNumbers, round) VALUES (?,?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);

            for (Lotto lotto : userLotto.getUserLotto()) {
                statement.setString(1, lotto.toString());
                statement.setInt(2, round);
                statement.addBatch();
                statement.clearParameters();
            }
            statement.executeBatch();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addWinningLotto(WinningLotto winningLotto, int lottoRound) {
        try {
            String query = "INSERT INTO winningLotto VALUES (?,?,?)";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);

            pstm.setInt(1, lottoRound);
            pstm.setString(2, winningLotto.toString());
            pstm.setInt(3, winningLotto.getBonus());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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

    public static int offerMaxRound() {
        try {
            String query = "SELECT MAX(round) FROM userLotto";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) return 0;

            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static List<String> offerUserLottoNumber(int lottoRound) {
        String query = "SELECT lottoNumbers FROM userLotto WHERE round = ?";

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

    public static String offerWinningNumber(int lottoRound) {
        try {
            String query = "SELECT winningLotto FROM winningLotto WHERE round = ?";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, lottoRound);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) return null;

            return rs.getString(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static int offerBonusNumber(int lottoRound) {
        try {
            String query = "SELECT bonus FROM winningLotto WHERE round = ?";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);
            pstm.setInt(1, lottoRound);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) return 0;

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
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

package lotto.dao;

import lotto.dbconnction.DBConnection;
import lotto.domain.Lotto;
import lotto.domain.UserLotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}

package lotto.dao;

import lotto.dbconnction.DBConnection;
import lotto.domain.Lotto;
import lotto.domain.UserLotto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LottoDao {
    public static void addLottoes(UserLotto userLotto, int round) {
        try {
            String query = "INSERT INTO userLotto (lottoNumbers, round) VALUES (?,?)";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);

            for (Lotto lotto : userLotto.getUserLotto()) {
                pstm.setString(1, lotto.toString());
                pstm.setInt(2, round);
                pstm.addBatch();
                pstm.clearParameters();
            }
            pstm.executeBatch();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getMaxRound() {
        try {
            String query = "SELECT MAX(round) FROM userLotto";
            PreparedStatement pstm = DBConnection.getConnection().prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) return 0;

            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}

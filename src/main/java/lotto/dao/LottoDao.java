package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.util.DbConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoDao {
    public static void addLotto(Lotto lotto, int roundNo) {
        try {
            String query = "INSERT INTO lotto(NO_1, NO_2, NO_3, NO_4, NO_5, NO_6, ROUND_NO)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = DbConnector.getConnection().prepareStatement(query);
            pstmt.setInt(1, lotto.getLottoNo(0));
            pstmt.setInt(2, lotto.getLottoNo(1));
            pstmt.setInt(3, lotto.getLottoNo(2));
            pstmt.setInt(4, lotto.getLottoNo(3));
            pstmt.setInt(5, lotto.getLottoNo(4));
            pstmt.setInt(6, lotto.getLottoNo(5));
            pstmt.setInt(7, roundNo);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }
    }

    public static Lottos findByRoundNo(int roundNo) {
        try {
            String query = "SELECT * FROM lotto WHERE round_no = ?";
            PreparedStatement pstmt = DbConnector.getConnection().prepareStatement(query);
            pstmt.setInt(1, roundNo);
            ResultSet rs = pstmt.executeQuery();

            List<Lotto> lottos = new ArrayList<>();
            while (rs.next()) {
                lottos.add(Lotto.of(
                        Arrays.asList(rs.getInt("NO_1"),
                                rs.getInt("NO_2"),
                                rs.getInt("NO_3"),
                                rs.getInt("NO_4"),
                                rs.getInt("NO_5"),
                                rs.getInt("NO_6"))
                ));
            }
            return new Lottos(lottos);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DbConnector.closeConnection();
        }

    }
}

package lotto.domain.dao;

import lotto.domain.model.Lotto;
import lotto.domain.utils.ConnectionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LottoDAO {

    public void addLotto(final Lotto lotto, final int round) {
        try {
            Connection con = ConnectionGenerator.getConnection();
            String query = "INSERT INTO lotto " +
                    "(round, first_num, second_num, third_num, forth_num, fifth_num, sixth_num) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);

            for (int i = 0; i <= 5; i++) {
                pstmt.setInt(i + 2, lotto.getLotto().get(i).getNumber());
            }

            pstmt.executeUpdate();
            ConnectionGenerator.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void selectLotto(final int round) {
    }
}

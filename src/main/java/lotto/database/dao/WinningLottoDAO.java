package lotto.database.dao;

import lotto.domain.ticket.LottoNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class WinningLottoDAO {
    private final Connection connection;

    public WinningLottoDAO(final Connection connection) {
        this.connection = connection;
    }

    public void addWinningLotto(int round, Set<LottoNumber> winningNumbers, int bonusNumber) throws SQLException {
        String query = "INSERT INTO winning (round, num1, num2, num3, num4, num5, num6, bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        int index = 1;
        pstmt.setInt(index++, round);
        for (LottoNumber lottoNumber  : winningNumbers) {
            pstmt.setInt(index++, lottoNumber.getNumber());
        }
        pstmt.setInt(index, bonusNumber);
        pstmt.executeUpdate();
    }
}

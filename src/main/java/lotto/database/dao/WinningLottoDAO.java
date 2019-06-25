package lotto.database.dao;

import lotto.domain.ticket.LottoNumber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WinningLottoDAO {
    private final Connection connection;
    private final int WINNING_NUMBER_LENGTH = 7;

    public WinningLottoDAO(final Connection connection) {
        this.connection = connection;
    }

    public void addWinningLotto(int round, Set<LottoNumber> winningNumbers, int bonusNumber) throws SQLException {
        String query = "INSERT INTO winning (round, num1, num2, num3, num4, num5, num6, bonus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        int index = 1;
        pstmt.setInt(index++, round);
        for (LottoNumber lottoNumber : winningNumbers) {
            pstmt.setInt(index++, lottoNumber.getNumber());
        }
        pstmt.setInt(index, bonusNumber);
        pstmt.executeUpdate();
    }

    public List<Integer> getWinnigNumbersByRound(final int round) throws SQLException {
        String query = "SELECT num1,num2,num3,num4,num5,num6,bonus FROM winning WHERE round = (?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> winningNumbers = new ArrayList<>();
        rs.next();
        for (int i = 1; i <= WINNING_NUMBER_LENGTH; i++) {
            winningNumbers.add(rs.getInt(i));
        }
        rs.close();
        return winningNumbers;
    }
}

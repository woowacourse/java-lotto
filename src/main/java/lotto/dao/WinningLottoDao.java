package lotto.dao;

import lotto.DtoConverter;
import lotto.InputParser;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.view.LottoDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WinningLottoDao {
    private static final String DELIMITER = ",";

    public void add(final WinningLotto winningLotto, final Integer turn) {
        Connection conn = DBManager.getConnection();
        LottoDto lotto = new DtoConverter().convertLottoToDto(winningLotto.getWinningLotto());
        try {
            String query = "insert into winning_lotto(turn, numbers, bonus_number) values (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, turn);
            pstmt.setString(2, makeNumbersFormat(lotto.getNumbers()));
            pstmt.setInt(3, winningLotto.getBonusNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }

    private String makeNumbersFormat(final List<String> numbers) {
        return String.join(DELIMITER, numbers);
    }

    public WinningLotto findByTurn(final int turn) {
        Connection conn = DBManager.getConnection();
        try {
            String query = "select numbers, bonus_number from winning_lotto where turn = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, turn);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Lotto lotto = new InputParser().makeLotto(rs.getString(1));
                LottoNumber lottoNumber = new InputParser().makeLottoNumber(rs.getInt(2));
                return WinningLotto.of(lotto, lottoNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
        return null;
    }

    public void deleteAll() {
        Connection conn = DBManager.getConnection();
        try {
            String query = "delete from winning_lotto";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(conn);
        }
    }
}

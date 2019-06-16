package lotto.dao;


import lotto.config.DBConnector;
import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import lotto.domain.WinningLotto;
import lotto.utils.ConverterToLottoNos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class WinningLottoDao {
    private DBConnector dbConnector;

    public WinningLottoDao(final DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void save(WinningLotto winningLotto, int round) {
        String sql = "INSERT INTO winning_lotto (numbers, bonus_no, round) VALUES (?, ?, ?)";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, winningLotto.getLottos());
            ps.setInt(2, winningLotto.getBonusNo());
            ps.setInt(3, round);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<WinningLotto> findAllByRound(int round) {
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, round);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                LottoNo bonusNo = LottoNo.from(rs.getInt("bonus_no"));
                Lotto lotto = Lotto.of(ConverterToLottoNos.convert(rs.getString("numbers")));
                return Optional.of(new WinningLotto(lotto, bonusNo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private PreparedStatement createPreparedStatement(final Connection conn, final int round) throws SQLException {
        String sql = "SELECT * FROM winning_lotto WHERE round = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, round);
        return ps;
    }
}

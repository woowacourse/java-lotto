package lotto.dao;

import lotto.config.DBConnector;
import lotto.domain.Lotto;
import lotto.utils.ConverterToLottoNos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDao {
    private static final int BATCH_SIZE = 1000;
    private DBConnector dbConnector;

    public LottoDao(final DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void add(List<Lotto> lottos, int round) {
        String sql = "INSERT INTO lotto (numbers, round) VALUES(?, ?)";
        int count = 0;

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (final Lotto lotto : lottos) {
                ps.setString(1, lotto.toString());
                ps.setInt(2, round);
                ps.addBatch();
                if (++count % BATCH_SIZE == 0) {
                    ps.executeBatch();
                }
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lotto> findAllByRound(int round) {
        List<Lotto> lottos = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = createPreparedStatement(conn, round);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lotto lotto = Lotto.of(ConverterToLottoNos.convert(rs.getString("numbers")));
                lottos.add(lotto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lottos;
    }

    private PreparedStatement createPreparedStatement(Connection con, int round) throws SQLException {
        String sql = "SELECT numbers FROM lotto WHERE round = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, round);
        return ps;
    }
}

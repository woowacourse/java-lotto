package lotto.dao;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import lotto.domain.generator.LottoNosManualGenerator;
import lotto.utils.ConverterToLottoNos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LottoDao {

    //TODO 중간에 하나라도 실패하면 롤백은 어떻게?
    public boolean add(List<Lotto> lottos, int round) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        int count = 0;
        try {
            for (final Lotto lotto : lottos) {
                String sql = "INSERT INTO lotto (numbers, round) VALUES(?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, lotto.toString());
                ps.setInt(2, round);
                count += ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps);
        }

        return count == lottos.size();
    }

    public List<Lotto> findByRound(int round) {
        Connection conn = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Lotto> lottos = new ArrayList<>();

        try {
            String sql = "SELECT numbers FROM lotto WHERE round = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, round);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lotto lotto = Lotto.of(ConverterToLottoNos.convert(rs.getString("numbers")));
                lottos.add(lotto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return lottos;
    }
}
